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
public class NoticeDAOImpl extends SimpleHerimarqueDAO implements NoticeDAO{

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.NoticeDAO#setCollectionName()
	 */
	@Override
	@PostConstruct
	public void setCollectionName() 
	{
		collectionName = "notices";
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.NoticeDAO#addNotice(net.julnamoo.swm.herimarque.model.Notice)
	 */
	@Override
	public Notice addNotice(Notice newNotice)
	{
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		logger.info("Enroll new notice object");
		mt.save(newNotice);
		return newNotice;
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.NoticeDAO#getNewest()
	 */
	@Override
	public Notice getNewest()
	{
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		Query query = new Query();
		query.sort().on("date", Order.DESCENDING);
		Notice notice = mt.findOne(query, Notice.class);
		return notice;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.NoticeDAO#getLastUpdateNoticeDate()
	 */
	@Override
	public String getLastUpdateNoticeDate()
	{
		Notice notice = this.getNewest();
		return notice.getDate();
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.NoticeDAO#getNotices(java.lang.String)
	 */
	@Override
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
