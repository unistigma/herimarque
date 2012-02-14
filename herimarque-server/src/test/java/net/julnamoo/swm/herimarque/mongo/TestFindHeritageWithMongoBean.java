package net.julnamoo.swm.herimarque.mongo;

import net.julnamoo.swm.herimarque.model.Item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:rootContext2.xml" })
public class TestFindHeritageWithMongoBean {

Item item;
	@Test
	public void test()
	{
		//For annotation
		System.out.println("start");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		System.out.println("get context");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		System.out.println("get mongooperation");
		
		//find
		item = mongoOperation.findOne(new Query(Criteria.where("crltsNm").is("쌍조석간")), Item.class);
		if(item != null)
		{
			System.out.println("Find result :" + item.toString());
		}else
		{
			System.out.println("Item is null");
		}
		
	}
}
