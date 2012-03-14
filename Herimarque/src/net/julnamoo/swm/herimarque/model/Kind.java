package net.julnamoo.swm.herimarque.model;

public class Kind {

	private int code;
	private String name;
	private long image;
	
	public Kind() {	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get image resource id
	 * @return
	 */
	public long getImage() {
		return image;
	}
	public void setImage(long image) {
		this.image = image;
	}
	
	
}
