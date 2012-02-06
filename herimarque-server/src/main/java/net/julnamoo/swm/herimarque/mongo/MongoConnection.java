package net.julnamoo.swm.herimarque.mongo;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Mongo;

public class MongoConnection {

	Logger logger = LoggerFactory.getLogger(MongoConnection.class.getSimpleName());
	
	Mongo mongo;
	
	private static MongoConnection instance = new MongoConnection();
	
	private MongoConnection()
	{
		String host = "localhost";
		int port = 27017;
		
		Properties props = new Properties();
		File mongoPropF = new File(ClassLoader.getSystemResource("mongo.properties").getFile());
		try 
		{
			props.load(new FileReader(mongoPropF));
			host = props.getProperty("host", "localhost");
			port = Integer.parseInt(props.getProperty("port", "27017"));
		} catch (Exception e) {}
		
		try 
		{
			mongo = new Mongo(host, port);
		} catch (Exception e)
		{
			e.printStackTrace();
			logger.error("Fail to connect to the server");
			mongo = null;
		} 
	}

	public Mongo getMongo() {
		return mongo;
	}

	public static MongoConnection getInstance() {
		return instance;
	}
}
