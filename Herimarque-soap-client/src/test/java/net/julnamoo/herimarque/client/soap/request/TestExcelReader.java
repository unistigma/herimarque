package net.julnamoo.herimarque.client.soap.request;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestExcelReader {

	File workbookFile;
	Workbook workbook;
	Sheet sheet;
	
	@Before
	public void setup() throws BiffException, IOException
	{
		workbookFile = new File("area.xls");
		workbook = Workbook.getWorkbook(workbookFile);
		sheet = workbook.getSheet(0);
	}
	
	@Test
	public void buildList()
	{
		// It contains only two columns. For getting code number, read only the first column
		for(int i = 0; i < sheet.getRows(); ++i)
		{
			Cell cell = sheet.getCell(0, i);
			CellType type = cell.getType();
			
			//check the type of the cell
			if(type == CellType.NUMBER || type == CellType.LABEL)
			{
				System.out.println(cell.getContents());
			}
		}
	}
	
	@After
	public void teardown()
	{
		
	}
}
