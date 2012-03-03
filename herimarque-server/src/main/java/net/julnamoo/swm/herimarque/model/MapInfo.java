package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MapInfo implements Serializable{

	String user;
	String filePath;
	ArrayList<String> area;
	ArrayList<Location> logging;
	String uploadTime;
	
	public MapInfo() {}

	
	@Override
	public String toString() 
	{
		return this.filePath;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public ArrayList<String> getArea() {
		return area;
	}


	public void setArea(ArrayList<String> area) {
		this.area = area;
	}


	public ArrayList<Location> getLogging() {
		return logging;
	}


	public void setLogging(ArrayList<Location> logging) {
		this.logging = logging;
	}


	public String getUploadTime() {
		return uploadTime;
	}


	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
}
