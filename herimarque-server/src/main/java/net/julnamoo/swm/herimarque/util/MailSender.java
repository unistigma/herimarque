package net.julnamoo.swm.herimarque.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender 
{
	Logger logger = LoggerFactory.getLogger(MailSender.class.getSimpleName());
	
	private String host = "localhost";
	private String port = "25";
	
	private String herimarque_admin_user = "herimarque";
	private String admin_pwd = "admin123";
	private String fromAddress = "herimarque@herimarque.com";
	
	private static String subject = "[Herimarque] 헤리마크 가입 인증 메일입니다.";
	
	public MailSender(){}
	
	public void sendMail(String toAddress, String id, String key)
	{
		logger.debug("prepare sending email to {}", toAddress);
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);  
        properties.put("mail.smtp.port", port);  
        properties.put("mail.smtp.username", herimarque_admin_user);  
        properties.put("mail.smtp.password", admin_pwd);  
        Session session = Session.getDefaultInstance(properties, null);
        logger.debug("create email sender session for {}", toAddress);
		
        //build the message
        Message message = new MimeMessage(session);
        try 
        {
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			message.setSubject(subject);

			//get mail contents
			String html = getContents(id, key);
			message.setText(html);
			//send the mail
			Transport.send(message);

			logger.info("Send authentication email to {}", toAddress);
			
		} catch (AddressException e) 
		{
			e.printStackTrace();
		} catch (MessagingException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Build html string to send.
	 * It contains email address and temp key of the user
	 * @param id
	 * @param code
	 * @return html
	 * @throws IOException 
	 */
	private String getContents(String id, String code) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		InputStream is = this.getClass().getResourceAsStream("/AuthUser.html");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = br.readLine();
		while(line != null)
		{
			sb.append(line);
			line = br.readLine();
		}
		
		is.close();
		br.close();

		String html = sb.toString();
		//build html containg user key
		String targetURL = new StringBuilder().append("http://localhost:8080/herimarque/api/u/").append(code).append("?id=").append(id).toString();
		logger.debug("building authentication request url:{}", targetURL);
		
		html = html.replaceAll("url", targetURL);
		logger.debug("HTML contents send to {} : {}", id, html);
		
		return html;
	}
}
