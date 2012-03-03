package net.julnamoo.swm.herimarque.service;

/**
 * 
 * @author Julie_air
 *
 */
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
	 public String addUser(String id, String email, String pwd);
	 
	 /**
	  * Authenticate the user from the typical link sent to the user in enrolling
	  * Check the key from the request and determine registering of the user.
	  * @param id 
	  * @param key
	  * @return boolean value of authenticated or not
	  */
	 public boolean authUser(String id, String key);
	 
	 /**
	  * Delete the object from the Mongo having the passed email address
	  * @param id
	  */
	 public void delUser(String id);
	 
	 /**
	  * Change the info of the user with the email.
	  * At first, auth the user is the origin with id and older pwd
	  * Password can be modified ONLY
	  * @param id
	  * @param pwd
	  * @return _id from the mongo
	  */
	 public String changeUserInfo(String id, String pwd, String nPwd);
}
