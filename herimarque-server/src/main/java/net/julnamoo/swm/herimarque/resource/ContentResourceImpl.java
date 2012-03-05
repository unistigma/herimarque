package net.julnamoo.swm.herimarque.resource;

import java.io.InputStream;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.service.ContentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadMap(
			@FormDataParam("id") String id,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDeatil,
			@FormDataParam("mapInfo") String mapInfo) 
	{
		
		String fname = fileDeatil.getFileName();
		logger.debug("Start upload with {} for {}",mapInfo, fname);
		String mapKey = contentService.uploadMap(uploadedInputStream, fname, id, mapInfo);
		logger.debug("returing generated map key {}", mapKey);
		
		if(mapKey == null)
		{
			return Response.status(Status.BAD_REQUEST).build();
		}else
		{
			return Response.status(Status.OK).entity(mapKey).build();
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
			String msg) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("maps/hot")
	@Override
	public Response mostHitMaps(@HeaderParam("user") String user) 
	{
		// TODO Auto-generated method stub
		return null;
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
	@Path("maps")
	@Override
	public Response likeMap(
			@DefaultValue("testId") @PathParam("id") String id, 
			@QueryParam("map") String mapId) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
