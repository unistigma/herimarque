package net.julnamoo.swm.herimarque.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.jetty.util.security.Credential.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Encrypt information of the one user with eamil and password
 * @author Julie_air
 *
 */
public class HerimarqueEncryptor 
{
	private static Logger logger = LoggerFactory.getLogger(HerimarqueEncryptor.class.getSimpleName());

	/**
	 * There is some Error and cannot finish the request
	 * when generated key is "-1".
	 * @param param1
	 * @param param2
	 * @return generatedKey
	 */
	public static String encryption(String target)
	{
//		MessageDigest md = null;
//		String code = null;
//
//		try 
//		{
//			md = MessageDigest.getInstance("MD5");
//			md.reset();
//			md.update(target.getBytes("UTF-8"));
//			code = new String(md.digest());
//
//		} catch (NoSuchAlgorithmException e) 
//		{
//			md.reset();
//			code = "-1";
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) 
//		{
//			e.printStackTrace();
//			
//			md.reset();
//			md.update(target.getBytes());
//			code = new String(md.digest());
//		}
		String code = MD5.digest(target).split(":")[1];
		logger.debug("success to encrypt {}, generated key length is {}", target, code.length());
		return code;
	}
}
