package net.julnamoo.swm.herimarque.dao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.MapInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * 
 * @author Julie_air
 *
 */

@Repository(value="contentsDAO")
public class ContentsDAOImpl extends SimpleHerimarqueDAO {

	Logger logger = LoggerFactory.getLogger(ContentsDAOImpl.class);

	public ContentsDAOImpl(){}

	@PostConstruct
	public void setUp()
	{
		collectionName = "data";
	}

	public void addMapInfo(MapInfo mapInfo)
	{

		//check authentication of the user
		if(isAuthedUser(mapInfo.getUser()))
		{
			setMongo();
			logger.info("upload map info into mongo of {}", mapInfo.getFilePath());
			DBObject doc = (DBObject) JSON.parse(new Gson().toJson(mapInfo));
			collection.insert(doc);
		}
	}

	public List<String> getUsersMapList(String id)
	{
		List<String> resultList = new ArrayList<String>();
		
		// add the list if the user is authenticated
		if(isAuthedUser(id))
		{
			setMongo();
			logger.debug("find all maps paths of {}", id);
			DBObject qdoc = new BasicDBObject();
			qdoc.put("user", id);
			
			DBCursor results = collection.find(qdoc);
			
			while(results.hasNext())
			{
				DBObject doc = results.next();
				
				//set mapInfo instances for adding to the list
				MapInfo mi = new MapInfo();
				mi.setUser(doc.get("user").toString());
				mi.setFilePath(doc.get("filePath").toString());
				mi.setAge(doc.get("age").toString());
				mi.setArea(doc.get("area").toString());
				mi.setKind(doc.get("kind").toString());
				mi.setUploadTime(doc.get("uploadTime").toString());

				String val = new Gson().toJson(mi);
				logger.debug("add {} to the UserMapList", val);
				resultList.add(val);
			}
		}
		
		logger.debug("return the {} map list, size is {}", id, resultList.size());
		return resultList;
	}
	
	private boolean isAuthedUser(String id)
	{
		logger.debug("check authentication of the {}", id);
		
		setMongo();
		collection = db.getCollection("users");
		
		boolean result = false;
		
		DBObject checkUser = new BasicDBObject();
		checkUser.put("id", id);
		
		DBCursor checkUserResults = collection.find(checkUser);
		if(checkUserResults.count() > 0)
		{
			logger.debug("There is the user {}", id);
			DBObject user = checkUserResults.next();
			result = (Boolean) user.get("auth");
		}else result = false;
		
		logger.debug("return the autentication of the {}, {}", id, result);
		return result;
	}
}
