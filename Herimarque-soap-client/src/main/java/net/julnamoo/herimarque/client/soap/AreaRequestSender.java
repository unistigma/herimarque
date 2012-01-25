package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;

public class AreaRequestSender extends RequestSender{

	Logger logger = LoggerFactory.getLogger(AreaRequestSender.class.getSimpleName());

	public AreaRequestSender(String url) throws Exception 
	{
		super();
		requestURI = url;
	}

	public void run() throws Exception
	{
		String xmlPath = "request/area_list_request.xml";
		String msgTemplate = FileUtils.readFileToString(new File(xmlPath));
		String requestMsg = msgTemplate.replace("size", pageSize);

		File workbookFile = new File("request/area.xls");
		Workbook workBook = Workbook.getWorkbook(workbookFile);
		Sheet codeSheet = workBook.getSheet(0);

		List<Item> areaList = new ArrayList<Item>();
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

				logger.info("Sending request with code num {}", cell.getContents());
				//consider the servicekey
				//String  response = "";
				String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);

				//String respFile = "response\\area_list_" + pastNum + ".txt";
				//FileUtils.writeStringToFile(new File(respFile), response);

				/** response to Item **/
				DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = docbuilder.parse(is);

				// get items
				NodeList items = doc.getElementsByTagName("item");
				for(int i_num = 0; i_num < items.getLength(); ++i_num)
				{
					Node item = items.item(i);
					NodeList subs = item.getChildNodes();

					//set field value
					Item instance = new Item();
					for(int j = 0; j < subs.getLength(); ++j)
					{
						Node sub = subs.item(j);

						String field = sub.getNodeName();
						String value = sub.getTextContent();

						try 
						{
							instance = setValue(instance, field, value);
							areaList.add(instance);
						} catch (Exception e) 
						{
							logger.error(e.getMessage(), e.getCause());
						}
					}
				}
			}
		}
		
		logger.info("Area List size is {}", areaList.size());
		Gson gson = new Gson();
		String res = gson.toJson(areaList);
		FileUtils.writeStringToFile(new File("response/areaList.json"), res);
	}
}