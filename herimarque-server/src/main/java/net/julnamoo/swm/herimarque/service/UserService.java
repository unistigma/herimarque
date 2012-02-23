package net.julnamoo.swm.herimarque.service;

public interface UserService {

	/**
	 * Enroll user service
	 * Generate encryption key with email and pwd
	 * Insert the user info to the Mongo
	 * return the mongo _id, the key for each user
	 * 
	 * Send a mail containing the key and user email in the url request
	 * The key and the email address will be passed by HeaderParam.
	 * @param email
	 * @param pwd
	 * @return userKey
	 */
	 public String addUser(String email, String pwd);
	 
	 /**
	  * Authenticate the user from the typical link sent to the user in enrolling
	  * Check the key from the request and determine registering of the user.
	  * @param email 
	  * @param key
	  * @return boolean value of authenticated or not
	  */
	 public boolean oauthUser(String email, String key);
	 
	 /**
	  * Delete the object from the Mongo having the passed email address
	  * @param email
	  */
	 public void delUser(String email);
	 
	 /**
	  * Change the info of the user with the email.
	  * Password can be modified ONLY
	  * @param email
	  * @param pwd
	  * @return
	  */
	 public boolean changeUserInfo(String email, String pwd);
}
