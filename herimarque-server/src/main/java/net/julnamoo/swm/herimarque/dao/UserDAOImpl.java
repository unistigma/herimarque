package net.julnamoo.swm.herimarque.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
public class UserDAOImpl implements UserDAO
{
	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	Mongo mongo;

	private DB db = null;
	private DBCollection collection = null;

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
		collection.save(doc); 

		logger.debug("exec save with a object, {}", doc);
		return (String) doc.get("_id");
	}

	@Override
	public String getUserKey(String email) 
	{
		setMongo();

		DBObject doc = new BasicDBObject();
		doc.put("email", email);

		logger.info("Retrive key of {}", email);
		return (String) collection.findOne(doc).get("_id");
	}

	@Override
	public String changeInfo(String email, String newKey) 
	{
		setMongo();

		//build target object
		DBObject doc = new BasicDBObject();
		doc.put("email", email);
		doc.put("finalKey", newKey);
		doc.put("auth", true);
		
		//build query object
		DBObject qdoc = new BasicDBObject();
		qdoc.put("email", email);
		
		collection.update(qdoc, doc);
		logger.debug("change Info of {} with {}", email, newKey);
		return (String) doc.get("_id");
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

	private void setMongo()
	{
		logger.debug("set mongo connection");
		db = mongo.getDB("herimarque");
		collection = db.getCollection("users");
	}
}
