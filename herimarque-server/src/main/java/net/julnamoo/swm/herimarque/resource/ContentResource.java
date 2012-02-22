package net.julnamoo.swm.herimarque.resource;

import javax.ws.rs.core.Response;

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
//	public Response uploadMap(InputStream uploadedInputStream, FormDataContentDisposition fileDetail);
	
	public Response getMyMapList(String key); // retrieve my maps with comments of it
	public Response getTheOtehrMapList(String email); // retrieve someone's maps with comments of it
	public Response getLocationMapList(String ctrdCd); //retrieve some place's(using gps location) maps with comments of it
	public Response getKindMapList(String itemCd); //retrieve maps of the kind with comments of it
	
	// For manage comments
	public Response addComment(String mapKey, String comment);
	
}
