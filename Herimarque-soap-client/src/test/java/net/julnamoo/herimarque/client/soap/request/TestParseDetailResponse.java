package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.julnamoo.herimarque.client.soap.Item;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestParseDetailResponse {

	List<Item> list;
	Map<String, Item> map;
	
	@Before
	public void setup() throws IOException
	{
		list = new ArrayList<Item>();
		map = new HashMap<String, Item>();
		
		JsonParser jsonPasrer = new JsonParser();
		String string = FileUtils.readFileToString(new File("response/ageList.json"));
		JsonArray jsonArray = jsonPasrer.parse(string).getAsJsonArray();
		
		Gson gson = new Gson();
		for(JsonElement je : jsonArray)
		{
			Item item = gson.fromJson(je, Item.class);
			map.put(item.getCrltsNoNm(), item);
		}
	}
	
	@Test
	public void parse() throws Exception
	{
		LineNumberReader reader = new LineNumberReader(new FileReader(new File("age_detail.txt")));
		StringBuilder sb = new StringBuilder();
		String s = reader.readLine();
		while(s != null)
		{
			if(s.contains("<soap:Envelope"))
			{
				sb = new StringBuilder();
				sb.append(s);
			}else if(s.contains("</soap:Envelope>"))
			{
				sb.append(s);
//				System.out.println(sb.toString());
				build(sb.toString());
			}else
			{
				sb.append(s);
			}
			s = reader.readLine();
		}
		
		reader.close();
		
		System.out.println("Total length is " + list.size());
		Gson gson = new Gson();
		String json = gson.toJson(list);
		FileUtils.writeStringToFile(new File("temp/age_detail.json"), json);
	}

	public void build(String msg) throws ParserConfigurationException, SAXException, IOException
	{
		//		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//		InputSource is = new InputSource(new FileReader(new File("age_detail.txt")));
		//		Document doc = db.parse(is);

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(msg));
		Document doc = db.parse(is);

		NodeList val = doc.getElementsByTagName("return");
		Node item = val.item(0);
		
		NodeList keylist = doc.getElementsByTagName("crltsNoNm");
		Node keynode = keylist.item(0);
		String key = keynode.getTextContent();
		//need to be get from the hashmap
		Item instance = map.get(key);
		System.out.println(key + ", " + instance.getCrltsNm());
		
		Node desc = doc.getElementsByTagName("crltsDc").item(0);
//		System.out.println(desc.getTextContent());
		instance.setCrltsDc(desc.getTextContent());
		
		Node guCd = doc.getElementsByTagName("signguCd").item(0);
//		System.out.println(guCd.getTextContent());
		instance.setSignguCd(guCd.getTextContent());
		
		Node guNm = doc.getElementsByTagName("signguNm").item(0);
//		System.out.println(guNm.getTextContent());
		instance.setSignguNm(guNm.getTextContent());

		list.add(instance);
	}

	protected Item setValue(Item item, String field, String value) throws Exception
	{
		//convert each field to object field with inflection api
//		Class target = null;
//
//		try 
//		{
//			target = Class.forName(item.getClass().getName());
//
//		} catch (ClassNotFoundException e) 
//		{
//			//internal error
//			e.printStackTrace();
//			throw new Exception("Cannot convert object to Item instance");
//		}

//		Field[] fields = target.getDeclaredFields();
		Field[] fields = Item.class.getDeclaredFields();

		for(Field f : fields)
		{
			if(f.getName().equals(field))
			{
				f.setAccessible(true);
				f.set(item, value);

				//				logger.debug("Set ({}, {})", item, value);
				return item;
			}
		}

		return null;
	}
}
