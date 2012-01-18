package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestListRequest {

	CreateListRequest clr;
	
	@Before
	public void setup() 
	{
		clr = new CreateListRequest("area", "11");
	}
	
	@Test
	public void testSendRequest() throws ParserConfigurationException, TransformerException, IOException
	{
		clr.buildRequest();
		String msg = clr.sendRequest();
		
		FileUtils.writeStringToFile(new File("responsetest.xml"), msg, "utf-8");
	}
	
	@After
	public void teardown()
	{
		clr = null;
	}
}
