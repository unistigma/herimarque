package net.julnamoo.swm.herimarque.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
		
		String user = mapInfo.getUser();
		userDAO.getUserKey(user);
		
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
		
		Date now = new Date();
		mapInfo.setUploadTime(now);
		//save other information of the map to the mongo
		contentsDAO.addMapInfo(mapInfo);
	}
	
	public List<String> getMyMapList(String id)
	{
		List<String> myMapList = new ArrayList<String>();
		
		
		return myMapList;
	}
}
