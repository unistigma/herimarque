package net.julnamoo.swm.herimarque.resource;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Notice;
import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.springframework.stereotype.Component;

@Path("/n")
@Component
public class NoticeResourceImpl implements NoticeResource {

	@Resource
	private ConstantsBean constants;
	
	@GET
	@Path("/new")
	@Override
	public Response isExist(String lastupdate) 
	{
		boolean isExist = true;
		Response response = Response.ok().header("exist", isExist).build();
		return response;
	}

	@GET
	@Path("/itsnew")
	@Override
	public Response getNewNotice() 
	{
		Notice itsNew = new Notice("testTitle", "testContent");
		Response response = Response.ok().entity(itsNew).build();
		return response;
	}

}
