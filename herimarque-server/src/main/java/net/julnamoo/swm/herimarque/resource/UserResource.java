package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

public interface UserResource {

	public Response addUser(String key);
	public Response oauthUser(String key);
	public Response changeKey(String key); // for change e-mail or password
}
