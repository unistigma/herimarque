package net.julnamoo.swm.herimarque.resource;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.Resource;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import net.julnamoo.swm.herimarque.dao.UserDAO;
import net.julnamoo.swm.herimarque.service.SqliteService;
import net.julnamoo.swm.herimarque.util.PropertiesUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/db")
@Component
public class SqliteResourceImpl implements SqliteResource  {

	Logger logger = LoggerFactory.getLogger(SqliteResourceImpl.class.getSimpleName());
	
	@Autowired
	SqliteService sqliteService;

	@Resource(name="userDAO")
	UserDAO userDAO;
	
	@POST
	@Path("upload/{version}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadNewDB(@Context HttpServletRequest request, @PathParam("version") String version, @HeaderParam("admin")String admin) throws URISyntaxException
	{
		if(!userDAO.isAdmin(admin))
		{
			logger.warn("Not admin user attemp to upload the db from {}:{}", request.getRemoteHost(), request.getRemotePort());
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		try 
		{
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		
		if(ServletFileUpload.isMultipartContent(request))
		{
			String repo = PropertiesUtil.getValueFromProperties("herimarque.properties", "db");
			StringBuilder sb = new StringBuilder(repo);
			String fname = sb.append(File.separatorChar).append(version).toString();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			List<FileItem> items = null;
			try 
			{
				items = upload.parseRequest(request);
			} catch (FileUploadException e) 
			{
				e.printStackTrace();
			}
			
			if(items == null)
			{
				logger.error("Fail to upload the new db version, {}. Return 400", version);
				return Response.status(Status.BAD_REQUEST).build();
			}
			
			logger.debug("get the new db version {}", version);
			for(FileItem item : items)
			{
				logger.error("field name is {}, contentType is {}",item.getFieldName(), item.getContentType());
				if(!item.isFormField() && item.getSize() > 0)
				{
					if(item.getFieldName().equals("db"))
					{
						File dir = new File(PropertiesUtil.getValueFromProperties("herimarque.properties", "db"));
						
						//If the first db file
						if(!dir.exists()) dir.mkdirs();
						//save the file
						try 
						{
							item.write(new File(fname));
						} catch (Exception e) 
						{
							logger.error("Fail to upload the new db version, {}. Return 400", version);
							return Response.status(Status.BAD_REQUEST).build();
						}
						
						sqliteService.saveNewVersion(version);
						logger.debug("Finish to update the db with {} return 200", version);
						return Response.ok(version).build();
					}
				}
			}
			
		}
		logger.debug("cannog handle the request, return 403");
		return Response.status(Status.BAD_REQUEST).location(new URI("/db.html")).build();
	}
	
	@GET
	@Path("/itsnew/{version}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response isNewData(@PathParam("version") String version) 
	{
		String lastversion = sqliteService.itsNew(version);
		Response response = null;
		
		logger.debug("isNewData, return 200");
		response = Response.status(Status.OK).entity(lastversion).build();
		return response;
	}

	@GET
	@Path("/new/{version}")
	@Produces( {MediaType.APPLICATION_OCTET_STREAM} )
	public Response getNewData(@PathParam("version") String dbVersion, @HeaderParam("user") String id) 
	{
		if(!userDAO.isAuthenticated(id))
		{
			logger.info("No authenticated user({}) request the db version({})", id, dbVersion);
			return Response.status(Status.BAD_REQUEST).build();
		}
		//need to find the file from the resource
		File file = sqliteService.getDB(dbVersion);
		if(file == null)
		{
			logger.error("the bad request:the version({}) doesn't exist or unAuthenticated user request({})", dbVersion, id);
			return Response.status(Status.BAD_REQUEST).build();
		}else
		{
			logger.debug("getNewData, pass {}", file.getName());
			
			ResponseBuilder response = Response.ok((Object) file);
			String content = "attachment; filename='" + file.getName() + "'";
			response.header("Content-Disposition", content);
			return response.build();
		}
	}

}
