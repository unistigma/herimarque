package net.julnamoo.swm.herimarque.mongo;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonParser;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class HeritageInsert {

	String host;
	int port;

	Mongo mongo;
	DB db;

	public HeritageInsert() throws UnknownHostException, MongoException
	{
		host = "localhost";
		port = 27017;

		mongo = new Mongo(host, port);
		db = mongo.getDB("herimarque");
	}
	
	
	private void insertTest()
	{
		JsonParser jsonParser = new JsonParser();
		
	}
	
}
