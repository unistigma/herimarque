package net.julnamoo.swm.herimarque.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	String mapKey;
	String comment;
	String userKey;
	
	public Comment() {}
	
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	
}
