package net.julnamoo.swm.herimarque.resource;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.springframework.stereotype.Component;

@Path("/c")
@Component
public class ContentResourceImpl implements ContentResource {

	@Resource
	private ConstantsBean constants;

	@POST
	@Path("/upload")
	@Override
	public Response uploadMap(String key) 
	{
		//service for get the map and store it.
		Response response = Response.ok(key).build();
		return response;
	}

	@POST
	@Path("/upload/test")
	@Produces("text/plain")
	@Override
	public Response uploadMap(@Context HttpServletRequest request) 
	{
		String status = "fail";
		Response response = Response.status(Status.OK).build();
		return response;
	}

	@GET
	@Path("/d/my")
	@Override
	public Response getMyMapList(String key) 
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
	public Response getTheOtehrMapList(String email) 
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

	@GET
	@Path("/u/comment")
	@Override
	public Response addComment(String mapKey, String comment) 
	{
		// TODO Auto-generated method stub
		return Response.ok().build();
	}

}
