package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.service.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Path("/u")
@Component
public class UserResourceImpl implements UserResource {

	Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);
	
	@Autowired(required=false)
	UserServiceImpl userServiceImpl;
	
	/**
	 * Enrolling the user
	 */
	@POST
	@Override
	public Response addUser(@HeaderParam("email") String email, @HeaderParam("pwd") String pwd) 
	{
		logger.info("start handling addUser, {}:{}", email, pwd);
		
		String key = userServiceImpl.addUser(email, pwd);
		Response response = Response.status(Status.CREATED).header("key",key).build();
		
		logger.debug("addUser, return 201");
		return response;
	}

	/**
	 * Delete the user
	 */
	@DELETE
	@Override
	public Response delUser(@QueryParam("email") String email) 
	{
		logger.info("start hanndling delUser");
		
		userServiceImpl.delUser(email);
		Response response = null;
		
		response = Response.ok().build();
		logger.debug("delUser, return 200");
		return response;
	}
	
	/**
	 * Authenticate the user from email link
	 */
	@GET
	@Path("{key}")
	@Override
	public Response oauthUser(@PathParam("key") String key, @QueryParam("email") String email) 
	{
		logger.info("start handling oauthUser");
		
		Response response = null;
		boolean Authentication = userServiceImpl.authUser(email, key);
		
		if(Authentication)
		{
			response = Response.status(Status.ACCEPTED).build();
			logger.debug("return 202");
		}/*else
		{
			response = Response.status(Status.UNAUTHORIZED).build();
			logger.debug("return 401");
		}*/
		
		return response;
	}

	/**
	 * Change the user info
	 */
	@PUT
	@Override
	public Response changeUserInfo(@HeaderParam("email") String email, @HeaderParam("pwd") String pwd) 
	{
		logger.info("start handling changeKey");
		
		Response response = null;
		boolean changed = userServiceImpl.changeUserInfo(email, pwd);
		
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

	@GET
	@Path("admin/all")
	public Response allUsers()
	{
		logger.info("request information of all users");
		
		Response response = null;
		String msg = new Gson().toJson(userServiceImpl.allUsers()).toString();
		return response.ok().entity(msg).build();
	}
}
