package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Read the excel file and then return the integer list for code numbers
 * 
 * @author Julie_air
 *
 */
public class CodeReader {

	String service;
	private File dataFile;
	List codeList;
	
	public CodeReader(String service)
	{
		this.service = service;
		loadFile();
		codeList = new ArrayList<Integer>();
	}
	
	private void loadFile()
	{
		if(this.service.equalsIgnoreCase(ServiceType.AGE.toString()))
		{
			this.dataFile = new File("age.xls");
			
		}else if (this.service.equalsIgnoreCase(ServiceType.AREA.toString()))
		{
			this.dataFile = new File("area.xls");
		}
	}
}
