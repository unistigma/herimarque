package net.julnamoo.swm.herimarque.mongo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class InsertHeritage {

	Logger logger = LoggerFactory.getLogger(InsertHeritage.class.getSimpleName());
	
	Mongo mongo;
	DB db;
	DBCollection collection;
	
	String jsonF;
	
	public InsertHeritage(String jsonF)
	{
		this.mongo = MongoConnection.getInstance().getMongo();
		logger.debug("get mongo instance");
		this.jsonF = jsonF;

		if(mongo != null)
		{
			db = mongo.getDB("herimarque");
			collection = db.getCollection("heritage");
			
			logger.info("Complete to connect to mongo, herimarque db, heritage collection");
		}else
		{
			logger.error("Faile to connect to the mongo. Mongo is null");
		}
	}
	
	public void insertAllHeritage() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		if(mongo != null && db != null && collection != null)
		{
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = jsonParser.parse(new FileReader(new File(ClassLoader.getSystemResource(jsonF).getFile()))).getAsJsonArray();
			
			for(JsonElement element : jsonArray)
			{
				DBObject object = (DBObject) JSON.parse(element.toString());
				collection.insert(object);
				logger.debug("Insert {}", element.toString());
			}
			
			logger.info("Complete to insert all heritage from {}, total size is {}", jsonF, jsonArray.size());
		}
	}
}
