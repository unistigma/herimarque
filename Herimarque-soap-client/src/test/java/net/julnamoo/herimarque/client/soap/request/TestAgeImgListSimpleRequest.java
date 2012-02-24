package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.julnamoo.herimarque.client.soap.Item;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class TestAgeImgListSimpleRequest {

	String requestf;
	String reqMsg;
	String requesturl;
	String responsef;
	
	@Before
	public void setup() throws IOException
	{
		requestf = "request/age_img_list_request.xml";
		requesturl = "http://openapi.cha.go.kr/openapi/soap/crlts/AgeCrltsService";
		responsef = "test/age_img_list_response.xml";
		
		reqMsg = FileUtils.readFileToString(new File(requestf));
		reqMsg = reqMsg.replace("pageNum", "1");
		reqMsg = reqMsg.replace("size", "100000");
		reqMsg = reqMsg.replace("code", "11");
		reqMsg = reqMsg.replace("number", "00010000");
		reqMsg = reqMsg.replace("locale", "11");
	}
	
	@Test
	public void test() throws Exception
	{
		IntegrationClientAPI api = new IntegrationClientAPI();
		
		WebSvcType wstype = WebSvcType.SOAP;
		Map headerCnt = null;

		String rep = api.send(wstype, requesturl, reqMsg, headerCnt);
		
		FileUtils.writeStringToFile(new File(responsef), rep, "UTF-8");
		
		DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource(new FileReader(responsef));
		is.setCharacterStream(new StringReader(rep));
		Document doc = docbuilder.parse(is);
		
		List<Item> resultList = new ArrayList<Item>();
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
			System.out.println(instance.getItemNm() + ", " + instance.getListImageUrl() + ", " + instance.getImageUrl());
		}
		
		System.out.println("Total size : " + resultList.size());
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
