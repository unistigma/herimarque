package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;

public class AgeRequestSender extends RequestSender {

	public AgeRequestSender(String url) throws Exception 
	{
		super();
		requestURI = url;
	}

	public void run() throws Exception
	{
		String xmlPath = "request/age_list_request.xml";
		String msgTemplate = FileUtils.readFileToString(new File(xmlPath));
		String requestMsg = msgTemplate.replace("size", pageSize);
		
		File workbookFile = new File("request/age.xls");
		Workbook workBook = Workbook.getWorkbook(workbookFile);
		Sheet codeSheet = workBook.getSheet(0);
		
		List<Item> ageList = new ArrayList<Item>();
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
//				String  response = "";
				if(!checkNumber())
				{
					logger.info("Current request number is {}. Sleep this thread for a day.....", number);
					logger.info("Set the request number to zero");
					initNumber();
					Thread.currentThread().sleep(1000*60);
					logger.info("Awake the Thread! Continue to send the request");
					
					//for test
					return;
				}
				String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);
				++number;
				
				//save the naive response to string
//				String respFile = "response/age_response_" + pastNum + ".txt";
//				FileUtils.writeStringToFile(new File(respFile), response);
				
				/** response to Item **/
				DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(response));
				Document doc = docbuilder.parse(is);

				// get items
				NodeList items = doc.getElementsByTagName("item");
				for(int i_num = 0; i_num < items.getLength(); ++i_num)
				{
					Node item = items.item(i_num);
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
							ageList.add(instance);
						} catch (Exception e) 
						{
							logger.error(e.getMessage(), e.getCause());
						}
					}
				}
			}
		}
		
		logger.info("Age List size is {}", ageList.size());
		Gson gson = new Gson();
		String res = gson.toJson(ageList);
		FileUtils.writeStringToFile(new File("response/ageList.json"), res);
		
		/** for test details **/
//		DocumentBuilder tdb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//		InputSource tis = new InputSource(new FileInputStream(new File("response/ageList.json")));
//
//		Gson gson = new Gson();
//		List<Item> ageList = new ArrayList<Item>();
//		ageList = gson.fromJson(new FileReader(new File("response/ageList.json")), ArrayList.class);
		
		/** Get detail information of the item **/
		String dreq = FileUtils.readFileToString(new File("request/age_detail_request.xml"));
		String oldItemCd = null;
		String oldCrltsNo = null;
		String oldCtrdCd = null;
		
		for(Item item : ageList)
		{
			//get values
			String itemCd = item.getItemCd();
			String crltsNo = item.getCrltsNo();
			String ctrdCd = item.getCtrdCd();
			
			//set parameters
			if(dreq.contains("code"))
			{
				dreq = dreq.replace("code", itemCd);
			}else
			{
				dreq = dreq.replace(oldItemCd, itemCd);
			}
			
			if(dreq.contains("number"))
			{
				dreq = dreq.replace("number", crltsNo);
			}else
			{
				dreq = dreq.replace(oldCrltsNo, crltsNo);
			}
			
			if(dreq.contains("locale"))
			{
				dreq = dreq.replace("locale", ctrdCd);
			}else
			{
				dreq = dreq.replace(oldCtrdCd, ctrdCd);
			}
			
			oldItemCd = itemCd;
			oldCrltsNo = crltsNo;
			oldCtrdCd = ctrdCd;
			
			if(!checkNumber())
			{
				logger.info("Current request number is {}. Sleep this thread for a day.....", number);
				logger.info("Set the request number to zero");
				initNumber();
				Thread.currentThread().sleep(1000*60);
				logger.info("Awake the Thread! Continue to send the request");
				
				//for test
				return;
			}
			
			String response = client.send(WebSvcType.SOAP, requestURI, dreq, null);
			
			// Set the value to the instance
			DocumentBuilder docbuilBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(response));
			Document doc = docbuilBuilder.parse(is);
			
			//Parse the item xml
			Node iNode = doc.getElementsByTagName("return").item(0);
			NodeList fields = iNode.getChildNodes();
			for(int fnum = 0; fnum < fields.getLength(); ++fnum)
			{
				Node field = fields.item(fnum);
				
				String fname = field.getNodeName();
				String value = field.getTextContent();
				
				try
				{
					//set detail information
					item = setValue(item, fname, value);
				}catch (Exception e) 
				{
					logger.error(e.getMessage(), e.getCause());
//					logger.info("Remove {}, name is {}", item.getCrltsNoNm(), item.getCrltsNm());
					// If there is blocking in set the value, remove the object from the list
					ageList.remove(item);
				}
			}
		}
	}
}
