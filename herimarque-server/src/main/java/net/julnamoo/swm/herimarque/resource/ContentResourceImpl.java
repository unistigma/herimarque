package net.julnamoo.swm.herimarque.resource;

import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	@Path("/d/loc")
	@Override
	public Response getLocationMapList(String ctrdCd) 
	{
		//new Object will be changed with Image url list
		ArrayList<String> imgs = new ArrayList<String>();
		imgs.add("url1");
		imgs.add("url2");

		Response response = Response.ok().entity(imgs).build();

		return response;
	}

	@GET
	@Path("/d/kind")
	@Override
	public Response getKindMapList(String itemCd) 
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
	@Override
	public Response addComment(String mapKey, String comment) 
	{
		// TODO Auto-generated method stub
		return Response.ok().build();
	}

}
