package net.julnamoo.swm.herimarque.resource;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface ContentResource {

	/**
	 * Save the image from the form data then pass the json to service class
	 * for saving via DAO
	 * @param request - HttpServletRequest containing mapInfo json and img files
	 * @param user - request user
	 * @return
	 */
	public Response uploadMap(@Context HttpServletRequest request, @PathParam("id")String user);
	
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
			@HeaderParam("user") String user,
			@DefaultValue("11") @PathParam("ctrdCd") String ctrdCd);
	
	/**
	 * adding comment to the map 
	 * @param msg
	 * @return
	 */
	@Consumes({MediaType.APPLICATION_JSON})
	public abstract Response addComment(
			@HeaderParam("user") String user,
			@PathParam("map") String map,
			@FormParam("content") String msg);
	
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
	public abstract Response mostHitMaps(
			@HeaderParam("user") String user);
	
	/**
	 * Retrieve the maps in the preriod 
	 * @param msg
	 * @return
	 */
	@Consumes({MediaType.APPLICATION_JSON})
	public abstract Response getMapsInPeriod(
			@HeaderParam("user")String user,
			@FormParam("start") String start,
			@FormParam("end") String end);
	
}
