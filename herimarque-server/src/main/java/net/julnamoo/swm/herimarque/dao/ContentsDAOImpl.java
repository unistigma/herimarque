package net.julnamoo.swm.herimarque.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.MapInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
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
public class ContentsDAOImpl extends SimpleHerimarqueDAO {

	Logger logger = LoggerFactory.getLogger(ContentsDAOImpl.class);

	public ContentsDAOImpl(){}

	@PostConstruct
	public void setUp()
	{
		collectionName = "data";
	}

	public void addMapInfo(MapInfo mapInfo)
	{
		setMongo();

		//check authentication of the user
		if(isAuthedUser(mapInfo.getUser()))
		{
			DBObject doc = (DBObject) JSON.parse(new Gson().toJson(mapInfo));
			collection.insert(doc);
		}
	}

	public List<String> getUsersMapList(String id)
	{
		setMongo();

		List<String> resultList = new ArrayList<String>();
		
		return resultList;
	}
	
	private boolean isAuthedUser(String id)
	{
		logger.debug("check authentication of the {}", id);
		setMongo();
		
		boolean result = false;
		
		DBObject checkUser = new BasicDBObject();
		checkUser.put("id", id);
		
		DBCursor checkUserResults = collection.find(checkUser);
		if(checkUserResults.count() > 0)
		{
			DBObject user = checkUserResults.next();
			result = user.get("auth").equals("true");
		}else result = false;
		
		logger.debug("return the autentication of the user, {}", result);
		return result;
	}
}
