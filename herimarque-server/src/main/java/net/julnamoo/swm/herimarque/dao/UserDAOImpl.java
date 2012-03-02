package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * @author Julie_air
 *
 */
@Repository(value="userDAO")
public class UserDAOImpl extends SimpleHerimarqueDAO implements UserDAO
{
	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	public UserDAOImpl(){}
	
	@PostConstruct
	@Override
	public void setCollectionName() 
	{
		collectionName = "users";
		
	}
	
	@Override
	public String addUser(String id, String key, boolean auth) 
	{
		setMongo();

		//Set authorization value to verify on going or done.
		DBObject doc = new BasicDBObject();
		doc.put("id", id);
		doc.put("finalKey", key);
		doc.put("auth", auth);

		//insert or update
		String _id = null;
		if(auth)
		{
			DBObject qdoc = new BasicDBObject();
			qdoc.put("id", id);
			collection.update(qdoc, doc);
			BasicDBObject fdoc = (BasicDBObject) collection.findOne(qdoc);
			ObjectId oid = (ObjectId) fdoc.get("_id");
			_id = String.valueOf(oid.getInc());
		}else
		{
			collection.save(doc);
			ObjectId oid = (ObjectId) doc.get("_id");
			_id = oid.toString();
		}
		logger.debug("exec save with a object, {}", doc);
		return _id;//doc.get("_id").toString();
	}

	@Override
	public String getUserKey(String id) 
	{
		setMongo();

		DBObject doc = new BasicDBObject();
		doc.put("id", id);

		logger.info("Retrive key of {}", id);
		DBCursor cursor = collection.find(doc);
		if(cursor.size() == 0)
		{
			return null;
		}else
		{
			DBObject resultDoc = cursor.next();
			ObjectId oid = (ObjectId) resultDoc.get("_id");
			return oid.toString();
		}
	}

	@Override
	public String changeInfo(String id, String newKey) 
	{
		setMongo();

		//find the target object
		DBObject doc = new BasicDBObject();
		doc.put("id", id);
		doc = collection.findOne(doc);
		//set the new key
		doc.put("finalKey", newKey);
		collection.save(doc);

		ObjectId oid = (ObjectId) doc.get("_id");
		String _id = oid.toString();
		logger.debug("change Info of {} with {}", id, newKey);
		return _id;
	}

	@Override
	public void delUser(String id) 
	{
		setMongo();
		
		//build query object
		DBObject qdoc = new BasicDBObject();
		qdoc.put("id", id);
		
		collection.remove(qdoc);
		logger.debug("remove the user {} info from the mongo", id);
	}

	public List<String> allUsers()
	{
		setMongo();
		DBCursor results = collection.find();
		
		List<String> userList = new ArrayList<String>();
		while(results.hasNext())
		{
			userList.add(results.next().get("email").toString());
		}
		
		logger.debug("total user count is {}. return the list", userList.size());
		return userList;
	}
}
