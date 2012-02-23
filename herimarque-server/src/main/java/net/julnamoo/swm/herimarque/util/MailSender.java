package net.julnamoo.swm.herimarque.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
	
	protected MailSender(){}
	
	public void sendMail(String toAddress)
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
			message.setText("Hello, Herimarque! It's sample contents");
//			message.setContent(new Object(), "");
			Transport.send(message);
			logger.info("Send authentication email to {}", toAddress);
			
		} catch (AddressException e) 
		{
			e.printStackTrace();
		} catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
}
