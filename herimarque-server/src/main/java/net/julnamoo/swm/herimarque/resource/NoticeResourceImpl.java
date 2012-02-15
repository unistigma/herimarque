package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Notice;

public class NoticeResourceImpl implements NoticeResource {

	@Override
	public Response isExist(String lastupdate) 
	{
		boolean isExist = true;
		Response response = Response.ok().header("existence", isExist).build();
		return response;
	}

	@Override
	public Response getNewNotice() 
	{
		Notice itsNew = new Notice("testTitle", "testContent");
		Response response = Response.ok().entity(itsNew).build();
		return response;
	}

}
