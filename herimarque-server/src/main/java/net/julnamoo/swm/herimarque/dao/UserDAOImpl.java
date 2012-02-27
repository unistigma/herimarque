package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

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
	
	@Override
	public String addUser(String email, String key, boolean auth) 
	{
		setMongo();

		//Set authorization value to verify on going or done.
		DBObject doc = new BasicDBObject();
		doc.put("email", email);
		doc.put("finalKey", key);
		doc.put("auth", auth);

		//insert or update
		String _id = null;
		if(auth)
		{
			DBObject qdoc = new BasicDBObject();
			qdoc.put("email", email);
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
	public String getUserKey(String email) 
	{
		setMongo();

		DBObject doc = new BasicDBObject();
		doc.put("email", email);

		logger.info("Retrive key of {}", email);
		return collection.findOne(doc).get("_id").toString();
	}

	@Override
	public String changeInfo(String email, String newKey) 
	{
		setMongo();

		//find the target object
		DBObject doc = new BasicDBObject();
		doc.put("email", email);
		doc = collection.findOne(doc);
		//set the new key
		doc.put("finalKey", newKey);
		collection.save(doc);

		logger.debug("change Info of {} with {}", email, newKey);
		return doc.get("_id").toString();
	}

	@Override
	public void delUser(String email) 
	{
		setMongo();
		
		//build query object
		DBObject qdoc = new BasicDBObject();
		qdoc.put("email", email);
		
		DBCursor results = collection.find(qdoc);
		logger.info("Find {} users with {}, all of them are removed", results.size(), email);
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
