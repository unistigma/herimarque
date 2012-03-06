package net.julnamoo.swm.herimarque.resource;

import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import net.julnamoo.swm.herimarque.service.ContentService;

import org.eclipse.jetty.util.MultiPartOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.multipart.MultiPartConfig;
import com.sun.jersey.multipart.MultiPartMediaTypes;

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

//	@POST
//	@Path("upload/map")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMap(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDeatil,
			@FormDataParam("mapInfo") String mapInfo) 
	{
		String fname = fileDeatil.getFileName();
		logger.debug("Start upload with {} for {}",mapInfo, fname);
		String mapKey = contentService.uploadMap(uploadedInputStream, fname, mapInfo);
		logger.debug("returing generated map key {}", mapKey);
		
		if(mapKey == null)
		{
			return Response.status(Status.BAD_REQUEST).build();
		}else
		{
			return Response.status(Status.OK).entity(mapKey).build();
		}
	}

	@POST
	@Path("upload/map")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMap(@Context HttpServletRequest request)
	{
		
		return null;
	}
	@POST
	@Path("upload/img/{map}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Override
	public Response uploadImg(@HeaderParam("user") String user,
			@PathParam("map") String mapKey,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail)
	{
		return null;
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
			logger.debug("Fail to load the map info with period, return 503");
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
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
			logger.debug("Cannot get most hit maps, return 503");
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
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
			logger.debug("Fail to add like to map {}, retrun 503", mapId);
			
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
	}
	
}
