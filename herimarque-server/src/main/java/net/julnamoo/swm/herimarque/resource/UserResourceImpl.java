package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/u")
@Component
public class UserResourceImpl implements UserResource {

	Logger logger = LoggerFactory.getLogger(UserResourceImpl.class.getSimpleName());
	
	@Autowired
	private ConstantsBean constants;
	
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
	
	@GET
	@Override
	public Response oauthUser(@HeaderParam("key") String key) 
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

	@PUT
	@Override
	public Response changeKey(@HeaderParam("key") String key, @HeaderParam("pwd") String pwd) 
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
