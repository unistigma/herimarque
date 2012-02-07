package net.julnamoo.swm.herimarque.mongo;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class TestMainForDataInsert
{
	@Test
	public void test() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		InsertHeritage inserter = new InsertHeritage("kindList.json");
		inserter.insertAllHeritage();
	}
}