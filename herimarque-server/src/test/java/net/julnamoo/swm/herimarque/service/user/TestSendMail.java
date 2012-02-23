package net.julnamoo.swm.herimarque.service.user;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class TestSendMail {

	@Test
	public void sendMail()
	{
		/***** CHANGE THESE FOUR VARIABLE VALUES TO REFLECT YOUR ENVIRONMENT ******/  
        String user = "herimarque";  // Newly created user on JAMES  
        String password = "admin123"; // user password  
  
        String fromAddress = "herimarque@herimarque.com"; // newlycreateduser@localhost  
        String toAddress = "kjulee114@gmail.com";  
  
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
            String html = "<h1>Hello herimarque</h1> <h2>Test for sending html mail</h2>";
//			BodyPart messageBodyPart = new MimeBodyPart();
			//build html containg user key
//			messageBodyPart.setContent(html, "text/html");
			
			message.setContent(html, "text/html");
			
            Transport.send(message);  
  
            System.out.println("Email sent successfully");  
        }  
        catch (MessagingException e)  
        {  
            e.printStackTrace();  
        }  
	}
}
