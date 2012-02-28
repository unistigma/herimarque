package net.julnamoo.swm.herimarque.service;

import java.util.List;

import javax.annotation.Resource;

import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.util.MailSender;
import net.julnamoo.swm.herimarque.util.UserInfoEncryptor;

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
		String key = UserInfoEncryptor.encryption(email, pwd);
		String _id = userDAO.addUser(id, key, false);
		
		/** Send the email **/
		new MailSender().sendMail(email, id, key);
		return _id;
	}

	/**
	 * Unauthorized user are removed by admin.
	 * Generate the real key with email and the key
	 */
	@Override
	public boolean authUser(String id, String key) 
	{
		//generate the final key
		logger.debug("Generate final key for new user {}", id);
		
		String expKey = userDAO.getUserKey(id);
		if(expKey.equals(key))
		{
			String finalKey = UserInfoEncryptor.encryption(id, key);
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
	public String changeUserInfo(String id, String pwd) 
	{
		logger.debug("generate new final key with new information for {}", id);
		String newKey = UserInfoEncryptor.encryption(id, Double.toString(Math.random()));
		String finalKey = UserInfoEncryptor.encryption(pwd, newKey);
		String result = userDAO.changeInfo(id, finalKey);
		
		return result;
	}
	
	public List<String> allUsers()
	{
		logger.debug("retrive all users");
		return userDAO.allUsers();
	}
}
