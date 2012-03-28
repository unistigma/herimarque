package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

/**
 * 
 * @author Julie_air
 *
 */

@Repository(value="contentsDAO")
public class ContentsDAOImpl extends SimpleHerimarqueDAO implements ContentsDAO {

	Logger logger = LoggerFactory.getLogger(ContentsDAOImpl.class);

	public ContentsDAOImpl(){}

	@PostConstruct
	@Override
	public void setCollectionName() 
	{
		collectionName = "data";
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#addMapInfo(net.julnamoo.swm.herimarque.model.MapInfo)
	 */
	@Override
	public String addMapInfo(MapInfo mapInfo)
	{
		//check authentication of the user
		if(isAuthedUser(mapInfo.getUser()))
		{
			
			MongoTemplate mt = new MongoTemplate(mongo, dbName);
			logger.info("upload map info into mongo of {}", mapInfo.getTitle());
			mt.save(mapInfo);
			return mapInfo.getMapKey();
		}
		
		logger.info("unauthoried user({}) request in addMapInfo", mapInfo.getUser());
		return null;
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#getUsersMapList(java.lang.String)
	 */
	@Override
	public List<MapInfo> getUsersMapList(String id)
	{
		List<MapInfo> resultList = new ArrayList<MapInfo>();
		
		// add the list if the user is authenticated
		if(isAuthedUser(id))
		{
			logger.debug("find all maps paths of {}", id);
			MongoTemplate mt = new MongoTemplate(mongo, dbName);
			Query query = new Query();
			query.addCriteria(new Criteria("user").is(id));
			resultList = mt.find(query, MapInfo.class);
			
			logger.debug("return the {} map list, size is {}", id, resultList.size());
		}
		
		logger.info("unauthoried user({}) request in getUserMapInfo", id);
		return resultList;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#getLocationMapList(java.lang.String)
	 */
	@Override
	public List<MapInfo> getLocationMapList(String ctrdCd)
	{
		List<MapInfo> mapList = new ArrayList<MapInfo>();
		
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		
		Query query = new Query();
		query.limit(20);
		query.addCriteria(new Criteria().elemMatch(new Criteria("area").is(ctrdCd)));
		mapList = mt.find(query, MapInfo.class);
		logger.debug("return the area:{} map list, size is {}", ctrdCd, mapList.size());
		return mapList;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#addComment(net.julnamoo.swm.herimarque.model.Comment)
	 */
	@Override
	public boolean addComment(String mapKey, Comment comment) 
	{
		boolean result = false;
		MongoTemplate mt = new MongoTemplate(mongo, dbName);
		
		Query query = new Query();
		query.addCriteria(new Criteria("mapKey").is(mapKey));
		//retrieve the older map
		MapInfo mapInfo = mt.findOne(query, MapInfo.class);
		if(mapInfo == null)
		{
			logger.info("There is no map with the key :{}", mapKey);
			result = false;
		}else
		{
			List<Comment> comments = mapInfo.getComments();
			if(comments == null)
			{
				logger.debug("The first comment for the map:{}", mapKey);
				comments = new ArrayList<Comment>();
			}
			comments.add(comment);
			logger.info("Add the comment by {} to the map {}", comment.getUserKey(), mapKey);
			
			mt.updateFirst(query, Update.update("comments", comments), MapInfo.class);
			result = true;
		}
		
		return result;
	}
	
	@Override
	public List<MapInfo> getMostHitMapList(String user) 
	{
		if(isAuthedUser(user))
		{
			MongoTemplate mt = new MongoTemplate(mongo, dbName);
			BasicQuery query = new BasicQuery(new BasicDBObject());
			query.limit(20);
			query.setSortObject(new BasicDBObject("likeCount", -1));

			logger.debug("query obj is {}", query);
			List<MapInfo> mapInfoList = mt.find(query, MapInfo.class);
			return mapInfoList;
		}else
		{
			logger.info("{} is not authenticated user at getMostHitMapList", user);
			return null;
		}
	}
	
	@Override
	public List<MapInfo> getMapsInPeriod(String user, Date start, Date end) 
	{
		if(isAuthedUser(user))
		{
			MongoTemplate mt = new MongoTemplate(mongo, dbName);
			Query query = new Query();
			query.limit(20);
			List<MapInfo> mapInfoList = mt.find(query, MapInfo.class);
			logger.debug("retrieve the mapInfo list, size is {}", mapInfoList.size());
			return mapInfoList;
		}else
		{
			logger.info("{} is not authenticated user at getMapsInPeriod");
			return null;
		}
	}
	
	@Override
	public MapInfo likeMap(String id, String mapKey) 
	{
		//check the user is the authenticated
		if(isAuthedUser(id))
		{
			MongoTemplate mt = new MongoTemplate(mongo, dbName);
			Query query = new Query();
			query.addCriteria(new Criteria("mapKey").is(mapKey));

			//find the map info instance of the user
			MapInfo mi = mt.findOne(query, MapInfo.class);
			//get the like list and count then update them
			List<String> likeList = mi.getLikes();
			if(likeList == null)
			{
				likeList = new ArrayList<String>();
			}
			
			if(!likeList.contains(id))
			{
				likeList.add(id);
				mi.setLikes(likeList); //update like list
				int likeCount = likeList.size();
				mi.setLikeCount(likeCount); //update like count
				mt.updateFirst(query, Update.update("likes", likeList), MapInfo.class);
				logger.debug("update the map {} like count to {}", mapKey, likeCount);
			}
			
			return mi;
		}else
		{
			return null;
		}
	}

	@Override
	public boolean unlikeMap(String id, String mapKey)
	{
		if(isAuthedUser(id))
		{
			Query query = new Query();
			query.addCriteria(new Criteria("mapKey").is(mapKey));
			query.addCriteria(new Criteria("likes").is(id));
			
			MapInfo mapInfo = mongoTemplate.findOne(query, MapInfo.class);
			//If there is no user or no map, then return false
			if(mapInfo == null) return false;
			List<String> likeList = mapInfo.getLikes();
			likeList.remove(id);
			mongoTemplate.updateFirst(query, Update.update("likes", likeList), MapInfo.class);
			logger.debug("Success to remove the user {} from like list of {}", id, mapKey);
			return true;
		}else
		{
			return false;
		}
	}
}
