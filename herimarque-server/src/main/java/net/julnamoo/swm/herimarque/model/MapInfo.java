package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MapInfo implements Serializable{

	String user;
	String title;
	String content;
	List<String> area;
	String filePath;
	List<Location> timeline;
	String uploadTime;
	
	List<String> likes;
	Integer likeCount;
	List<Comment> comments;
	String mapKey;
	
	public MapInfo() {}
	
	@Override
	public String toString() 
	{
		return "[user@" + this.user + ", filePath@" + this.filePath + "]";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getArea() {
		return area;
	}

	public void setArea(List<String> area) {
		this.area = area;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<Location> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<Location> timeline) {
		this.timeline = timeline;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	public Integer getLikeCount() {
		return this.likes.size();
	}

	public void setLikeCount(Integer likeCount) {
//		this.likeCount = likeCount;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
}
