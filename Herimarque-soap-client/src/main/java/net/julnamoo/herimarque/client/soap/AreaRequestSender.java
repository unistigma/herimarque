package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class AreaRequestSender extends RequestSender {

	Logger logger = LoggerFactory.getLogger(AreaRequestSender.class.getSimpleName());
	
	public AreaRequestSender(String requestURI) throws Exception 
	{
		super(requestURI);
	}


	public List<String> getCodes() throws BiffException, IOException  
	{
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
				codes.add(cell.getContents());
			}
		}

		logger.debug("total codes count : {}", codes.size());
		logger.info("Setting String list to Area codes");
		return codes;
	}

	/**
	 * Exception throwing will be fixed to be build xml
	 * 
	 * After build the xml then it passed to thread Pool.
	 * 
	 */
	public String buildRequest() throws IOException
	{
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if(cl == null) cl = ClassLoader.getSystemClassLoader();

		//load wsdl request url
		FileInputStream fis = new FileInputStream(cl.getResource("").getPath() + 
				"/request.properties");
		Properties properties = new Properties();
		properties.load(fis);
		String requestUrl = properties.getProperty("url.area");
		
		//load the template
		String xmlPath = cl.getResource("").getPath() + "/area_list_request.xml";
		String msgTemplate = FileUtils.readFileToString(new File(xmlPath));
		
		
		//setting page size(pageMg) and pagenumber
		String requestMsg = msgTemplate.replace("size", pageSize);
		requestMsg = requestMsg.replace("pagenum", "1");
		
		String pastNum = null;
		for(String code : codes)
		{
			//setting code num
			if(requestMsg.contains("code"))
			{
				requestMsg = requestMsg.replace("code", code);
			}else if (pastNum != null)
			{
				requestMsg = requestMsg.replace(pastNum, code);
			}
			
			pastNum = code;
			
			logger.debug("request msg : {}", requestMsg);
			logger.info("Finish to build the requet xml with {} code", code);
			
			logger.info("Start request {} code thread", code);
			String response = sendRequest();
			
		}
		return null;
	}


	public String pasreResponse(String response) 
	{
		return null;
	}


	@Override
	public String pasreResponse() {
		// TODO Auto-generated method stub
		return null;
	}


	public void run() throws Exception
	{
//		requestURI = "http://openapi.cha.go.kr/openapi/soap/crlts/AreaCrltsService";
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
				
				String respFile = "are_response_" + pastNum + ".txt";
				FileUtils.writeStringToFile(new File(respFile), response);
				
			}
		}
	}
}
