package net.julnamoo.swm.herimarque.dao;

/**
 * 
 * @author Julie_air
 *
 */
public interface UserDAO {
	
	/**
	 * Insert the user info to the Mongo.
	 * If there is a user with the email then overwrite the object to new
	 * @param email
	 * @param key
	 * @return _id from Mongo
	 */
	public String addUser(String email, String key, boolean auth);
	
	/**
	 * Return the _id search result with email.
	 * If there are multiple object with the mail,
	 * then remain the first object only and others are removed.
	 * The key of the first object will be returned
	 * @param email
	 * @return _id from Mongo
	 */
	public String getUserKey(String email);
	
	/**
	 * Search the object with the email and insert the new object with the email and newKey.
	 * Previous value is removed automatically.
	 * @param email
	 * @param newKey
	 * @return _id from Mongo
	 */
	public String changeInfo(String email, String newKey);
	
	/**
	 * Delete a object or objects with the email from Mongo
	 * @param email
	 */
	public void delUser(String email);
}
