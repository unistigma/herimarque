package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment implements Serializable{

	private String content;
	private String userKey;
	private String uploadTime;
	
	public Comment() {}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	
	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.userKey).append(", ").append(this.content);
		
		return sb.toString();
	}
	
}
