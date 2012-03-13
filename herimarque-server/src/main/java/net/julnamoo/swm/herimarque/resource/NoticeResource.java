package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * 
 * @author Julie_air
 *
 */
public interface NoticeResource {

	/**
	 * add new notice 
	 * @param title - notice title
	 * @param contents - notice contents
	 * @return
	 */
	@POST
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//	@Override
	public abstract Response addNotice(@FormParam("title") String title,
			@FormParam("content") String contents,
			@HeaderParam("admin") String admin);

	/**
	 * check there is new notices after the lastupdate
	 * @param lastupdate - format in 12. 3. 8 오후 8:26
	 * @return
	 */
	@GET
	@Path("/hasnew/{lastupdated}")
	public abstract Response isExist(@PathParam("lastupdated") String lastupdate);

	/**
	 * pass the notice list after the lastupdate
	 * @param lastupdate - format in 12. 3. 8 오후 8:26
	 * @return
	 */
	@GET
	@Path("/new/{lastupdated}")
	@Produces({ MediaType.APPLICATION_JSON })
	public abstract Response getNewNotice(
			@PathParam("lastupdated") String lastupdate);

}