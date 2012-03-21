package net.julnamoo.swm.herimarque.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.eclipse.jetty.util.security.Credential.MD5;
import org.junit.Before;
import org.junit.Test;

public class TestEncryption 
{
	String email;
	String pwd;
	
	@Before
	public void setup()
	{
		email = "test@test.com";
		pwd = "test";
	}
	
//	@Test
	public void test() throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(email.getBytes("UTF-8"));
		md.update(pwd.getBytes("UTF-8"));
		BigInteger bi = new BigInteger(md.digest());
		String key = bi.toString();
		System.out.println("new key : " + key + ", length is " + key.length());
	}
	
	@Test
	public void encrypt(){
		System.out.println(HerimarqueEncryptor.encryption("juliesmxlskan"));
		System.out.println(HerimarqueEncryptor.encryption("tusersomasoma"));
	}

}
