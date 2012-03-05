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
import net.julnamoo.swm.herimarque.model.Location;
import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.eclipse.jetty.util.security.Credential.MD5;
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
	public String uploadMap(InputStream uploadedInputStream, String fname,
			String id, String otherInfo) 
	{
		logger.debug("Start to write {} with {}", fname, otherInfo);
		
		//build map file path
		String repo = PropertiesUtil.getValueFromProperties("herimarque.properties", "mapsRepo");
		StringBuilder sb = new StringBuilder();
		sb.append(repo).append(File.separatorChar).append(id);
		sb.append(File.separatorChar).append("maps");
		String dirPath = sb.toString();
		
		File userDir = new File(dirPath);
		
		if(!userDir.exists())
		{
			userDir.mkdirs();
		}
		
		//CAN BE PROCESSED with THREAD for taking SHORTER TIME for saving the image
		String mapPath = sb.append(File.separatorChar).append(fname).toString();
		logger.debug("start save the file {}", mapPath);
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
		
		String mapKey = MD5.digest(mapPath);
		
		MapInfo mapInfo = new MapInfo();
		mapInfo.setUploadTime(DateFormat.getInstance().format(new Date()));
		mapInfo.setUser(id);
		mapInfo.setFilePath(mapPath);
		mapInfo.setLikeCount(0);
		mapInfo.setMapKey(mapKey);
		
		MapInfo temp = new Gson().fromJson(otherInfo, MapInfo.class);
		logger.debug("temp MapInfo with json, 0th area : {}, 0th location : {}", temp.getArea().get(0), temp.getLogging().get(0));
		
		mapInfo.setArea(temp.getArea());
		mapInfo.setLogging(temp.getLogging());
		temp = null;
		//save other information of the map to the mongo
		mapKey = contentsDAO.addMapInfo(mapInfo);
		//fail to save the mapinfo
		if(mapKey == null)
		{
			File f = new File(mapPath);
			f.delete();
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
	public boolean addComment(String comment) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String mostHitMaps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMapsInPeriod(String perioid) {
		// TODO Auto-generated method stub
		return null;
	}
}
