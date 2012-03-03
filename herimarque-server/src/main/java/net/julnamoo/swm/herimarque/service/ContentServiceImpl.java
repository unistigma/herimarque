package net.julnamoo.swm.herimarque.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.ContentsDAO;
import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

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
	public String uploadMap(InputStream uploadedInputStream, MapInfo mapInfo)
	{
		logger.debug("Start to write {}", mapInfo.getFilePath());
		
		StringBuilder sb = new StringBuilder();
		String repo = PropertiesUtil.getValueFromProperties("herimarque.properties", "mapsRepo");
		sb.append(repo).append(File.separatorChar).append(mapInfo.getUser());
		String dirPath = sb.toString();
		
		File userDir = new File(dirPath);
		
		if(!userDir.exists())
		{
			userDir.mkdirs();
		}
		
		//CAN BE PROCESSED with THREAD for taking SHORTER TIME for saving the image
		String mapPath = mapInfo.getFilePath();
		//It needs downgrade the resolution of the map picture
		try
		{
			OutputStream os = new FileOutputStream(new File(mapPath));
			
			int read = 0;
			int size = 0;

			byte[] buff = new byte[2048];
			
			while((read = uploadedInputStream.read(buff)) > 0)
			{
				size += read;
				os.write(buff, 0, read);
			}
			
			os.flush();
			os.close();
			
			logger.debug("{} >> Total size : {}", mapPath, size);
			logger.info("Complete to save {}", mapPath);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		mapInfo.setUploadTime(DateFormat.getInstance().format(new Date()));
		//save other information of the map to the mongo
		return contentsDAO.addMapInfo(mapInfo);
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#getMyMapList(java.lang.String)
	 */
	@Override
	public String getMyMapList(String id)
	{
		logger.debug("request user map list to contents dao");
		List<MapInfo> myMapList = contentsDAO.getUsersMapList(id);
		
		String msg = new Gson().toJson(myMapList);
		logger.debug("getMyMapList, return json {}", msg);
		return msg;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#getOtherMapList(java.lang.String)
	 */
	@Override
	public String getOtherMapList(String id)
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
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#getKindMapList(java.lang.String)
	 */
	@Override
	public String getKindMapList(String itemCd)
	{
		logger.debug("request maps of the kind:{}", itemCd);
		
		List<MapInfo> mapList = contentsDAO.getKindMapList(itemCd);
		
		String msg = new Gson().toJson(mapList);
		logger.debug("getKindMapList, return json {}", msg);
		return msg;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.ContentService#addComment(net.julnamoo.swm.herimarque.model.Comment)
	 */
	@Override
	public boolean addComment(Comment comment)
	{
		logger.debug("request add comment to the map");
		boolean result = false;
		try 
		{
			result = contentsDAO.addComment(comment);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
