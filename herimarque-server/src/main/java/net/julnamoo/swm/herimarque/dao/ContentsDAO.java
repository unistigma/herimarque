package net.julnamoo.swm.herimarque.dao;

import java.util.Date;
import java.util.List;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;

public interface ContentsDAO {

	/**
	 * 
	 * @param mapInfo
	 * @return mapID produced by mongo
	 */
	public abstract String addMapInfo(MapInfo mapInfo);

	/**
	 * Retrieve of maps information from mongo about the user, id
	 * @param id
	 * @return List<MapInfo>
	 */
	public abstract List<MapInfo> getUsersMapList(String id);

	/**
	 * Retrieve of maps information from mongo with location category code, ctrdCd
	 * @param ctrdCd
	 * @return List<MapInfo>
	 */
	public abstract List<MapInfo> getLocationMapList(String ctrdCd);

	/**
	 * Insert comment to mongo.
	 * If there are maps, like duplicate mapInfo object, throws multiple map exception
	 * @param mapKey - mapKey for adding comment
	 * @param comment - Comment to add
	 * @return success whether of insert
	 * @throws Exception
	 */
	public abstract boolean addComment(String mapKey, Comment comment) ;
	
	/**
	 * Retrieve the objects from the mongo in decrease order of likes count of maps
	 * @return List<MapInfo>
	 */
	public abstract List<MapInfo> mostHitMap();

	/**
	 * Retrieve map info objects from the mongo uploaded in period from start to end.
	 * @param start - start date
	 * @param end - end date
	 * @return List<MapInfo>
	 */
	public abstract List<MapInfo> getMapsInPeriod(Date start, Date end);
	
	/**
	 * Add the user to the like list of the map and increse the like count.
	 * Also, update the map information object of the map to mongo
	 * @param id - like user
	 * @param mapKey - map user like
	 * @return mapInfo - target map instance
	 */
	public abstract MapInfo likeMap(String id, String mapKey);
}