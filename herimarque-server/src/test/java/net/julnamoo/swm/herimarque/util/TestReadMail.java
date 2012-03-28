package net.julnamoo.swm.herimarque.util;

import java.beans.Statement;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Test;

public class TestReadMail {

	@Test
	public static void main(String args[]) throws Exception {
		String host = "localhost";
		String user = "sandeep";
		String password = "sandeep";
		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties);
		Store store = session.getStore("pop3");
		store.connect(host, user, password);
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_ONLY);

		Message[] message = folder.getMessages();

		for (int i = 0; i < message.length; i++) {
			System.out.println("------------ Message " + (i + 1) + " ------------");
			System.out.println("SentDate : " + message[i].getSentDate());
			System.out.println("From : " + message[i].getFrom()[0]);
			System.out.println("Subject : " + message[i].getSubject());
			System.out.print("Message : ");
			InputStream stream = message[i].getInputStream();
			while (stream.available() != 0) {
				System.out.print((char) stream.read());
			}
		}
		folder.close(true);
		store.close();

		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String driver = "com.mysql.jdbc.Driver";
		String username = "root"; 
		String userPassword = "root";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,username,userPassword);
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = ((java.sql.Statement) st).executeQuery("select message from message");
			while (rs.next()){
				String msg = rs.getString("message");
				System.out.println(msg);
			}
			System.out.println("Query Executed Successfully...");  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}
}
