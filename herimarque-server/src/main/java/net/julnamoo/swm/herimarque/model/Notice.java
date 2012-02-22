package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notice implements Serializable{

	String title;
	String content;
	
	public Notice() {}
	
	public Notice(String title, String content)
	{
		this.title = title;
		this.content = content;
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
	
	@Override
	public String toString() 
	{
		return this.title;
	}
}
