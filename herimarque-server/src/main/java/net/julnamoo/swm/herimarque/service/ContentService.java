package net.julnamoo.swm.herimarque.service;

import java.io.InputStream;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;

/**
 * Convert msg from the resource class to POJO and perform each service logic
 * @author Julie_air
 *
 */
public interface ContentService {

	/**
	 * Store the file into map repository 
	 * and passing mapinfo to content dao for saving map info in the mongo
	 * @param uploadedInputStream - map input stream
	 * @param fname - name of the map
	 * @param id - upload user
	 * @param otherInfo - other information of the map like area string list, gps tracking logs
	 * @return mapId - id of uploaded map
	 */
	public abstract String uploadMap(InputStream uploadedInputStream,
			String fname, String id, String otherInfo);

	/**
	 * Retrieve the map info list with the id
	 * @param id - request id
	 * @return json of mapInfo list
	 */
	public abstract String getUserMapList(String id);

	/**
	 * Retrieve the map info list having the code of params
	 * @param ctrdCd - request location code. It follows public open DB portal spec with 'etc' field
	 * @return json of mapInfo list
	 */
	public abstract String getLocationMapList(String ctrdCd);

	/**
	 * Adding comment to the map contained in parameter
	 * @param user - user adding comment
	 * @param map - comment added map 
	 * @param comment - json msg of comment POJO
	 * @return success - boolean of whether success
	 */
	public abstract boolean addComment(String user, String map, String comment);

	/**
	 * Retrieve the map info list in decrease order of likes count of each maps
	 * @param user - check for authentication
	 * @return json of mapInfo list
	 */
	public abstract String getMostHitMaps(String user);
	
	/**
	 * Retrieve the map info list in the period
	 * @param user - request user
	 * @param start - start date of uploaded
	 * @param end - end date of uploaded
	 * @return json of mapInfo list
	 */
	public abstract String getMapsInPeriod(String user, String start, String end);
	
	/**
	 * Increase the count of the map.
	 * Also, add the user to the like list
	 * @param id - like user
	 * @param mapKey - map user like
	 * @return json of the target map instance
	 */
	public abstract String likeMap(String id, String mapKey);
}