package net.julnamoo.swm.herimarque.dao;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.MapInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * 
 * @author Julie_air
 *
 */

@Repository(value="contentsDAO")
public class ContentsDAOImpl extends SimpleHerimarqueDAO {

	Logger logger = LoggerFactory.getLogger(ContentsDAOImpl.class);

	public ContentsDAOImpl(){}
	
	public void addMapInfo(MapInfo mapInfo)
	{
		setMongo();
		
		DBObject doc = (DBObject) JSON.parse(new Gson().toJson(mapInfo));
		collection.insert(doc);
	}
	
	public void getUsersMapList(String id)
	{
		setMongo();
		
		DBObject qdoc = new BasicDBObject();
		qdoc.put("id", id);
	}
}
