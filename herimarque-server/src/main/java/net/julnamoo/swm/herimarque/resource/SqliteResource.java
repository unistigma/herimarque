package net.julnamoo.swm.herimarque.resource;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface SqliteResource {

	@POST
	@Path("upload/{version}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public abstract Response uploadNewDB(@Context HttpServletRequest request,
			@PathParam("version") String version,
			@HeaderParam("admin") String admin) throws URISyntaxException;

	@GET
	@Path("/itsnew/{version}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public abstract Response isNewData(@PathParam("version") String version);

	@GET
	@Path("/new/{version}")
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public abstract Response getNewData(@PathParam("version") String dbVersion,
			@HeaderParam("user") String id);

}