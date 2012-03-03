package net.julnamoo.swm.herimarque.service;

import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.util.MailSender;
import net.julnamoo.swm.herimarque.util.UserInfoEncryptor;

import org.eclipse.jetty.util.security.Credential.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Julie_air
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name="userDAO")
	UserDAOImpl userDAO;
	
	public UserServiceImpl(){}
	
	@Override
	public String addUser(String id, String email, String pwd) 
	{
		//check existence of a user with the id
		String isThere = userDAO.getUserKey(id);
		if(isThere != null) return null;
		
		// generate the temporal key and insert to Mongo
		logger.debug("Generate temp key for respected user {}", id);
		String key = UserInfoEncryptor.encryption(id);
		userDAO.addUser(id, pwd, false);
		
		/** Send the email **/
		new MailSender().sendMail(email, id, key);
		return key;
	}

	/**
	 * Unauthorized user are removed by admin.
	 * Generate the real key with information
	 */
	@Override
	public boolean authUser(String id, String key) 
	{
		//generate the final key
		logger.debug("Generate final key for new user {}", id);
		
		String expKey = MD5.digest(id);
		if(expKey.equals(key))
		{
			//get the first password
			String initP = userDAO.getUserKey(id);
			//generate the new key with the password
			String code = new StringBuilder().append(id).append(initP).toString();
			String finalKey = UserInfoEncryptor.encryption(code);
			userDAO.addUser(id, finalKey, true);
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public void delUser(String id) 
	{
		logger.debug("request delete user with {}", id);
		userDAO.delUser(id);
	}

	@Override
	public String changeUserInfo(String id, String pwd, String nPwd) 
	{
		logger.debug("generate new final key with new information for {}", id);
		
		String result;
		String code = new StringBuilder().append(id).append(pwd).toString();
		String olderKey = UserInfoEncryptor.encryption(code);
		//If the user is real
		if(olderKey.equals(userDAO.getUserKey(id)))
		{
			code = new StringBuilder().append(id).append(nPwd).toString();
			String newKey = UserInfoEncryptor.encryption(code);
			userDAO.changeInfo(id, newKey);
			result = newKey;
		}
		
		return null;
	}
	
	public List<String> allUsers()
	{
		logger.debug("retrive all users");
		return userDAO.allUsers();
	}
}
