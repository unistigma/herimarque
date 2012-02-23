package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Path("/u")
@Component
public class UserResourceImpl implements UserResource {

	Logger logger = LoggerFactory.getLogger(UserResourceImpl.class.getSimpleName());
	
	/**
	 * Enrolling the user
	 */
	@POST
	@Override
	public Response addUser(@HeaderParam("email") String email, @HeaderParam("pwd") String pwd) 
	{
		logger.debug("start handling addUser, {}", email);
		
		String key = "tempKey";
		Response response = Response.status(Status.CREATED).header("key",key).build();
		
		logger.debug("addUser, return 200");
		return response;
	}

	/**
	 * Delete the user
	 */
	@DELETE
	@Override
	public Response delUser(@HeaderParam("key") String key, @HeaderParam("email") String email) 
	{
		logger.debug("start hanndling delUser");
		
		boolean isDeleted = true;
		Response response = null;
		
		if(isDeleted)
		{
			response = Response.ok().build();
			logger.debug("delUser, return 200");
		}else
		{
			response = Response.status(Status.SERVICE_UNAVAILABLE).build();
			logger.debug("delUser, return 503");
		}
		return response;
	}
	
	/**
	 * Authenticate the user from email link
	 */
	@GET
	@Override
	public Response oauthUser(@PathParam("key") String key, @QueryParam("email") String email) 
	{
		logger.debug("start handling oauthUser");
		
		Response response = null;
		boolean Authentication = true;
		
		if(Authentication)
		{
			response = Response.status(Status.ACCEPTED).build();
			logger.debug("return 202");
		}else
		{
			response = Response.status(Status.UNAUTHORIZED).build();
			logger.debug("return 401");
		}
		
		return response;
	}

	/**
	 * Change the user info
	 */
	@PUT
	@Override
	public Response changeUserInfo(@HeaderParam("email") String email, @HeaderParam("pwd") String pwd) 
	{
		logger.debug("start handling changeKey");
		
		Response response = null;
		boolean changed = true;
		
		if(changed)
		{
			response = Response.status(Status.OK).header("key", new String("newkey")).build();
			logger.debug("return 200");
		}else
		{
			response = Response.status(Status.NOT_ACCEPTABLE).build();
			logger.debug("return 406");
		}
		return response;
	}

}
