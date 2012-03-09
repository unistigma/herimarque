package net.julnamoo.swm.herimarque.service;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.ContentsDAO;
import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.util.HerimarqueEncryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ContentServiceImpl implements ContentService {

	Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
	
	@Resource(name="contentsDAO")
	ContentsDAO contentsDAO;
	
	@Resource(name="userDAO")
	UserDAOImpl userDAO;
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#uploadMap(java.io.InputStream, net.julnamoo.swm.herimarque.model.MapInfo)
	 */
	@Override
	public String uploadMap(MapInfo mapInfo) 
	{
		mapInfo.setUploadTime(DateFormat.getInstance().format(new Date()));
		mapInfo.setLikeCount(0);

		String mapKey = HerimarqueEncryptor.encryption(mapInfo.getFilePath()); 
		mapInfo.setMapKey(mapKey);		

		//save other information of the map to the mongo
		mapKey = contentsDAO.addMapInfo(mapInfo);
		
		//if fail to save the mapinfo, remove the files
		if(mapKey == null)
		{
			File map = new File(mapInfo.getFilePath());
			map.delete();
			
			//remove the imgs cascade
//			for(Location location : locs)
//			{
//				location.getImage();
//			}
			return null;
		}else return mapKey;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#getOtherMapList(java.lang.String)
	 */
	@Override
	public String getUserMapList(String id)
	{
		logger.debug("request user {} map list from another user", id);
		List<MapInfo> otherMapList = contentsDAO.getUsersMapList(id);
		
		String msg = new Gson().toJson(otherMapList);
		logger.debug("getOtherMapList, return json {}", msg);
		return msg;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#getLocationMapList(java.lang.String)
	 */
	@Override
	public String getLocationMapList(String ctrdCd)
	{
		logger.debug("request maps of the location:{}", ctrdCd);
		
		List<MapInfo> mapList = contentsDAO.getLocationMapList(ctrdCd);
		
		String msg = new Gson().toJson(mapList);
		logger.debug("getLocationMapList, return json {}", msg);
		return msg;
	}
	
	@Override
	public boolean addComment(String user, String map, String c) 
	{
		logger.debug("Convert comment information({}, {}) to the comment object from "+user, map, c);
		Comment comment = new Comment();
		comment.setUserKey(user);
		comment.setContent(c);
		comment.setUploadTime(DateFormat.getInstance().format(new Date()));
		
		return contentsDAO.addComment(map, comment);
	}

	@Override
	public String getMostHitMaps(String user) 
	{
		List<MapInfo> mapInfoList = contentsDAO.getMostHitMapList(user);
		logger.debug("get most hit maps, length is {}", mapInfoList.size());
		String json = new Gson().toJson(mapInfoList);
		logger.debug("return most hit maps {}", json);
		return json;
	}

	@Override
	public String getMapsInPeriod(String user, String start, String end) 
	{
		Date startDate, endDate;
		try 
		{
			startDate = DateFormat.getInstance().parse(start);
			endDate = DateFormat.getInstance().parse(end);
		} catch (ParseException e) 
		{
			e.printStackTrace();
			return null;
		}
		List<MapInfo> mapInfoList = contentsDAO.getMapsInPeriod(user, startDate, endDate);
		String json = new Gson().toJson(mapInfoList);
		logger.debug("return json is {} uploaded from {} to " + end, json, start);
		
		return json;
	}
	
	@Override
	public String likeMap(String id, String mapKey) 
	{
		MapInfo mi = contentsDAO.likeMap(id, mapKey);
		if(mi == null)
		{
			logger.debug("the like map operation fail, return null");
			return null;
		}else
		{
			String json = new Gson().toJson(mi);
			logger.debug("likeMap, return {}", json);
			return json;
		}
	}
	
	@Override
	public boolean unlikeMap(String id, String mapKey)
	{
		boolean result = contentsDAO.unlikeMap(id, mapKey);
		
		logger.debug("request delete user {} from like list of the map {} :" + result, id, mapKey);
		return result;
	}
}
