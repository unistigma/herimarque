package net.julnamoo.swm.herimarque.resource;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

@Path("/db")
@Component
public class SQLiteResourceImpl implements SQLiteResource {

	@GET
	@Path("/new")
	@Override
	public Response isNewData(String dbVersion) 
	{
		boolean isExist = true;
		Response response = Response.ok().header("exist", isExist).build();
		return response;
	}

	@GET
	@Path("/itsnew")
	@Override
	public Response getNewData(String dbVersion) 
	{
		File file = new File("");
		 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"herimarque_dummy_db.db\"");
		return response.build();
	}

}
