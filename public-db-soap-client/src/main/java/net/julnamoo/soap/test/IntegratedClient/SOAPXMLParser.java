package net.julnamoo.soap.test.IntegratedClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;

public class SOAPXMLParser {

	String fname;
	
	public SOAPXMLParser(String fname) 
	{
		this.fname = fname;
	}
	
	public void getItem()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(fname)));
//			DocumentBuilderFactory buildFactory = 
		}catch(IOException e)
		{
			
		}
		
		
	}
}
