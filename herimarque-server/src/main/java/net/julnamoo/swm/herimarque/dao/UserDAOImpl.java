package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
	public void setup()
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
			_id = null;
		}else
		{
			collection.save(doc);
			_id = doc.get("_id").toString();
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
		if(cursor.size() > 0)
		{
			return null;
		}else
		{
			DBObject resultDoc = cursor.next();
			return cursor.next().get("finalKey").toString();
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

		logger.debug("change Info of {} with {}", id, newKey);
		return doc.get("_id").toString();
	}

	@Override
	public void delUser(String id) 
	{
		setMongo();
		
		//build query object
		DBObject qdoc = new BasicDBObject();
		qdoc.put("id", id);
		
		DBCursor results = collection.find(qdoc);
		logger.info("Find {} users with {}, all of them are removed", results.size(), id);
		while(results.hasNext())
		{
			DBObject target = results.next();
			collection.remove(target);
			logger.debug("remove {}", target);
		}
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
