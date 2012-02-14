package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

public interface SQLiteResource {

	public Response isNewData(String dbVersion);
	public Response getNewData(String dbVersion);
}