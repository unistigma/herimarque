package net.julnamoo.swm.herimarque.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXB;

import net.julnamoo.swm.herimarque.model.Comment;
import net.julnamoo.swm.herimarque.model.Location;
import net.julnamoo.swm.herimarque.model.MapInfo;

import org.eclipse.jetty.util.ajax.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryBuilder;

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
			System.out.println("get comment list test");
			BasicDBObject o = (BasicDBObject) cursor.next();
			BasicDBList l = (BasicDBList) o.get("comments");
			for(int i =0; i <l.size(); ++i)
			{
				BasicDBObject c = (BasicDBObject) l.get(i);
				System.out.println(c.getString("f1"));
			}
			System.out.println();
		}
		
	}
	
	@Test
	public void insertMapInfo()
	{
		MapInfo mi = new MapInfo();
		mi.setUploadTime(new Date().toString());
		mi.setUser("testId");
		mi.setFilePath("testFilePath");
		
		ArrayList<String> areas = new ArrayList<String>();
		areas.add("a1");
		areas.add("a2");
		mi.setArea(areas);
		
		List<Comment> comments =  new ArrayList<Comment>();
		
		Comment c1 = new Comment();
		c1.setUserKey("u1");
		c1.setContent("comment1");
		
		Comment c2 = new Comment();
		c2.setUserKey("u2");
		c2.setContent("comment2");
		
		comments.add(c1);
		comments.add(c2);
		mi.setComments(comments);
		mi.setLikeCount(2);
		
		List<String> likes = new ArrayList<String>();
		likes.add("u1");
		likes.add("u2");
		mi.setLikes(likes);
		
		List<Location> logging = new ArrayList<Location>();
		Location l1 = new Location();
		l1.setType("PT");
		l1.setX("0.00");
		l1.setY("0.01");
		
		Location l2 = new Location();
		l2.setType("TR");
		l2.setX("0.0023413");
		l2.setY("0.011232315");
		logging.add(l1);
		logging.add(l2);
		mi.setLogging(logging);
		
		String json = new Gson().toJson(mi);
		System.out.println("first converted by gson : " + json);

		MongoTemplate mt = new MongoTemplate(mongo, "herimarque");
		mt.save(mi, "test");
		
		Query q = new Query();
//		new QueryBuilder().is(obj);
		q.addCriteria(new Criteria("filePath").is("testFilePath"));
//		q.addCriteria(new Criteria("filePath").is("testFileth"));
		
		MapInfo mir = mt.findOne(q, MapInfo.class, "test");
		Comment c3 = new Comment();
		c3.setUserKey("ddd");
		c3.setContent("testContents");
		
		List<Comment> cs = mir.getComments();
		cs.add(c3);
		mt.updateFirst(q, Update.update("comments", cs), "test");
		
		List<MapInfo> maps = mt.find(q, MapInfo.class, "test");
		System.out.println("returned map list size is " + maps.size());
		for(MapInfo m : maps)
		{
			System.out.println(m);
			List<Comment> temp = m.getComments();
			for(Comment tempC : temp)
			{
				System.out.println(tempC.toString());
			}
		}
		System.out.println(mir == null);
		System.out.println(mir);
		
	}
}
