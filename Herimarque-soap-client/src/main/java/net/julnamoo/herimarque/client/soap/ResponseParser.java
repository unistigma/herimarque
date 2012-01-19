package net.julnamoo.herimarque.client.soap;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;

/**
 * Send the request to the requestURL with the requestMsg.
 * Then, parse the response msg and convert it to JSON then store it.
 * 
 * @author Julie_air
 *
 */
public class ResponseParser {

	Logger logger = LoggerFactory.getLogger(ResponseParser.class.getSimpleName());

	public void run() throws Exception
	{
		File responseDir = new File("response");
		if(responseDir.isDirectory())
		{
			File[] response = responseDir.listFiles();

			List<Item> areaList = new ArrayList<Item>();
			List<Item> ageList = new ArrayList<Item>();

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
					
					if(f.getName().contains("age"))
					{
						ageList.add(instance);
					}else if (f.getName().contains("area"))
					{
						areaList.add(instance);
					}
				}
			}

			Gson gson = new Gson();
			if(areaList.size() > 0)
			{
				System.out.println("Area Total size : " + areaList.size());
				String res = gson.toJson(areaList);
				FileUtils.writeStringToFile(new File( "area.json"), res);
				//				System.out.println(res);
			}
			
			if(ageList.size() > 0)
			{
				System.out.println("Age Total size : " + areaList.size());
				String res = gson.toJson(ageList);
				FileUtils.writeStringToFile(new File( "age.json"), res);
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

}
