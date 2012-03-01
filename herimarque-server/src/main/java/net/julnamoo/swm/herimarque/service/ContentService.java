package net.julnamoo.swm.herimarque.service;

import java.io.InputStream;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.MapInfo;

public interface ContentService {

	public abstract String uploadMap(InputStream uploadedInputStream,
			MapInfo mapInfo);

	public abstract String getMyMapList(String id);

	public abstract String getOtherMapList(String id);

	public abstract String getLocationMapList(String ctrdCd);

	public abstract String getKindMapList(String itemCd);

	public abstract boolean addComment(Comment comment);

}