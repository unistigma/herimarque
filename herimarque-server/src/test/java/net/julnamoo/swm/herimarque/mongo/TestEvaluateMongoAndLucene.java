package net.julnamoo.swm.herimarque.mongo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.julnamoo.swm.herimarque.model.MapInfo;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:rootContext.xml" })
public class TestEvaluateMongoAndLucene {

	private static int size = 100000;
	private List<MapInfo> testList;

	private static Version version;
	private static String luceneDir;
	
	private static Mongo mongo;
	private static DB db;
	private static DBCollection collection;

	@BeforeClass
	public static void setUp() throws CorruptIndexException, LockObtainFailedException, IOException, IllegalAccessException, ClassNotFoundException
	{
		//setup mongo
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		mongo = (Mongo) ctx.getBean("mongo");
		db = mongo.getDB("herimarque");
		collection = db.getCollection("test");
		
		//setup lucene
		version = Version.LUCENE_35;
		luceneDir = "/Volumes/Disk 2/herimarqueRepo/lucene/maps";

		System.out.println("Finish to set up");
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
//		
//		IndexWriter writer = new IndexWriter(FSDirectory.open(new File(luceneDir)), config);
//		//insert 100000 objects to mongo and lucene
//		for(int i = 0; i < size; i++)
//		{
//			String fpath = new String("test" + i);
//			String area = Integer.toString(i%17);
//			String age = Integer.toString(i%17);
//			String kind = Integer.toString(i%14);
//			String user = "user";
//
//			//to lucene
//			MapInfo map = new MapInfo();
//			map.setFilePath(fpath);
//			map.setArea(area);
//			map.setAge(age);
//			map.setKind(kind);
//			map.setUser(user);
//			//map.setUploadTime(new SimpleDateFormat("").format(new Date()));
//			// get simple format for test
//			map.setUploadTime(Integer.toString(i));
//			writer.addDocument(Obj2Doc.toDocument(map));
//			writer.commit();
//			System.out.println("insert " + i + "th lucene object");
//
//			// to mongo
//			DBObject doc = new BasicDBObject();
//			doc.put("fpath", fpath);
//			doc.put("area", area);
//			doc.put("age", age);
//			doc.put("kind", kind);
//			doc.put("user", user);
//			doc.put("updated", i);
//		
//			collection.insert(doc);
//			System.out.println("insert " + i + "th mongo object");
//		}
//		
//		writer.close();
//		System.out.println("Finish to insert");
	}

	@Test
	public void testFindAreaMongo()
	{
		long start = System.currentTimeMillis();
		DBObject qdoc = new BasicDBObject();
		qdoc.put("area", "10");
		DBCursor cursor = collection.find(qdoc);
		long end = System.currentTimeMillis();
		System.out.println("search with MONGO, with area 10, taking times : " + Long.toString(end-start)+ ". Total result set size is "+ cursor.count());
	}

	@Test
	public void testFindAreaLucene() throws CorruptIndexException, IOException
	{
		long start = System.currentTimeMillis();
		IndexReader reader = IndexReader.open(FSDirectory.open(new File(luceneDir)));
		IndexSearcher searcher = new IndexSearcher(reader);
		
		TermQuery tq = new TermQuery(new Term("area", "10"));
		TopDocs td = searcher.search(tq, 10000000);
		long end = System.currentTimeMillis();
		System.out.println("search with LUCENE, with area 10, taking times : " + Long.toString(end-start)+ ". Total result set size is "+ td.totalHits);
	}
}
