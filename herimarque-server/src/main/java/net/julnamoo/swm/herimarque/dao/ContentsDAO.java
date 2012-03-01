package net.julnamoo.swm.herimarque.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;

public interface ContentsDAO {

	@PostConstruct
	public abstract void setUp();

	public abstract String addMapInfo(MapInfo mapInfo);

	public abstract List<MapInfo> getUsersMapList(String id);

	public abstract List<MapInfo> getLocationMapList(String ctrdCd);

	public abstract List<MapInfo> getKindMapList(String itemCd);

	public abstract boolean addComment(Comment comment) throws Exception;

}