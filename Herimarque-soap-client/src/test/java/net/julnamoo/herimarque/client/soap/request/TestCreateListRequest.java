package net.julnamoo.herimarque.client.soap.request;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCreateListRequest {

	CreateListRequest clr;
	
	@Before
	public void setup() 
	{
		clr = new CreateListRequest("area", "11");
	}
	
	@Test
	public void testSendRequest() throws ParserConfigurationException, TransformerException
	{
		clr.buildRequest();
		String msg = clr.sendRequest();
		
		System.out.println(msg);
	}
	
	@After
	public void teardown()
	{
		clr = null;
	}
}
