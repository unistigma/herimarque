package net.julnamoo.swm.herimarque.service;

import net.julnamoo.swm.herimarque.dao.UserDAOImpl;
import net.julnamoo.swm.herimarque.util.MailSender;
import net.julnamoo.swm.herimarque.util.UserInfoEncryptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Julie_air
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAOImpl userDAO;
	
	@Override
	public String addUser(String email, String pwd) 
	{
		// generate the temporal key and insert to Mongo
		logger.debug("Generate temp key for respected user {}", email);
		String key = UserInfoEncryptor.encryption(email, pwd);
		String _id = userDAO.addUser(email, key, false);
		
		/** Send the email **/
		new MailSender().sendMail(email, key);
		return _id;
	}

	/**
	 * Unauthorized user are removed by admin.
	 * Generate the real key with email and the key
	 */
	@Override
	public boolean authUser(String email, String key) 
	{
		//generate the final key
		logger.debug("Generate final key for new user {}", email);
		String finalKey = UserInfoEncryptor.encryption(email, key);
		userDAO.addUser(email, key, true);
		
		return true;
	}

	@Override
	public void delUser(String email) 
	{
		logger.debug("request delete user with {}", email);
		userDAO.delUser(email);
	}

	@Override
	public boolean changeUserInfo(String email, String pwd) 
	{
		logger.debug("generate new final key with new information for {}", email);
		String newKey = UserInfoEncryptor.encryption(email, "0000");
		String finalKey = UserInfoEncryptor.encryption(email, newKey);
		String result = userDAO.changeInfo(email, finalKey);
		
		return result == null ? false : true;
	}
	
}
