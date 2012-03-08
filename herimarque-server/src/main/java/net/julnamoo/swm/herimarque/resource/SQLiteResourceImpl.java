package net.julnamoo.swm.herimarque.resource;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

@Path("/db")
@Component
public class SQLiteResourceImpl implements SQLiteResource {

	Logger logger = LoggerFactory.getLogger(SQLiteResourceImpl.class.getSimpleName());
	
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadNewDB(@FormDataParam("db") InputStream db)
	{
		Response response = Response.status(Status.OK).build();
		return response;
	}
	
	@GET
	@Path("/itsnew/{version}")
	@Override
	public Response isNewData(@PathParam("version") String dbVersion) 
	{
		boolean isUpdated = false;
		Response response = null;
		
		if(isUpdated)
		{
			response = Response.status(Status.OK).build();
			logger.debug("isNewData, return 200");
		}else
		{
			response = Response.status(Status.NO_CONTENT).build();
			logger.debug("isNewData, return 204");
		}
		
		return response;
	}

	@GET
	@Path("/new/{version}")
	@Produces( {MediaType.APPLICATION_OCTET_STREAM} )
	@Override
	public Response getNewData(@PathParam("version") String dbVersion) 
	{
		//need to find the file from the resource
		File file = new File("herimarque_dummy_db.db");
		
		logger.debug("getNewData, pass {}", file.getName());
		
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"herimarque_dummy_db.db\"");
		return response.build();
	}

}
