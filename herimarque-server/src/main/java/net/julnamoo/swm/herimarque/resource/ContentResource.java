package net.julnamoo.swm.herimarque.resource;


import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public interface ContentResource {

	/**
	 * with POST.
	 * The map photos will be downgraded
	 * @param key
	 * @return
	 */
	public Response uploadMap(
			@FormDataParam("id") String id,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDeatil,
			@FormDataParam("mapInfo") String mapInfo);
	// For Up/Download maps
//	public Response uploadMap();
//	public Response uploadMap(String key); // upload
//	public Response uploadMap(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String user, String area, String age, String kind);
	
	/**
	 * retrieve my maps with comments of it
	 * @param key
	 * @return
	 */
	public abstract Response getUserMapList(
			@DefaultValue("testId") @PathParam("id") String key);  
	
	/**
	 * retrieve some place's(using gps location) maps with comments of it
	 * @param ctrdCd
	 * @return
	 */
	public abstract Response getLocationMapList(
			@DefaultValue("11") @PathParam("ctrdCd") String ctrdCd);
	
	/**
	 * adding comment to the map 
	 * @param msg
	 * @return
	 */
	@Consumes({MediaType.APPLICATION_JSON})
	public abstract Response addComment(String msg);
	
	/**
	 * update the hit(like) for the map with the user
	 * @param id
	 * @param mapId
	 * @return
	 */
	public abstract Response likeMap(
			@DefaultValue("testId") @PathParam("id") String id, 
			@QueryParam("map") String mapId);
	
	/**
	 * Retrieve the maps in decrease order of like count
	 * @return
	 */
	public abstract Response mostHitMaps();
	
	/**
	 * Retrieve the maps in the preriod 
	 * @param msg
	 * @return
	 */
	@Consumes({MediaType.APPLICATION_JSON})
	public abstract Response getMapsInPeriod(String msg);
	
}
