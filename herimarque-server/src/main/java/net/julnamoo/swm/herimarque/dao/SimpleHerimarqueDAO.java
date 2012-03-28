package net.julnamoo.swm.herimarque.dao;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public abstract class SimpleHerimarqueDAO {

	Logger logger = LoggerFactory.getLogger(SimpleHerimarqueDAO.class);
	
	protected String dbName = "herimarque";
	protected String collectionName = null;
	
	@Autowired
	Mongo mongo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	protected DB db = null;
	protected DBCollection collection = null;
	
	/**
	 * setter for collection name for each service DAO
	 */
	@PostConstruct
	public abstract void setCollectionName();
	
	/**
	 * It must be called before performing any mongo process.
	 * This method is designed for more convenience manage of mongo client pool
	 */
	protected void setMongo()
	{
		logger.debug("set mongo connection");
		db = mongo.getDB(dbName);
		if(collectionName != null)
		{
			collection = db.getCollection(collectionName);
		}else throw new IllegalStateException("Please set the collectionName");
	}
	
	/**
	 * check the authentication of the user with the id
	 * @param id
	 * @return
	 */
	protected boolean isAuthedUser(String id)
	{
		logger.debug("check authentication of the {}", id);

		MongoTemplate mt = new MongoTemplate(mongo, dbName);
	
		Query q = new Query();
		q.addCriteria(new Criteria("user").is(id));
		
		User expUser = mt.findOne(q, User.class, "user");
		boolean result = expUser == null ? false : expUser.isAuth();
		
		logger.debug("return the autentication of the {}, {}", id, result);
		return result;
	}
}
