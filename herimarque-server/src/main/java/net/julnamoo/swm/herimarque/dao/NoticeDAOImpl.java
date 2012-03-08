package net.julnamoo.swm.herimarque.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Notice;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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
	public Notice addNotice(Notice newNotice)
	{
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		logger.info("Enroll new notice object");
		mt.save(newNotice);
		return newNotice;
	}

	/**
	 * Retrieve the newest notice
	 * @return newes notice object
	 */
	public Notice getNewest()
	{
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		Query query = new Query();
		query.sort().on("date", Order.DESCENDING);
		Notice notice = mt.findOne(query, Notice.class);
		return notice;
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
	 * return the newest 10 notices in the order from start date 
	 * @param start
	 * @return noticeList with 10 
	 */
	public List<Notice> getNotices(String start)
	{
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		Query query = new Query();
		//{ date: { $gt: "2012-03-06T06:11:53.182Z" } 
		query.addCriteria(new Criteria("date").gte(start));
		query.sort().on("date", Order.DESCENDING);
		query.limit(10);
		List<Notice> noticeList = mt.find(query, Notice.class);
		logger.debug("Retrieve notices from date {}, size is {}", start, noticeList.size());
		return noticeList;
	}
}
