package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGenerateRequestTemplate {

	String requestURI;
	String pageSize;
	String areaCode;
	IntegrationClientAPI client;
	
	@Before
	public void setup() throws Exception
	{
		pageSize = "9999";
		areaCode = "11";
		client = new IntegrationClientAPI();
	}
	
	
	@Test
	public void buildAgeRequest() throws Exception
	{
		requestURI = "http://openapi.cha.go.kr/openapi/soap/crlts/AgeCrltsService";
		String xmlPath = "age_list_request.xml";
		String msgTemplate = FileUtils.readFileToString(new File(xmlPath));
		String requestMsg = msgTemplate.replace("size", pageSize);
		
		File workbookFile = new File("age.xls");
		Workbook workBook = Workbook.getWorkbook(workbookFile);
		Sheet codeSheet = workBook.getSheet(0);
		
		String pastNum = null;
		for(int i = 0; i < codeSheet.getRows(); ++i)
		{
			Cell cell = codeSheet.getCell(0, i);

			//get the code number
			if(cell.getType() == CellType.LABEL || cell.getType() == CellType.NUMBER)
			{
				if(requestMsg.contains("code"))
				{
					requestMsg = requestMsg.replace("code", cell.getContents());
				}else if(pastNum != null)
				{
					requestMsg = requestMsg.replace(pastNum, cell.getContents());
				}
				
				pastNum = cell.getContents();
				requestMsg = requestMsg.replace("pagenum", "1");
				
				System.out.println("Sending request with code num"+ cell.getContents());
				//consider the servicekey
//				String  response = "";
				String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);
				
				String respFile = "response/age_response_" + pastNum + ".txt";
				FileUtils.writeStringToFile(new File(respFile), response);
				
			}
		}
	}
	
//	@Test
	public void buildAreaRequest() throws IOException, BiffException
	{
		//looping with the ctrdCd and dynamic pageMg
		
//		ClassLoader cl = Thread.currentThread().getContextClassLoader();
//		if(cl == null) cl = ClassLoader.getSystemClassLoader();
//		
//		String xmlPath = cl.getResource("").getPath() + "/area_list_request.xml";
		requestURI = "http://openapi.cha.go.kr/openapi/soap/crlts/AreaCrltsService";
		String xmlPath = "area_list_request.xml";
		String msgTemplate = FileUtils.readFileToString(new File(xmlPath));
		String requestMsg = msgTemplate.replace("size", pageSize);
		
		File workbookFile = new File("area.xls");
		Workbook workBook = Workbook.getWorkbook(workbookFile);
		Sheet codeSheet = workBook.getSheet(0);
		
		String pastNum = null;
		for(int i = 0; i < codeSheet.getRows(); ++i)
		{
			Cell cell = codeSheet.getCell(0, i);

			//get the code number
			if(cell.getType() == CellType.LABEL || cell.getType() == CellType.NUMBER)
			{
				if(requestMsg.contains("code"))
				{
					requestMsg = requestMsg.replace("code", cell.getContents());
				}else if(pastNum != null)
				{
					requestMsg = requestMsg.replace(pastNum, cell.getContents());
				}
				
				pastNum = cell.getContents();
				requestMsg = requestMsg.replace("pagenum", "1");
				
				System.out.println("Sending request with code num"+ cell.getContents());
				//consider the servicekey
				String  response = "";
//				String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);
				
				String respFile = "response_" + pastNum + ".txt";
				FileUtils.writeStringToFile(new File(respFile), response);
				
			}
		}
	}
	
	@After
	public void teardown()
	{
		
	}
}
