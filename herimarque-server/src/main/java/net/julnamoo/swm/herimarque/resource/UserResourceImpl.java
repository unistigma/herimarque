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
	public Response addUser(@HeaderParam("email") String email, @HeaderParam("pwd") String pwd, @QueryParam("id") String id) 
	{
		logger.info("start handling addUser, {}:{}", email, id);
		logger.debug("Try to add user with {}", pwd);
		
		String key = userServiceImpl.addUser(id, email, pwd);
		
		Response response = null;
		
		if(key == null)
		{
			response = Response.status(Status.BAD_REQUEST).build();
			logger.debug("addUser, return 400");
		}else
		{
			response = Response.status(Status.CREATED).header("key",key).build();
			logger.debug("addUser, return 201");
		}
		
		return response;
	}

	/**
	 * Delete the user
	 */
	@DELETE
	@Override
	public Response delUser(@QueryParam("id") String id) 
	{
		logger.info("start hanndling delUser");
		
		userServiceImpl.delUser(id);
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
	public Response oauthUser(@PathParam("key") String key, @QueryParam("id") String id) 
	{
		logger.info("start handling oauthUser");
		
		Response response = null;
		boolean Authentication = userServiceImpl.authUser(id, key);
		
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
	 * Login
	 * @param id
	 * @param pwd
	 * @return
	 */
	@GET
	@Path("/login/{id}")
	public Response logIn(@PathParam("id")String id, @HeaderParam("pwd") String pwd)
	{
		logger.debug("request log in authentication");
		boolean result = userServiceImpl.logIn(id, pwd);
		
		if(result)
		{
			logger.info("{} is logged in", id);
			return Response.ok().build();
		}else
		{
			logger.info("{} trys to logged in with malformed password", id);
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	/**
	 * Change the user info
	 */
	@PUT
	@Override
	public Response changeUserInfo(@HeaderParam("id") String id, @HeaderParam("pwd") String pwd, @HeaderParam("isNew") String nPwd) 
	{
		logger.info("start handling changeKey");
		
		Response response = null;
		String changed = userServiceImpl.changeUserInfo(id, pwd, nPwd);
		
		if(changed != null)
		{
			response = Response.status(Status.OK).header("key", changed).build();
			logger.debug("change user info, return 200");
		}else
		{
			response = Response.status(Status.NOT_ACCEPTABLE).build();
			logger.debug("change user info, return 406");
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
