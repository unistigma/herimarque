package net.julnamoo.swm.herimarque.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class SimpleHerimarqueDAO {

	Logger logger = LoggerFactory.getLogger(SimpleHerimarqueDAO.class);
	
	@Autowired
	Mongo mongo;

	protected DB db = null;
	protected DBCollection collection = null;
	
	/**
	 * It must be called before performing any mongo process.
	 * This method is designed for more convenience manage of mongo client pool
	 */
	protected void setMongo()
	{
		logger.debug("set mongo connection");
		db = mongo.getDB("herimarque");
		collection = db.getCollection("users");
	}
}
