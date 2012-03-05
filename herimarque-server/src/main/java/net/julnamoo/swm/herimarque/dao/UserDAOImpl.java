package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.User;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
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
		collectionName = "user";
	}
	
	@Override
	public String addUser(String id, String key, boolean auth) 
	{
		setMongo();
		
		//find there is non-authenticated user in mongo
		DBObject doc = new BasicDBObject();
		doc.put("user", id);
		if(auth)
		{
			doc = collection.findOne(doc);
		}
		
		doc.put("auth", auth);
		doc.put("finalKey", key);
		collection.save(doc);

		logger.debug("exec save with a object, {}", doc);
		return key;//doc.get("_id").toString();
	}

	@Override
	public String getUserKey(String id) 
	{
		setMongo();

		BasicDBObject doc = new BasicDBObject();
		doc.put("user", id);

		logger.info("Retrive key of {}", id);
		doc = (BasicDBObject) collection.findOne(doc);
		
		if(doc == null) return null;
		String key = doc.containsField("finalKey") ? doc.getString("finalKey") : null; 
		return key;
	}

	@Override
	public String changeInfo(String id, String newKey) 
	{
		setMongo();

		//find the target object
		DBObject doc = new BasicDBObject();
		doc.put("user", id);
		doc = collection.findOne(doc);
		//set the new key
		doc.put("finalKey", newKey);
		collection.save(doc);

		logger.debug("change Info of {} with {}", id, newKey);
		return newKey;
	}

	@Override
	public void delUser(String id) 
	{
		setMongo();
		
		//build query object
		DBObject qdoc = new BasicDBObject();
		qdoc.put("user", id);
		
		collection.remove(qdoc);
		logger.debug("remove the user {} info from the mongo", id);
	}

	public List<User> allUsers()
	{
//		setMongo();
		MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
		List<User> allUsers = mt.findAll(User.class);
		logger.debug("total user count is {}. return the list", allUsers.size());
		return allUsers;
	}
}
