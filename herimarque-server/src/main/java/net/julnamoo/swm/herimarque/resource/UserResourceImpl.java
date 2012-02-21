package net.julnamoo.swm.herimarque.resource;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.springframework.stereotype.Component;

@Path("/u")
@Component
public class UserResourceImpl implements UserResource {

	@Resource
	private ConstantsBean constants;
	
	@POST
	@Override
	public Response addUser(String email, String pwd) 
	{
		Response response = Response.ok().build();
		return response;
	}

	@DELETE
	@Override
	public Response delUser(String key, String email) 
	{
		boolean isDeleted = true;
		Response response = null;
		
		if(isDeleted)
		{
			response = Response.ok().build();
		}else
		{
			response = Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		return response;
	}
	
	@GET
	@Override
	public Response oauthUser(String key) 
	{
		Response response = null;
		boolean Authentication = true;
		
		if(Authentication)
		{
			response = Response.status(Status.ACCEPTED).build();
		}else
		{
			response = Response.status(Status.UNAUTHORIZED).build();
		}
		
		return response;
	}

	@PUT
	@Override
	public Response changeKey(String key) 
	{
		Response response = null;
		boolean changed = true;
		
		if(changed)
		{
			response = Response.status(Status.OK).build();
		}else
		{
			response = Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return response;
	}

}
