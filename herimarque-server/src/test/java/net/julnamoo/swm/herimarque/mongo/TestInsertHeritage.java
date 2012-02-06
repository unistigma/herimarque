package net.julnamoo.swm.herimarque.mongo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Properties;

import junit.framework.Assert;

import net.julnamoo.swm.herimarque.model.Item;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.chainsaw.Main;
import org.bson.BSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.ClassRoadie;
import org.junit.runners.model.TestClass;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class TestInsertHeritage {

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
	public void test() throws JsonIOException, JsonSyntaxException, IOException
	{
		DBCollection collection = db.getCollection("heritage");
		
		JsonParser jsonparser = new JsonParser();
		
		/*
		//using file reader
		System.out.println("Using jsonparser with string");
		File f = new File(ClassLoader.getSystemResource("kindList.json").getPath());
		System.out.println(f.getPath());
		JsonArray jsonarray = jsonparser.parse(new FileReader(f)).getAsJsonArray();
		
		JsonElement jsonElement = jsonarray.get(0);
		System.out.println(jsonElement.toString());
		*/
		
		System.out.println("Using jsonparser with file reader");
		JsonArray jsonarray = jsonparser.parse(new FileReader(new File(ClassLoader.getSystemResource("kindList.json").getFile()))).getAsJsonArray();
		JsonElement jsonElement = jsonarray.get(0);
		System.out.println(jsonElement.toString());
		
		DBObject object = (DBObject) JSON.parse(jsonElement.toString());
		collection.insert(object);
		System.out.println("insert the json");
		
		BasicDBObject queryDoc = new BasicDBObject();
		queryDoc.put("crltsNm", "서울 숭례문");
		
		DBCursor result = collection.find(queryDoc);
		if(result.hasNext())
		{
			System.out.println(result.next());
		}
	}
	
	@After
	public void teardown()
	{
		
	}
}
