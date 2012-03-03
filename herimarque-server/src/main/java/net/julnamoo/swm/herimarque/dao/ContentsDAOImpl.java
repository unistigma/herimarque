package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
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
			setMongo();
			logger.info("upload map info into mongo of {}", mapInfo.getFilePath());
			BasicDBObject doc = (BasicDBObject) JSON.parse(new Gson().toJson(mapInfo));
			collection.insert(doc);
			ObjectId oid = (ObjectId) doc.get("_id");
			return oid.toString();
		}
		
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
			setMongo();
			logger.debug("find all maps paths of {}", id);
			DBObject qdoc = new BasicDBObject();
			qdoc.put("user", id);
			
			DBCursor results = collection.find(qdoc);
			results.batchSize(20);
			
			while(results.hasNext())
			{
				DBObject doc = results.next();
				
				//set mapInfo instances for adding to the list
				MapInfo mi = doc2MapInfo(doc);
				logger.debug("add {} to the UserMapList", mi.toString());
				resultList.add(mi);
			}
		}
		
		logger.debug("return the {} map list, size is {}", id, resultList.size());
		return resultList;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#getLocationMapList(java.lang.String)
	 */
	@Override
	public List<MapInfo> getLocationMapList(String ctrdCd)
	{
		List<MapInfo> mapList = new ArrayList<MapInfo>();
		
		setMongo();
		DBObject qdoc = new BasicDBObject();
		qdoc.put("area", ctrdCd);
		
		DBCursor results = collection.find(qdoc);
		results.batchSize(20);
		
		while(results.hasNext())
		{
			//set mapInfo instances for adding to the list
			MapInfo mi = doc2MapInfo(results.next());
			logger.debug("add {} tp the locationMapList", mi.toString());
			mapList.add(mi);
		}
		
		logger.debug("return the area:{} map list, size is {}", ctrdCd, mapList.size());
		return mapList;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#getKindMapList(java.lang.String)
	 */
	@Override
	public List<MapInfo> getKindMapList(String itemCd)
	{
		List<MapInfo> mapList = new ArrayList<MapInfo>();
		
		setMongo();
		DBObject qdoc = new BasicDBObject();
		qdoc.put("kind", itemCd);
		
		DBCursor results = collection.find(qdoc);
		results.batchSize(20);
		
		while(results.hasNext())
		{
			MapInfo mi = doc2MapInfo(results.next());
			logger.debug("add {} to the kindMapList", mi.toString());
			mapList.add(mi);
		}
		
		logger.debug("return the kind:{} list , size is {}", itemCd, mapList.size());
		return mapList;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.dao.ContentsDAO#addComment(net.julnamoo.swm.herimarque.model.Comment)
	 */
	@Override
	public boolean addComment(Comment comment) throws Exception
	{
		boolean result = false;
		setMongo();
		
		DBObject qdoc = new BasicDBObject();
		qdoc.put("_id", new ObjectId(comment.getMapKey()));
		
		DBCursor cursor = collection.find(qdoc);
		if(cursor.size() > 0)
		{
			if(cursor.size() != 1) throw new Exception(cursor.next().get("_id") + " map is more than one, please check it.");
			
			BasicDBObject mapInfo = (BasicDBObject) cursor.next();
			
			//check whether it has comments
			BasicDBList cList = null;
			if(mapInfo.containsField("comments"))
			{
				cList = (BasicDBList) mapInfo.get("comments");
			}else
			{
				//the first comment to save
				cList = new BasicDBList();
			}
			
			//build DBobject with the comment and add it to comment list
			DBObject cObj = comment2DBObj(comment);
			cList.add(cObj);
			
			//put it to origin DBObj
			mapInfo.put("comments", cList);
			
			//update the obj
			collection.save(mapInfo);
			
			result = true;
		}else
		{
			result = false;
		}
		return result;
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
	
	private MapInfo doc2MapInfo(DBObject doc)
	{
		MapInfo mi = new MapInfo();
		mi.setUser(doc.get("user").toString());
		mi.setFilePath(doc.get("filePath").toString());
//		mi.setAge(doc.get("age").toString());
//		mi.setArea(doc.get("area").toString());
//		mi.setKind(doc.get("kind").toString());
		mi.setUploadTime(doc.get("uploadTime").toString());
		
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
