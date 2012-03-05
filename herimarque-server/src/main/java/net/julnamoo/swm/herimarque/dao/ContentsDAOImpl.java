package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.model.User;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;

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
			
			MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
			logger.info("upload map info into mongo of {}", mapInfo.getFilePath());
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
			MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
			Query query = new Query();
			query.addCriteria(new Criteria("id").is(id));
			resultList = mt.find(query, MapInfo.class);
			
//			DBObject qdoc = new BasicDBObject();
//			qdoc.put("user", id);
//			
//			DBCursor results = collection.find(qdoc);
//			results.batchSize(20);
//			
//			while(results.hasNext())
//			{
//				DBObject doc = results.next();
//				
//				//set mapInfo instances for adding to the list
//				MapInfo mi = doc2MapInfo(doc);
//				logger.debug("add {} to the UserMapList", mi.toString());
//				resultList.add(mi);
//			}
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
		
		MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
		
		DBObject qdoc = new BasicDBObject();
		qdoc.put("area", ctrdCd);
		BasicQuery query = new BasicQuery(qdoc);
		query.limit(20);
		mapList = mt.find(query, MapInfo.class);
		
//		setMongo();
//		
//		DBCursor results = collection.find(qdoc);
//		results.batchSize(20);
//		
//		while(results.hasNext())
//		{
//			//set mapInfo instances for adding to the list
//			MapInfo mi = doc2MapInfo(results.next());
//			logger.debug("add {} tp the locationMapList", mi.toString());
//			mapList.add(mi);
//		}
		
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
		MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
		
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
//		setMongo();
//		
//		DBObject qdoc = new BasicDBObject();
////		qdoc.put("_id", new ObjectId(comment.getMapKey()));
//		
//		DBCursor cursor = collection.find(qdoc);
//		if(cursor.size() > 0)
//		{
//			if(cursor.size() != 1) throw new Exception(cursor.next().get("_id") + " map is more than one, please check it.");
//			
//			BasicDBObject mapInfo = (BasicDBObject) cursor.next();
//			
//			//check whether it has comments
//			BasicDBList cList = null;
//			if(mapInfo.containsField("comments"))
//			{
//				cList = (BasicDBList) mapInfo.get("comments");
//			}else
//			{
//				//the first comment to save
//				cList = new BasicDBList();
//			}
//			
//			//build DBobject with the comment and add it to comment list
//			DBObject cObj = comment2DBObj(comment);
//			cList.add(cObj);
//			
//			//put it to origin DBObj
//			mapInfo.put("comments", cList);
//			
//			//update the obj
//			collection.save(mapInfo);
//			
//			result = true;
//		}else
//		{
//			result = false;
//		}
	}
	
	@Override
	public List<MapInfo> mostHitMap() 
	{
		setMongo();
		
		
		return null;
	}
	
	@Override
	public List<MapInfo> getMapsInPeriod(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isAuthedUser(String id)
	{
		logger.debug("check authentication of the {}", id);

		MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
	
		Query q = new Query();
		q.addCriteria(new Criteria("user").is(id));
		
		User expUser = mt.findOne(q, User.class, "user");
		boolean result = expUser == null ? false : expUser.isAuth();
		
		logger.debug("return the autentication of the {}, {}", id, result);
		return result;
	}
	
	private MapInfo doc2MapInfo(DBObject doc)
	{
		MapInfo mi = new MapInfo();
		mi.setUser(doc.get("user").toString());
		mi.setFilePath(doc.get("filePath").toString());
		mi.setUploadTime(doc.get("uploadTime").toString());
		
		mi.setLikeCount(Integer.valueOf(doc.get("likeCount").toString()));
		
		BasicDBList areaList = (BasicDBList) doc.get("area");
		BasicDBList logList = (BasicDBList) doc.get("logging");
		BasicDBList likeUserList = (BasicDBList) doc.get("likes");
		BasicDBList commentList = (BasicDBList) doc.get("comments");
//		mi.setAge(doc.get("age").toString());
//		mi.setArea(doc.get("area").toString());
//		mi.setKind(doc.get("kind").toString());
		
		return mi;
	}
	
	private DBObject comment2DBObj(Comment comment)
	{
		BasicDBObject target = new BasicDBObject();
		target.put("content", comment.getContent());
		target.put("user", comment.getUserKey());
		
		return target;
	}
}
