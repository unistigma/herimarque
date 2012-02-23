package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.hamcrest.core.IsInstanceOf;

@XmlRootElement
public class User implements Serializable{

	String email;
	String finalKey;
	boolean auth;

	public User(){}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public boolean equals(Object obj) 
	{
		if(User.class.isInstance(obj))
		{
			User user = (User) obj;

			return user.getEmail().equals(this.getEmail());
		}else
			return false;
	}
	
	@Override
	public String toString() 
	{
		return this.getEmail();
	}
}
