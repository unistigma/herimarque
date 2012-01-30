package net.julnamoo.swm.herimarque.model;

public class MyLocation {

	Double latitude;
	Double longitude;
	
	public MyLocation(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
}