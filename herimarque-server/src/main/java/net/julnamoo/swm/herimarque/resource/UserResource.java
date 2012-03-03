package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

public interface UserResource {

	public Response addUser(String email, String pwd, String id);
	public Response delUser(String id);
	public Response oauthUser(String key, String id);
	public Response changeUserInfo(String id, String pwd, String nPwd); // for change e-mail or password
}
