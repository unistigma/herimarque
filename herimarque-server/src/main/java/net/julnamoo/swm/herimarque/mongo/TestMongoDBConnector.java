package net.julnamoo.swm.herimarque.mongo;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class TestMongoDBConnector {

	String host;
	int port;
	
	Mongo mongo;
	DB db;
	
	@Before
	public void setup() throws UnknownHostException, MongoException
	{
		host = "localhost";
		port = 27017;
		
		mongo = new Mongo(host, port);
		db = mongo.getDB("herimarque");
	}
	
	@Test
	public void test()
	{
		DBCollection collection = db.getCollection("test");
		
		BasicDBObject document = new BasicDBObject();
		document.put("id", 1001);
		document.put("msg", "Hello world MongDB in Java");
		
		collection.insert(document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", 1001);
		
		//querying
		DBCursor cursor = collection.find(searchQuery);
		
		while(cursor.hasNext())
		{
			System.out.println(cursor.next());
		}
		
		System.out.println("Finished");
	}
	
}
