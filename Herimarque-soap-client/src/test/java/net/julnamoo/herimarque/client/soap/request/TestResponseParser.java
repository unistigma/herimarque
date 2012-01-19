package net.julnamoo.herimarque.client.soap.request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestResponseParser {

	String fpath;
	BufferedReader br;
	
	@Before
	public void setup()
	{
	}
	
	@Test
	public void testResponseParsing() throws IOException
	{
		File responseDir = new File("response");
		if(responseDir.isDirectory())
		{
			File[] response = responseDir.listFiles();
			
			for(File f : response)
			{
				System.out.println(f.getPath());
				br = new BufferedReader(new FileReader(f));
				System.out.println(br.readLine());
			}
		}
	}
	
	@After
	public void teardowns()
	{
		
	}
	
}
