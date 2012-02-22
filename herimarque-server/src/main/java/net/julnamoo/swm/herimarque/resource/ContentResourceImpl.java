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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/c")
@Component
public class ContentResourceImpl implements ContentResource {

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
		contentService.uploadMap(uploadedInputStream, fpath);
		//add the file path to the mongo and get the id. It will be returned with response
		String mapKey = "tempkey";
		
		return Response.status(Status.OK).entity(mapKey).build();
	}
	
	@GET
	@Path("/d/my")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getMyMapList(@HeaderParam("key") String key) 
	{
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");

		Response response = Response.ok().entity(imgs).build();

		return response;
	}

	@GET
	@Path("/d/other")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getTheOtehrMapList(@HeaderParam("email") String email) 
	{
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");

		Response response = Response.ok().entity(imgs).build();

		return response;
	}

	@GET
	@Path("/d/loc/{ctrdCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getLocationMapList(@PathParam("ctrdCd") String ctrdCd) 
	{
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");

		Response response = Response.ok().entity(imgs).build();

		return response;
	}

	@GET
	@Path("/d/kind/{itemCd}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response getKindMapList(@PathParam("itemCd") String itemCd) 
	{
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");

		Response response = Response.ok().entity(imgs).build();

		return response;
	}

	@POST
	@Path("/u/comment")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public Response addComment(Comment comment) 
	{
		//add the comment
		return Response.ok().build();
	}

}
