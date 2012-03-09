package net.julnamoo.swm.herimarque.service;

public interface UserService {

	/**
	 * Enroll user service
	 * Generate encryption key with email and pwd
	 * Return the _id received from DAO, the key for each user
	 * 
	 * Send a mail containing the key and user email in the url request
	 * The key and the id address will be passed by HeaderParam.
	 * @param email
	 * @param pwd
	 * @return userKey
	 */
	public abstract String addUser(String id, String email, String pwd);

	/**
	 * Authenticate the user from the typical link sent to the user in enrolling
	 * Check the key from the request and determine registering of the user.
	 * @param id
	 * @param key
	 * @return boolean value of authenticated or not
	 */
	public abstract boolean authUser(String id, String key);

	/**
	 * check the encrypted key with id and pwd is equal
	 * @param id - user id
	 * @param pwd - user pwd
	 * @return true - if the encrypted value is equal with mongo
	 */
	public abstract boolean logIn(String id, String pwd);

	/**
	 * Delete the object from the Mongo having the passed email address
	 * @param id
	 */
	public abstract void delUser(String id);

	/**
	 * Change the info of the user with the email.
	 * At first, auth the user is the origin with id and older pwd
	 * Password can be modified ONLY
	 * @param id
	 * @param pwd
	 * @return _id from the mongo
	 */
	public abstract String changeUserInfo(String id, String pwd, String nPwd);

	/**
	 * Retrieve all user from mongo
	 * @return json - json of user list  
	 */
	public abstract String allUsers();

}