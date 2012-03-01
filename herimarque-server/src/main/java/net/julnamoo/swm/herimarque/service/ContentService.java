package net.julnamoo.swm.herimarque.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.ContentsDAOImpl;
import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.eclipse.jetty.server.handler.ResourceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ContentService {

	Logger logger = LoggerFactory.getLogger(ContentService.class);
	
	@Resource(name="contentsDAO")
	ContentsDAOImpl contentsDAO;
	
	@Resource(name="userDAO")
	UserDAOImpl userDAO;
	
	public void uploadMap(InputStream uploadedInputStream, MapInfo mapInfo)
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		mapInfo.setUploadTime(sdf.format(new Date()));
		//save other information of the map to the mongo
		contentsDAO.addMapInfo(mapInfo);
	}
	
	public String getMyMapList(String id)
	{
		logger.debug("request user map list to contents dao");
		List<MapInfo> myMapList = contentsDAO.getUsersMapList(id);
		
		String msg = new Gson().toJson(myMapList);
		logger.debug("getMyMapList, return json {}", msg);
		return msg;
	}
	
	public String getOtherMapList(String id)
	{
		logger.debug("request user {} map list from another user", id);
		List<MapInfo> otherMapList = contentsDAO.getUsersMapList(id);
		
		String msg = new Gson().toJson(otherMapList);
		logger.debug("getOtherMapList, return json {}", msg);
		return msg;
	}
	
	public String getLocationMapList(String ctrdCd)
	{
		logger.debug("request maps of the location:{}", ctrdCd);
		
		List<MapInfo> mapList = contentsDAO.getLocationMapList(ctrdCd);
		
		String msg = new Gson().toJson(mapList);
		logger.debug("getLocationMapList, return json {}", msg);
		return msg;
	}
	
	public String getKindMapList(String itemCd)
	{
		logger.debug("request maps of the kind:{}", itemCd);
		
		List<MapInfo> mapList = contentsDAO.getKindMapList(itemCd);
		
		String msg = new Gson().toJson(mapList);
		logger.debug("getKindMapList, return json {}", msg);
		return msg;
	}
	
}
