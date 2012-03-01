package net.julnamoo.swm.herimarque.mongo;

import javax.annotation.Resource;

import org.eclipse.jetty.util.ajax.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:rootContext.xml"}) 
public class TestAddArrayList {

	@Resource
	Mongo mongo;
	
	@Test
	public void test()
	{
		DB db = mongo.getDB("herimarque");
		DBCollection collections = db.getCollection("test");
		DBObject origin = new BasicDBObject();
		origin.put("f1", "f1");
		
		//insert new obj in any case
		collections.save(origin);
		
		//check there is the comments
		DBCursor cursor = collections.find(origin);
		DBObject resObj = cursor.next();
		System.out.println("It is the first comment? : " + !resObj.containsField("comments"));
		
		DBObject comment1 = new BasicDBObject();
		comment1.put("f1", "f1");
		comment1.put("f2", "F2");
		DBObject comment2 = new BasicDBObject();
		comment2.put("f1", "f1");
		comment2.put("f2", "F2_2");
		
		BasicDBList clist = new BasicDBList();
		clist.add(comment1);
		clist.add(comment2);
		
		//add the list to the origin object
		origin.put("comments", clist);
		collections.save(origin);
		
		cursor = collections.find(origin);
		if(cursor.hasNext())
		{
			System.out.println(JSON.toString(cursor.next()));
		}
	}
}
