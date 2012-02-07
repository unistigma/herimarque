package net.julnamoo.swm.herimarque.mongo;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/rootContext.xml" })
public class TestMainForDataInsert {

	@Test
	public void test() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		InsertHeritage inserter = new InsertHeritage("kindList.json");
		inserter.insertAllHeritage();
	}
}
