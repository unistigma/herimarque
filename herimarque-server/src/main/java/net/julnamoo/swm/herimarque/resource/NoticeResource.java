package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

public interface NoticeResource {

	public Response addNotice(String notice);
	public Response isExist(String lastupdate);
	public Response getNewNotice(String lastupdate);
}
