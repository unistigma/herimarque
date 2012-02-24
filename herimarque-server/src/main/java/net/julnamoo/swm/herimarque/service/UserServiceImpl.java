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
		String _id = userDAO.addUser(email, key);
		
		/** Send the email **/
		new MailSender().sendMail(email, key);
		return _id;
	}

	/**
	 * Unauthorized user are removed by admin.
	 * Generate the real key with email and the key
	 */
	@Override
	public boolean oauthUser(String email, String key) 
	{
		//generate the final key
		logger.debug("Generate final key for new user {}", email);
		String finalKey = UserInfoEncryptor.encryption(email, key);
		
		
		return false;
	}

	@Override
	public void delUser(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean changeUserInfo(String email, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
