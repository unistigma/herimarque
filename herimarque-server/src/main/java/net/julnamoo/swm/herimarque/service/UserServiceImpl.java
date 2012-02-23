package net.julnamoo.swm.herimarque.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Julie_air
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public String addUser(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean oauthUser(String email, String key) {
		// TODO Auto-generated method stub
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
