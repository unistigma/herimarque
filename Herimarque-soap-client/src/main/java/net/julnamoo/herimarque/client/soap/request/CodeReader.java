package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read the excel file and then return the integer list for code numbers
 * 
 * @author Julie_air
 *
 */
public class CodeReader {

	Logger logger = LoggerFactory.getLogger(CodeReader.class.getSimpleName());
	
	String service;
	private File dataFile;
	List<String> codeList;
	
	public CodeReader(String service)
	{
		this.service = service;
		codeList = new ArrayList<String>();
	}
	
	/**
	 * Load and read the excel file and add numbers to the list as String.
	 *  
	 */
	public void getCodeNumberList()
	{
		// It contains only two columns. For getting code number, read only the first column
		
		if(this.service.equalsIgnoreCase(ServiceType.AGE.toString()))
		{
			this.dataFile = new File("age.xls");
			
		}else if (this.service.equalsIgnoreCase(ServiceType.AREA.toString()))
		{
			this.dataFile = new File("area.xls");
		}

		Workbook workbook = null;
		Sheet sheet = null;
		
		try 
		{
			workbook = Workbook.getWorkbook(dataFile);
		} catch (BiffException e) 
		{
			logger.error(e.getMessage(), e.getCause());
			
			sheet = null;
			workbook = null;
			dataFile = null;
			return;
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e.getCause());
			
			sheet = null;
			workbook = null;
			dataFile = null;
			return;
		}
		
		// get the sheet and add code numbers in the list
		sheet = workbook.getSheet(0);
		for(int i = 0; i < sheet.getRows(); ++i)
		{
			Cell cell = sheet.getCell(0, i);
			CellType type = cell.getType();
			
			//check the type of the cell
			if(type == CellType.NUMBER || type == CellType.LABEL)
			{
				codeList.add(cell.getContents());
			}
		}
	}

	public List<String> getCodeList() {
		return codeList;
	}
	
	
}
