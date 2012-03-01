package net.julnamoo.swm.herimarque.resource;


import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

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
	
	/**
	 * retrieve my maps with comments of it
	 * @param key
	 * @return
	 */
	public Response getMyMapList(String key);  
	
	/**
	 * retrieve someone's maps with comments of it
	 * @param email
	 * @return
	 */
	public Response getTheOtehrMapList(String email); 
	
	/**
	 * retrieve some place's(using gps location) maps with comments of it
	 * @param ctrdCd
	 * @return
	 */
	public Response getLocationMapList(String ctrdCd);
	
	/**
	 * retrieve maps of the kind with comments of it
	 * @param itemCd
	 * @return
	 */
	public Response getKindMapList(String itemCd);
	
	/**
	 * adding comment to the map 
	 * @param msg
	 * @return
	 */
	public Response addComment(String msg);
	
}
