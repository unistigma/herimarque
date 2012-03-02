package net.julnamoo.swm.herimarque.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Notice;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository(value="noticeDAO")
public class NoticeDAOImpl extends SimpleHerimarqueDAO{

	@PostConstruct
	public void setCollectionName() 
	{
		collectionName = "notices";
	}
	
	/**
	 * add notice object to mongo
	 * @param newNotice
	 */
	public void addNotice(Notice newNotice)
	{
		setMongo();
		DBObject notice = notice2DBObj(newNotice);
		collection.save(notice);
	}

	/**
	 * Retrieve the newest notice
	 * @return newes notice object
	 */
	public Notice getNewest()
	{
		setMongo();
		
		DBCursor cursor = collection.find().limit(1).sort(new BasicDBObject("date", -1));
		BasicDBObject result = (BasicDBObject) cursor.next();
		return DBObj2Notice(result);
	}
	
	/**
	 * The date of the newest notice
	 * @return the formated date string of the newset notice(yyy/MM/dd/HH:mm:ss)
	 */
	public String getLastUpdateNoticeDate()
	{
		Notice notice = this.getNewest();
		return notice.getDate();
	}
	
	/**
	 * convert notice to DBobject
	 * @param notice
	 * @return converted DBobject
	 */
	private DBObject notice2DBObj(Notice notice)
	{
		BasicDBObject target = new BasicDBObject();
		target.put("title", notice.getTitle());
		target.put("content", notice.getContent());
		//set date field value
		String format = PropertiesUtil.getValueFromProperties("herimarque.properties", "dateFormat");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date uploadTime = null;
		try 
		{
			uploadTime = sdf.parse(notice.getDate());
		} catch (ParseException e) 
		{
			e.printStackTrace();
			uploadTime = new Date();
		}
		target.put("date", uploadTime);
		
		return target;
	}
	
	/**
	 * convert DBobject to Notice
	 * @param dbObj
	 * @return converted notice object
	 */
	private Notice DBObj2Notice(DBObject dbObj)
	{
		Notice target = new Notice();
		target.setTitle(((BasicDBObject)dbObj).getString("title"));
		target.setContent(((BasicDBObject) dbObj).getString("content"));
		target.setDate(((BasicDBObject) dbObj).getString("date"));

		return target;
	}
	
}
