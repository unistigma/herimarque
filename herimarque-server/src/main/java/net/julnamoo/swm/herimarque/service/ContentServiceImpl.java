package net.julnamoo.swm.herimarque.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.ContentsDAO;
import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.util.HerimarqueEncryptor;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.apache.commons.fileupload.FileItem;
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
	public String uploadMap(List<FileItem> items) 
	{
		//set mapInfo instance
		FileItem mapItem = getParam(items, "mapInfo");
		String mapInfoStr = "";
		try 
		{
			mapInfoStr = mapItem.getString("UTF-8");
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		logger.debug("save the mapInfo json string:" + mapInfoStr);
		MapInfo mapInfo = new Gson().fromJson(mapInfoStr, MapInfo.class);

		//set Userdir path
		String userDir = new StringBuilder(PropertiesUtil.getValueFromProperties("herimarque.properties", "dataRepo"))
		.append(File.separatorChar).append(mapInfo.getUser()).toString();
		String filePath = userDir;
		String title = mapInfo.getTitle().replace(" ", "");
		String mapKey;
		StringBuilder pathBuilder = new StringBuilder(userDir);

		/** save the map img **/
		//file path: /herimarque/data/{user}/maps/{title}/map.png
		FileItem mapImg = getParam(items, "map");
		if(mapImg.isFormField() || mapImg.getSize() == 0)
		{
			logger.warn("captured map image is null");
		}else
		{
			filePath = pathBuilder.append(File.separatorChar).append("maps").append(File.separatorChar)
					.append(title).toString();
			File dir = new File(filePath);
			if(!dir.exists()) dir.mkdirs();
			filePath = pathBuilder.append(File.separatorChar).append("map.png").toString();
			
			//generate the mapKey and set to the instance
			mapKey = HerimarqueEncryptor.encryption(filePath);
			mapInfo.setMapKey(mapKey);
			 
			//save the map img
			try 
			{
				File target = new File(filePath);
				mapImg.write(target);
				logger.info("save the map image @" + filePath);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		/** save other images **/
		List<FileItem> photos = getParams(items, "image");
		for(FileItem photo : photos)
		{
			if(photo.isFormField() || photo.getSize() == 0)
			{
				logger.warn("cannot save the photo " + photo.getName());
				continue;
			}else
			{
				//file path: /herimarque/data/{user}/imgs/{title}/{timestamp}.png
				pathBuilder = new StringBuilder(userDir);
				String dirPath = pathBuilder.append(File.separatorChar).append("imgs").append(File.separatorChar).append(title).toString();
				File dir = new File(dirPath);
				if(!dir.exists()) dir.mkdirs();
				//set file name
				pathBuilder = new StringBuilder(dirPath).append(File.separatorChar).append(photo.getName());
				filePath = pathBuilder.toString();

				try 
				{
					File target = new File(filePath);
					mapImg.write(target);
					logger.info("save the photo image @" + filePath);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}

		//set other meta information
		mapInfo.setUploadTime(DateFormat.getInstance().format(new Date()));
		mapInfo.setLikeCount(0);

		//save other information of the map to the mongo
		mapKey = contentsDAO.addMapInfo(mapInfo);
		mapInfo.setMapKey(mapKey);		

		//if fail to save the mapinfo, remove the files
		if(mapKey == null)
		{
			String path = new StringBuilder(userDir).append(File.separatorChar).append("maps").append(File.separatorChar).append(title).toString();
			File dir = new File(path);
			if(dir.exists())
			{
				dir.delete();
				logger.info("delete the file(or directory) " + dir.getAbsolutePath());
			}
			path = path.replace("maps", "imgs");
			dir = new File(path);
			if(dir.exists())
			{
				dir.delete();
				logger.info("delete the file(or directory) " + dir.getAbsolutePath());
			}

			return null;
		}else return mapKey;
	}

	private FileItem getParam(List<FileItem> items, String name)
	{
		for(FileItem item : items)
		{
			if(item.getFieldName().equals(name)) return item;
		}
		return null;
	}

	private List<FileItem> getParams(List<FileItem> items, String name)
	{
		List<FileItem> targets = new ArrayList<FileItem>();
		for(FileItem item : items)
		{
			if(item.getFieldName().contains("image"))
			{
				targets.add(item);
			}
		}
		return targets;
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
		logger.debug("getUserMapList:" + id +", return json {}", msg);
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
