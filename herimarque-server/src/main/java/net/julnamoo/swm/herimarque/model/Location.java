package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement
@Field
public class Location implements Serializable{

	Character type;
	String x;
	String y;
	
	public Location() {	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	@Override
	public String toString() 
	{
		return getType()+",("+getX()+","+getY()+")";
	}
}
