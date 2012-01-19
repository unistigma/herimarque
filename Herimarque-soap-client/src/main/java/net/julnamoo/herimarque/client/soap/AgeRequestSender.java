package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;

import java.io.File;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;

public class AgeRequestSender extends RequestSender {

	public AgeRequestSender(String requestURI) throws Exception 
	{
		super(requestURI);
	}

	public List<String> getCodes() 
	{
		
		return null;
	}

	public String buildRequest() 
	{
		return null;
	}

	public String pasreResponse() 
	{
		return null;
	}

	public void run() throws Exception
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
	
}
