package net.julnamoo.swm.herimarque.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.Assert;

import net.julnamoo.swm.herimarque.util.HerimarqueEncryptor;

import org.eclipse.jetty.util.security.Credential.MD5;
import org.junit.Test;

import com.sun.jersey.api.client.RequestWriter;
import com.sun.jersey.core.spi.factory.MessageBodyFactory;
import com.sun.jersey.spi.MessageBodyWorkers;
import com.sun.research.ws.wadl.Request;

public class TestSendMail {

//	@Test
	public void setMD5() throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		String a = "aaaaa";
		MessageDigest md = null;
		String code = null;

		md = MessageDigest.getInstance("MD5");
		
		md.update(a.getBytes("UTF-8"));
		String s = new String(md.digest());
		md.reset();
		System.out.println(s);
		
		md.update(a.getBytes("UTF-8"));
		String s2 = new String(md.digest());
		md.reset();
		System.out.println(s2);
		
		String s3 = MD5.digest(a);
		System.out.println(s3);
		
		String s4 = MD5.digest(a);
		System.out.println(s4);
		
		Assert.assertEquals(md.isEqual(s.getBytes(), s2.getBytes()), true);
		Assert.assertEquals(s3.equals(s4), true);
	}
	
//	@Test 
	public void sendMail() throws IOException
	{
		/***** CHANGE THESE FOUR VARIABLE VALUES TO REFLECT YOUR ENVIRONMENT ******/  
		String user = "herimarque";  // Newly created user on JAMES  
		String password = "admin123"; // user password  

		String fromAddress = "herimarque@herimarque.com"; // newlycreateduser@localhost  
		//String toAddress = "kjulee114@gmail.com";
		String toAddress = "netinamu42@naver.com";

		// Create a mail session  
		Properties properties = new Properties();  
		properties.put("mail.smtp.host", "localhost");  
		properties.put("mail.smtp.port", "25");  
		properties.put("mail.smtp.username", user);  
		properties.put("mail.smtp.password", password);  
		Session session = Session.getDefaultInstance(properties, null);

		try  
		{  
			Message message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(fromAddress));  
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));  

			message.setSubject("Email from our JAMES Herimarque Server");  

			/** build mail content **/
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/AuthUser.html")));
			String line = br.readLine();
			while(line != null)
			{
				System.out.println("read, " + line);
				sb.append(line);
				line = br.readLine();
			}

			String html = sb.toString();
			//build html containg user key
			String key = HerimarqueEncryptor.encryption("test@test.com");
			String targetURL = new StringBuilder().append("http://localhost:8080/herimarque/api/u/").append(key).append("?email=").append(toAddress).toString();
			html = html.replaceAll("url", targetURL);
			System.out.println("send : " + html);

			message.setText(html);

			Transport.send(message);  

			System.out.println("Email sent successfully");  
		}  
		catch (MessagingException e)  
		{  
			e.printStackTrace();  
		}  
	}
	
//	@Test
	public void sendMail2()
	{
		MailSender ms = new MailSender();
		ms.sendMail("netinamu42@naver.com", "test", "testKey");
		ms.sendMail("kjulee114@gmail.com", "test", "testKey");
		ms.sendMail("bjul@nate.com", "test", "testKey");
		ms.sendMail("netinamu42@hanmail.net", "test", "testKey");
	}
}
