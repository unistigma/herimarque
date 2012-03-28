package net.julnamoo.swm.herimarque.resource;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
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

import net.julnamoo.swm.herimarque.dao.UserDAO;
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

/**
 * 
 * @author Julie_air
 *
 */

@Path("/c")
@Component
public class ContentResourceImpl implements ContentResource {

	Logger logger = LoggerFactory.getLogger(ContentResourceImpl.class);

	private final int WIDTH_THUM = 52;
	
	@Autowired
	private ContentService contentService;

	@Resource(name="userDAO")
	UserDAO userDAO;

	@POST
	@Path("upload/map/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMap(@Context HttpServletRequest request, @PathParam("id")String user)
	{
		/**
		 * return bad request when request sent from unauthenticated user or non multipart request
		 */
		if(!ServletFileUpload.isMultipartContent(request) || !userDAO.isAuthenticated(user))
		{
			logger.debug("the Request is not multipart/formdata or the user is not authenticated, return 400");
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(512*512);
		List<FileItem> items = null;

		try 
		{
			items = upload.parseRequest(request);
			logger.debug("Total params count is {}", items.size());
		} catch (FileUploadException e) 
		{
			e.printStackTrace();
		}

		if(items == null)
		{
			logger.debug("Fail to receive multiple files, return 400");
			return Response.status(Status.BAD_REQUEST).build();
		}

		String mapKey = contentService.uploadMap(items);
		
		return Response.ok(mapKey).build();
	}


	@GET
	@Path("maps/user/{id}")
	@Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML })
	@Override
	public Response getUserMapList(
			@PathParam("id") String key) 
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
			@PathParam("id") String id, 
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
	
	@GET
	@Path("image/{user}/{title}/{fileName}")
	public Response passImage(
			@HeaderParam("user") String user, //request user
			@PathParam("user") String id, //content creator
			@PathParam("title") String title,
			@PathParam("fileName") String fname,
			@HeaderParam("User-Agent") String client)
	{
		if(!userDAO.isAuthenticated(user))
		{
			logger.info("image request from unauthorized user {}", user);
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		//sending different image according to main mobile client
		if(client.contains("Android") || client.contains("android")
				|| client.contains("iPhone") || client.contains("iphone"))
		{
			//with thumbnail
			File origin = new File(getOriginFilePath(user, title, fname));
			try 
			{
				BufferedImage image = getThumbnail(origin);
				String type = "image/" + fname.substring(fname.length()-3);
				logger.info("return full image {} requested from{}", fname, user);
				return Response.ok(image, type).build();
			} catch (IOException e) 
			{
				e.printStackTrace();
				logger.error("cannot handle the request {} from {}", fname, user);
				return Response.status(Status.NO_CONTENT).build();
			}
			
		}else
		{
			//with original
			BufferedImage image;
			try
			{
				image = ImageIO.read(new File(getOriginFilePath(user, title, fname)));
				String type = "image/" + fname.substring(fname.length()-3);
				logger.info("return full image {} requested from{}", fname, user);
				return Response.ok(image, type).build();
			} catch (IOException e) 
			{
				e.printStackTrace();
				logger.error("cannot handle the request {} from {}", fname, user);
				return Response.status(Status.NO_CONTENT).build();
			}
		}
	}
	
	private String getOriginFilePath(String user, String title, String fname)
	{
		String basePath = new StringBuilder(PropertiesUtil.getValueFromProperties("herimarque.properties", "dataRepo"))
		.append(File.separatorChar).append(user).append(File.separatorChar).toString();
		if(fname.contains("map"))
		{
			return new StringBuilder(basePath).append("maps").append(File.separatorChar)
					.append(title).append(File.separatorChar).append(fname).toString(); 
			
		}else
		{
			return new StringBuilder(basePath).append("imgs").append(File.separatorChar)
					.append(title).append(File.separatorChar).append(fname).toString();
		}
	}
	
	private BufferedImage getThumbnail(File imgFile) throws IOException
	{
		BufferedImage origin = ImageIO.read(imgFile);
		int height = origin.getHeight();
		int width = origin.getWidth();

		if(width <= WIDTH_THUM)
		{
			return origin;
		}

		int newHeight = height * WIDTH_THUM / width;
		BufferedImage result = new BufferedImage(WIDTH_THUM, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = result.createGraphics();
		graphic.setComposite(AlphaComposite.Src);
		graphic.drawImage(origin, 0, 0, WIDTH_THUM, newHeight, null);
		graphic.dispose();

		return result;
	}
}
