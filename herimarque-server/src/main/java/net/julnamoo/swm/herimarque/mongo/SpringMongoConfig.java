package net.julnamoo.swm.herimarque.mongo;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.InitBinder;

import com.mongodb.Mongo;

/**
 * Spring MongoDB configuration file
 * @author julie
 *
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration{

	String host;
	int port;
	String dbname;
	
	@InitBinder
	public void initBinder()
	{
		//set default value
		host = "localhost";
		port = 27017;
		
		//get values from properties file
		Properties props = new Properties();
		File mongoPropF = new File(ClassLoader.getSystemResource("mongo.properties").getFile());
		try 
		{
			props.load(new FileReader(mongoPropF));
			host = props.getProperty("host", "localhost");
			port = Integer.parseInt(props.getProperty("port", "27017"));
		} catch (Exception e) {}
	}
	
	
	@Override
	public String getDatabaseName() {
		return this.dbname;
	}

	@Override
	public @Bean Mongo mongo() throws Exception {
		
		return new Mongo();
	}

	@Override
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), this.dbname);
	}
}
