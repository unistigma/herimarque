package net.julnamoo.swm.herimarque.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.model.MapInfo;
import net.julnamoo.swm.herimarque.service.ContentService;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * 
 * @author Julie_air
 *
 */

@Path("/c")
@Component
public class ContentResourceImpl implements ContentResource {

	Logger logger = LoggerFactory.getLogger(ContentResourceImpl.class);

	@Autowired
	private ContentService contentService;

	@POST
	@Path("upload/map/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMap(@Context HttpServletRequest request, @PathParam("id")String user)
	{
		try 
		{
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) 
		{
			e1.printStackTrace();
		}
		
		if(ServletFileUpload.isMultipartContent(request))
		{
			String repo = PropertiesUtil.getValueFromProperties("herimarque.properties", "mapsRepo");
			StringBuilder sb = new StringBuilder();
			repo = sb.append(repo).append(File.separatorChar).append(user).append(File.separatorChar).toString();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(512*512);
			List<FileItem> items = null;
			
			try 
			{
				items = upload.parseRequest(request);
				logger.debug("Total img count is {}", items.size());
			} catch (FileUploadException e) 
			{
				e.printStackTrace();
			}
			
			if(items == null)
			{
				logger.debug("Fail to receive multiple files, return 400");
				return Response.status(Status.BAD_REQUEST).build();
			}
			
			//for saving meta infos
			String mapPath = null;
			
			for(FileItem item : items)
			{
				logger.error("field name is {}, contentType is {}",item.getFieldName(), item.getContentType());
				if(!item.isFormField() && item.getSize() > 0)
				{
					String fileName = processFileName(item.getName());
					String dirPath = null;
					String fpath = null;
					StringBuilder pathBuilder = new StringBuilder(repo);

					//build map path or img paths
					if(item.getFieldName().contains("map"))
					{
						dirPath = pathBuilder.append("maps")
								.append(File.separatorChar).toString();
						//create directories
						File dir = new File(dirPath);
						if(!dir.exists()) dir.mkdirs();
						//set up final path
						fpath = pathBuilder.append(fileName).toString();
						
						//set mapPath
						mapPath = fpath;
					}else
					{
						dirPath = pathBuilder.append("imgs")
								.append(File.separatorChar).toString();
						//create directories
						File dir = new File(dirPath);
						if(!dir.exists()) dir.mkdirs();
						//set up final path
						fpath = pathBuilder.append(fileName).toString();
					}
					logger.error("save the file {}", fpath);
					try 
					{
						File target =new File(fpath); 
						
						//save the file
						item.write(target);
						
					} catch (Exception e) 
					{
						logger.error("Fail to receive file {}", fpath);
						e.printStackTrace();
					}
				}else if(item.getFieldName().equals("mapInfo"))
				{
					String msg;
					try 
					{
						msg = item.getString("UTF-8");
						logger.debug("parse the json {}", msg);
						
						MapInfo mapInfo = new Gson().fromJson(msg, MapInfo.class);
						mapInfo.setFilePath(mapPath);
						
					} catch (UnsupportedEncodingException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			logger.debug("Finish to save images, return 200");
			return Response.ok().build();
		}
		
		logger.debug("the Request is not multipart/formdata, return 400");
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	private String processFileName(String fname)
	{
		String target = fname.substring(fname.lastIndexOf("\\")+1, fname.length());
		return target;
	}
	
	private void resizeImage(File imgFile) throws IOException
	{
		BufferedImage bi = ImageIO.read(imgFile);
		int height = bi.getHeight();
		int width = bi.getWidth();
		
		int newWidth = 0;
		int newHeight = 0;
		double ratioToResize = 0;
		boolean resize = false;
		if(width > 512 || height > 512)
		{
			resize = true;
			if(width > height)
			{
				ratioToResize = Double.parseDouble(String.valueOf("512"))/Double.parseDouble(String.valueOf(height));
			}else
			{
				ratioToResize = Double.parseDouble("512")/Double.parseDouble(String.valueOf(width));
			}
			
			newWidth = width * Integer.valueOf(String.valueOf(ratioToResize));
			newHeight = height *  Integer.valueOf(String.valueOf(ratioToResize));
		}else
		{
			newWidth = width;
			newHeight = height;
		}
		
		
	}

	@GET
	@Path("maps/user/{id}")
	@Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
	@Override
	public Response getUserMapList(
			@DefaultValue("testId") @PathParam("id") String key) 
	{
		logger.debug("start handling getMyMapList with user {}", key);

		//get path list of images
		String msg = contentService.getUserMapList(key);
		
		Response response = Response.ok().entity(msg).build();
		logger.info("user map retrieve, return 200");

		return response;
	}

	@GET
	@Path("maps/loc/{ctrdCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getLocationMapList(
			@HeaderParam("user") String user,
			@DefaultValue("11") @PathParam("ctrdCd") String ctrdCd)
	{
		logger.debug("start handling getLocationMapList of {} ", ctrdCd);

		//get path list of images
		String msg = contentService.getLocationMapList(ctrdCd);
		Response response = Response.ok().entity(msg).build();
		logger.info("location map retrieve, return 200");

		return response;
	}

	@GET
	@Path("maps/period")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Override
	public Response getMapsInPeriod(
			@HeaderParam("user")String user,
			@FormParam("start") String start,
			@FormParam("end") String end) 
	{
		logger.debug("handling request get maps in period from {} to {}", start, end);
		String msg = contentService.getMapsInPeriod(user, start, end);
		if(msg == null)
		{
			logger.debug("Fail to load the map info with period, return 400");
			return Response.status(Status.BAD_REQUEST).build();
		}else
		{
			logger.debug("Success to get map list with perioid, return 200");
			return Response.ok(msg).build();
		}
	}

	@GET
	@Path("maps/hot")
	@Override
	public Response mostHitMaps(@HeaderParam("user") String user) 
	{
		logger.debug("handling get most hit maps request");
		String msg = contentService.getMostHitMaps(user);
		if(msg == null)
		{
			logger.debug("Cannot get most hit maps, return 400");
			return Response.status(Status.BAD_REQUEST).build();
		}else
		{
			logger.debug("Sucess to retrieve the most hit maps info list, return 200");
			return Response.ok(msg).build();
		}
	}
	
	@POST
	@Path("u/{map}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addComment(
			@HeaderParam("user") String user,
			@PathParam("map") String map,
			@FormParam("content") String msg)
	{
		logger.debug("adding comment to {} with {}", map, user);
		//add the comment
		boolean result = contentService.addComment(user, map, msg);
		if(result)
		{
			logger.info("adding new comment to {}, return 200", map);
			return Response.ok().build();
		}else
		{
			logger.info("fail to add new comment to {}, return 400", map);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("maps/{id}")
	@Override
	public Response likeMap(
			@DefaultValue("testId") @PathParam("id") String id, 
			@QueryParam("map") String mapId) 
	{
		String result = contentService.likeMap(id, mapId);
		if(result != null)
		{
			logger.debug("Success add count to map {}, return 200", mapId);
			
			return Response.ok(result).build();
		}else
		{
			logger.debug("Fail to add like to map {}, retrun 400", mapId);
			
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("maps/{id}")
	public Response unlikeMap(
			@PathParam("id")String id,
			@QueryParam("map") String mapKey)
	{
		boolean result = contentService.unlikeMap(id, mapKey);
		if(result)
		{
			logger.debug("Success remove the user {} from likes list of the map {}, return 200", id, mapKey);
			return Response.ok().build();
		}else
		{
			logger.debug("Fail to remove the user {} from likes list of the map {}, return 400", id, mapKey);
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
