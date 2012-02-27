package net.julnamoo.swm.herimarque.resource;


import java.io.InputStream;

import javax.ws.rs.core.Response;

import net.julnamoo.swm.herimarque.model.Comment;

import com.sun.jersey.core.header.FormDataContentDisposition;

public interface ContentResource {

	/**
	 * with POST.
	 * The map photos will be downgraded
	 * @param key
	 * @return
	 */
	// For Up/Download maps
//	public Response uploadMap();
//	public Response uploadMap(String key); // upload
	public Response uploadMap(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String user, String area, String age, String kind);
	
	public Response getMyMapList(String key); // retrieve my maps with comments of it
	public Response getTheOtehrMapList(String email); // retrieve someone's maps with comments of it
	public Response getLocationMapList(String ctrdCd); //retrieve some place's(using gps location) maps with comments of it
	public Response getKindMapList(String itemCd); //retrieve maps of the kind with comments of it
	
	// For manage comments
	public Response addComment(Comment comment);
	
}
