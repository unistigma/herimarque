package net.julnamoo.swm.herimarque.mongo;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.util.Hash;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:rootContext.xml"})
public class TestGetObjetID {

	@Resource
	Mongo mongo;
	
	@Test
	public void test()
	{
		DB db = mongo.getDB("herimarque");
		DBCollection collection = db.getCollection("test");
		
		BasicDBObject doc = (BasicDBObject) collection.findOne();
		ObjectId oid = (ObjectId) doc.get("_id");
		System.out.println("oid : " + oid.toString());

		ObjectId noid = new ObjectId("4f4bb03faa241508431e6155");
		System.out.println(noid.toString());
		BasicDBObject qdoc = new BasicDBObject();
		qdoc.put("_id", noid);
		BasicDBObject rdoc = (BasicDBObject) collection.findOne(qdoc);
		System.out.println(rdoc);
	}
}
