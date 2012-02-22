package net.julnamoo.swm.herimarque.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.model.Notice;
import net.julnamoo.swm.herimarque.util.ConstantsBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Path("/n")
@Component
public class NoticeResourceImpl implements NoticeResource {

	Logger logger = LoggerFactory.getLogger(NoticeResourceImpl.class.getSimpleName());
	
	@Resource
	private ConstantsBean constants;
	
	@GET
	@Path("/hasnew/{lastupdated}")
	@Override
	public Response isExist(@PathParam("lastupdated") String lastupdate) 
	{
		boolean isExist = true;
		
		Response response = null;
		
		if(isExist)
		{
			response = Response.status(Status.OK).build();
			logger.debug("notice isExist, return 200");
			
		}else
		{
			response = Response.status(Status.NO_CONTENT).build();
			logger.debug("notice isExist, return 204");
		}
		return response;
	}

	@GET
	@Path("/new/{lastupdated}")
	@Override
	public Response getNewNotice(@PathParam("lastupdated") String lastupdate) 
	{
		List<Notice> list = new ArrayList<Notice>();
		Notice itsNew = new Notice("testTitle", "testContent");
		list.add(itsNew);
		
		logger.debug("send the notice after {}", lastupdate);
		Response response = Response.ok().entity(list).build();
		
		return response;
	}

}
