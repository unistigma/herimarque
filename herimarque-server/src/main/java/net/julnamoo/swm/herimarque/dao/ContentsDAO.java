package net.julnamoo.swm.herimarque.dao;

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
	 * Retrieve of maps information from mongo with kind category code, itemCd
	 * @param itemCd
	 * @return List<MapInfo>
	 */
	public abstract List<MapInfo> getKindMapList(String itemCd);

	/**
	 * Insert comment to mongo.
	 * If there are maps, like duplicate mapInfo object, throws multiple map exception
	 * @param comment
	 * @return success whether of insert
	 * @throws Exception
	 */
	public abstract boolean addComment(Comment comment) throws Exception;

}