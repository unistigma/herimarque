package net.julnamoo.swm.herimarque.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.model.Notice;
import net.julnamoo.swm.herimarque.service.NoticeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Path("/n")
@Component
public class NoticeResourceImpl implements NoticeResource {

	Logger logger = LoggerFactory.getLogger(NoticeResourceImpl.class.getSimpleName());
	
	@Autowired
	NoticeServiceImpl noticeService;
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Override
	public Response addNotice(String notice) 
	{
		logger.debug("handling add notice request");
		Notice uploaded = noticeService.addNotice(notice);
		String msg = new Gson().toJson(uploaded);
		
		Response response = Response.ok(msg).build();
		return response;
	}
	
	@GET
	@Path("/hasnew/{lastupdated}")
	@Override
	public Response isExist(@PathParam("lastupdated") String lastupdate) 
	{
		logger.debug("handling isExist request");
		boolean isExist = noticeService.checkNew(lastupdate);
		
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
	@Produces( {MediaType.APPLICATION_JSON} )
	@Override
	public Response getNewNotice(@PathParam("lastupdated") String lastupdate) 
	{
		logger.debug("handling getNewNotice request");
		
		String msg = noticeService.getNotices(lastupdate);
		logger.debug("send the notice after {}", lastupdate);
		Response response = Response.ok().entity(msg).build();
		
		return response;
	}

}
