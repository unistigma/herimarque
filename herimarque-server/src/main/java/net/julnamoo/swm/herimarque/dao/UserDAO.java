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
	 * 
	 * @param id
	 * @param key
	 * @param auth
	 * @return _id from mongo or null at the first
	 */
	public String addUser(String id, String key, boolean auth);
	
	/**
	 * Return the _id search result with id.
	 * If there are multiple object with the id,
	 * then remain the first object only and others are removed.
	 * The key of the first object will be returned
	 * @param id
	 * @return _id from Mongo
	 */
	public String getUserKey(String id);
	
	/**
	 * Search the object with the id and insert the new object with the email and newKey.
	 * Previous value is removed automatically.
	 * @param id
	 * @param newKey
	 * @return _id from Mongo
	 */
	public String changeInfo(String id, String newKey);
	
	/**
	 * Delete a object or objects with the id from Mongo
	 * @param email
	 */
	public void delUser(String id);
}
