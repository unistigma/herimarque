package net.julnamoo.swm.herimarque.resource;

import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.service.ContentService;
import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * 
 * @author Julie_air
 *
 */

@Path("/c")
@Component
public class ContentResourceImpl implements ContentResource {

	Logger logger = LoggerFactory.getLogger(ContentResourceImpl.class.getSimpleName());
	
	@Resource
	private ConstantsBean constants;
	
	@Autowired
	private ContentService contentService;

	@POST
	@Path("/upload")
	public Response uploadMap(@FormParam("file") InputStream uploadedInputStream,
			@FormParam("file") FormDataContentDisposition fileDeatil) 
	{
		String fpath = constants.getMapsRepo() + fileDeatil.getFileName();
		logger.debug("start handling uploadmap with {} file path", fpath);
		
		contentService.uploadMap(uploadedInputStream, fpath);
		
		//add the file path to the mongo and get the id. It will be returned with response
		String mapKey = "tempkey";
		logger.info("{} map key : {}, return 200", fpath, mapKey);
		
		return Response.status(Status.OK).entity(mapKey).build();
	}
	
	@GET
	@Path("/d/my")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getMyMapList(@HeaderParam("key") String key) 
	{
		logger.debug("start handling getMyMapList with user {}", key);
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");
		
		logger.debug("total {} map list size is {}", key, imgs.size());
		Response response = Response.ok().entity(imgs).build();
		logger.info("user map retrieve, return 200");
		
		return response;
	}

	@GET
	@Path("/d/other")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getTheOtehrMapList(@HeaderParam("email") String email) 
	{
		logger.debug("start handling getTheOtehrMapList of {} user", email);
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");
		logger.debug("total {} map list size is {}", email, imgs.size());
		
		Response response = Response.ok().entity(imgs).build();
		logger.info("other user map retrieve, return 200");
		
		return response;
	}

	@GET
	@Path("/d/loc/{ctrdCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getLocationMapList(@PathParam("ctrdCd") String ctrdCd) 
	{
		logger.debug("start handling getLocationMapList of {} ", ctrdCd);
		
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");
		logger.debug("total {} map list size is {}", ctrdCd, imgs.size());
		
		Response response = Response.ok().entity(imgs).build();
		logger.info("location map retrieve, return 200");

		return response;
	}

	@GET
	@Path("/d/kind/{itemCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getKindMapList(@PathParam("itemCd") String itemCd) 
	{
		logger.debug("start handling getKindMapList of {} ", itemCd);
		
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");
		logger.debug("total {} map list size is {}", itemCd, imgs.size());

		Response response = Response.ok().entity(imgs).build();
		logger.info("kind map retrieve, return 200");
		
		return response;
	}

	@POST
	@Path("/u/comment")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response addComment(Comment comment) 
	{
		logger.debug("adding comment from {} to {}, {}", comment.getUserKey(), comment.getMapKey());
		//add the comment
		
		logger.info("adding new comment to {}, return 200", comment.getMapKey());
		return Response.ok().build();
	}

}
