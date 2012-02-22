package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Path("/u")
@Component
public class UserResourceImpl implements UserResource {

	@POST
	@Path("/add")
	//need to get params by json
	@Override
	public Response addUser(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response oauthUser(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response changeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
