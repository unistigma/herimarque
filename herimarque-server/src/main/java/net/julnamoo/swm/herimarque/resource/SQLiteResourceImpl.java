package net.julnamoo.swm.herimarque.resource;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

@Path("/db")
@Component
public class SQLiteResourceImpl implements SQLiteResource {

	@GET
	@Path("/itsnew")
	@Override
	public Response isNewData(String dbVersion) 
	{
		boolean isUpdated = false;
		Response response = null;
		
		if(isUpdated)
		{
			response = Response.status(Status.ACCEPTED).build();
		}else
		{
			response = Response.status(Status.NO_CONTENT).build();
		}
		
		return response;
	}

	@GET
	@Path("/new")
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
