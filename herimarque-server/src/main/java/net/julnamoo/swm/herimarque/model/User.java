package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User implements Serializable{

	String user;
	String finalKey;
	boolean auth;
	String type;

	public User(){}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(User.class.isInstance(obj))
		{
			User user = (User) obj;

			return user.getUser().equals(this.getUser());
		}else
			return false;
	}
	
	@Override
	public String toString() 
	{
		return "[id@" + this.user + ", finalKey@" + this.finalKey + ", auth@" + this.auth + "]" ;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFinalKey() {
		return finalKey;
	}

	public void setFinalKey(String finalKey) {
		this.finalKey = finalKey;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
