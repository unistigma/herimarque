package net.julnamoo.swm.herimarque.resource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.service.NoticeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/n")
@Component
public class NoticeResourceImpl implements NoticeResource {

	Logger logger = LoggerFactory.getLogger(NoticeResourceImpl.class.getSimpleName());
	
	@Autowired
	NoticeServiceImpl noticeService;
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.resource.NoticeResource#addNotice(java.lang.String, java.lang.String)
	 */
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Override
	public Response addNotice(@FormParam("title") String title, @FormParam("content") String contents)
	{
		try 
		{
			title = URLDecoder.decode(title, "UTF-8");
			contents = URLDecoder.decode(contents, "UTF-8");
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		logger.debug("handling add notice request with {},{}", title, contents);
		String msg = noticeService.addNotice(title, contents);
		
		Response response = Response.ok(msg).build();
		return response;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.resource.NoticeResource#isExist(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.resource.NoticeResource#getNewNotice(java.lang.String)
	 */
	@GET
	@Path("/new/{lastupdated}")
	@Produces( {MediaType.APPLICATION_JSON} )
	@Override
	public Response getNewNotice(@PathParam("lastupdated") String lastupdate) 
	{
		logger.debug("handling getNewNotice request");
		
		String msg = noticeService.getNotices(lastupdate);
		logger.debug("send the notice after {}", lastupdate);
		Response response = Response.ok(msg).build();
		
		return response;
	}

}
