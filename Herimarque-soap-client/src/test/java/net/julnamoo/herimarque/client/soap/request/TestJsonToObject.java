package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.io.IOException;

import net.julnamoo.herimarque.client.soap.Item;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestJsonToObject {

	@Test
	public void test() throws IOException
	{
		JsonParser jsonPasrer = new JsonParser();
		String string = FileUtils.readFileToString(new File("response/ageList.json"));
		JsonArray jsonArray = jsonPasrer.parse(string).getAsJsonArray();
		
		Gson gson = new Gson();
		for(JsonElement je : jsonArray)
		{
			Item item = gson.fromJson(je, Item.class);
			System.out.println(item.getCrltsNm());
		}
	}
}
