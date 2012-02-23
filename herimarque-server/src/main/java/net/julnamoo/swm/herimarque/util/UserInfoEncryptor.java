package net.julnamoo.swm.herimarque.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Encrypt information of the one user with eamil and password
 * @author Julie_air
 *
 */
public class UserInfoEncryptor 
{
	private static Logger logger = LoggerFactory.getLogger(UserInfoEncryptor.class.getSimpleName());

	/**
	 * There is some Error and cannot finish the request
	 * when generated key is "-1".
	 * @param param1
	 * @param param2
	 * @return generatedKey
	 */
	public static String encryption(String param1, String param2)
	{
		MessageDigest md = null;
		String code = null;

		try 
		{
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(param1.getBytes("UTF-8"));
			md.update(param2.getBytes("UTF-8"));

			BigInteger bi = new BigInteger(md.digest());
			code = bi.toString();

		} catch (NoSuchAlgorithmException e) 
		{
			code = "-1";
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			
			md.reset();
			md.update(param1.getBytes());
			md.update(param2.getBytes());
			
			BigInteger bi = new BigInteger(md.digest());
			code = bi.toString();
		}

		logger.debug("success to encrypt {}, generated key length is {}", param1, code.length());
		return code;
	}
}
