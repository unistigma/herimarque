package net.julnamoo.swm.herimarque.service;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.util.HerimarqueEncryptor;
import net.julnamoo.swm.herimarque.util.MailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

/**
 * 
 * @author Julie_air
 *
 */
@Service
public class UserServiceImpl implements UserService{

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name="userDAO")
	UserDAOImpl userDAO;

	public UserServiceImpl(){}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#addUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addUser(String id, String email, String pwd) 
	{
		//check existence of a user with the id
		String isThere = userDAO.getUserKey(id);
		if(isThere != null) return null;

		// generate the temporal key and insert to Mongo
		logger.debug("Generate temp key for respected user {}", id);
		String key = HerimarqueEncryptor.encryption(id);
		userDAO.addUser(id, pwd, false);

		/** Send the email **/
		new MailSender().sendMail(email, id, key);
		return key;
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#authUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authUser(String id, String key) 
	{
		//generate the final key
		logger.debug("Generate final key for new user {}", id);
		String expKey = HerimarqueEncryptor.encryption(id);
		if(expKey.equals(key))
		{
			//get the first password
			String initP = userDAO.getUserKey(id);
			//generate the new key with the password
			String code = new StringBuilder().append(id).append(initP).toString();
			String finalKey = HerimarqueEncryptor.encryption(code);
			userDAO.addUser(id, finalKey, true);
			return true;
		}else
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#logIn(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean logIn(String id, String pwd)
	{
		logger.debug("Attempt to login. check key of the user with parameters");
		String code = new StringBuilder().append(id).append(pwd).toString();
		String expKey = HerimarqueEncryptor.encryption(code);
		String realKey = userDAO.getUserKey(id);

		boolean result = expKey.equals(realKey);
		logger.debug("login attempt is success? {}", result);
		return result;
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#delUser(java.lang.String)
	 */
	@Override
	public void delUser(String id) 
	{
		logger.debug("request delete user with {}", id);
		userDAO.delUser(id);
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#changeUserInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String changeUserInfo(String id, String pwd, String nPwd) 
	{
		logger.debug("generate new final key with new information for {}", id);

		String result = null;
		String code = new StringBuilder().append(id).append(pwd).toString();
		String olderKey = HerimarqueEncryptor.encryption(code);
		//If the user is real
		if(olderKey.equals(userDAO.getUserKey(id)))
		{
			code = new StringBuilder().append(id).append(nPwd).toString();
			String newKey = HerimarqueEncryptor.encryption(code);
			userDAO.changeInfo(id, newKey);
			result = newKey;
		}

		return result; 
	}

	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#allUsers()
	 */
	@Override
	public String allUsers()
	{
		logger.debug("retrive all users");
		String json = new Gson().toJson(userDAO.allUsers());
		return json;
	}
	
	/* (non-Javadoc)
	 * @see net.julnamoo.swm.herimarque.service.UserService#allUsers()
	 */
	@Override
	public boolean isAdmin(String user, String pwd)
	{
		if(logIn(user, pwd) && userDAO.isAuthenticated(user) && userDAO.isAdmin(user)) return true;
		else return false;
	}
}
