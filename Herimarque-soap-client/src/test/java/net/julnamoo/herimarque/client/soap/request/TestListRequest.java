package net.julnamoo.herimarque.client.soap.request;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jsoup.Jsoup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TestListRequest {

	CreateListRequest clr;
	
	@Before
	public void setup() 
	{
		clr = new CreateListRequest("area", "11");
	}
	
	@Test
	public void testSendRequest() throws ParserConfigurationException, TransformerException, IOException, SAXException
	{
		clr.buildRequest();
		String msg = clr.sendRequest();
		
//		FileUtils.writeStringToFile(new File("responsetest.xml"), msg, "utf-8");
		
		/** Test to parse the response **/
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(msg));
		Document responseDoc = builder.parse(is);
		
		NodeList items = responseDoc.getElementsByTagName("item");
		
		System.out.println("total Size : " + items.getLength());
//		for(int i = 0; i < items.getLength(); ++i)
//		{
//			System.out.println(items.item(i).getFirstChild().getTextContent());
//		}
	}
	
	@After
	public void teardown()
	{
		clr = null;
	}
}
