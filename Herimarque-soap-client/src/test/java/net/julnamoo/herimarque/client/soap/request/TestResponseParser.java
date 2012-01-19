package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.julnamoo.herimarque.client.soap.Item;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestResponseParser {

	String fpath;
	
	@Before
	public void setup()
	{
	}
	
	@Test
	public void testResponseParsing() throws IOException, ParserConfigurationException, SAXException
	{
		File responseDir = new File("response");
		if(responseDir.isDirectory())
		{
			File[] response = responseDir.listFiles();
			
			List<Item> resultList = new ArrayList<Item>();
			
			for(File f : response)
			{
				System.out.println(f.getPath());
				
				String msg = FileUtils.readFileToString(f);
				
				DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource(new FileReader(f));
				is.setCharacterStream(new StringReader(msg));
				Document doc = docbuilder.parse(is);
				
				// get items
				NodeList items = doc.getElementsByTagName("item");
				for(int i = 0; i < items.getLength(); ++i)
				{
					Node item = items.item(i);
					NodeList subs = item.getChildNodes();
					
					//set field value
					Item instance = new Item();
					for(int j = 0; j < subs.getLength(); ++j)
					{
						Node sub = subs.item(j);
						
						String field = sub.getNodeName();
						String value = sub.getTextContent();
						
						try 
						{
							instance = setValue(instance, field, value);
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					
					//for testing
					resultList.add(instance);
				}
			}
			
			if(resultList.size() > 0)
			{
				System.out.println("Total size : " + resultList.size());
				Gson gson = new Gson();
				String res = gson.toJson(resultList);
				FileUtils.writeStringToFile(new File("area.json"), res);
//				System.out.println(res);
			}
		}
	}
	
	
	private Item setValue(Item item, String field, String value) throws Exception
	{
		//convert each field to object field with inflection api
		
		Class target = null;
		
		try 
		{
			target = Class.forName(item.getClass().getName());
			
		} catch (ClassNotFoundException e) 
		{
			//internal error
			e.printStackTrace();
			throw new Exception("Cannot convert object to Item instance");
		}
		
		Field[] fields = target.getDeclaredFields();
		
		for(Field f : fields)
		{
			if(f.getName().equals(field))
			{
				f.setAccessible(true);
				
				f.set(item, value);
//				System.out.println(item + ", " + value);
				return item;
			}
		}
		
		return null;
	}
	
	@After
	public void teardowns()
	{
		
	}
	
}
