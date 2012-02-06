package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

public class AgeRequestSender extends RequestSender {

	String dmsg;
	
	public AgeRequestSender(String url) throws Exception 
	{
		super();
		requestURI = url;
		dmsg = FileUtils.readFileToString(new File("request/age_detail_request.xml"));
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
				String ageCd = cell.getContents();
				
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
//				if(!checkNumber())
//				{
//					logger.info("Current request number is {}. Sleep this thread for a day.....", number);
//					logger.info("Set the request number to zero");
//					initNumber();
//					Thread.currentThread().sleep(1000*60);
//					logger.info("Awake the Thread! Continue to send the request");
//					
//					//for test
//					return;
//				}
				String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);
//				++number;
				
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
						} catch (Exception e) 
						{
							logger.error(e.getMessage(), e.getCause());
						}
					}
					
					instance = getDetail(instance);
					ageList.add(instance);
					logger.info("Add new instance : {}, {}", instance.getCrltsNm(), instance.getCrltsNoNm());
				}
			}
		}
		
		logger.info("Age List size is {}", ageList.size());
		Gson gson = new Gson();
		String res = gson.toJson(ageList);
		FileUtils.writeStringToFile(new File("response/ageList.json"), res);
		
	}
	
	public Item getDetail(Item item) throws ParserConfigurationException, SAXException, IOException
	{
		String requestMsg = dmsg.replace("code", item.getItemCd());
		requestMsg = requestMsg.replace("number", item.getCrltsNo());
		requestMsg = requestMsg.replace("locale", item.getCtrdCd());
		
		logger.info("Setting detail age request msg with {}, {}", item.getCrltsNm(), item.getCrltsNoNm());
		String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);
		
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(response));
		Document doc = db.parse(is);

		try
		{
			Node desc = doc.getElementsByTagName("crltsDc").item(0);
			item.setCrltsDc(desc.getTextContent());
		}catch (Exception e){
			
			item.setCrltsDc("");
		}
		
		try
		{
			Node guCd = doc.getElementsByTagName("signguCd").item(0);
			item.setSignguCd(guCd.getTextContent());
		}catch (Exception e) {	
			item.setSignguCd("");
		}
		
		try
		{
			Node guNm = doc.getElementsByTagName("signguNm").item(0);
			item.setSignguNm(guNm.getTextContent());
		}catch (Exception e) {
			item.setSignguNm("");
		}
		
<<<<<<< HEAD
=======
		try
		{
			Node imageYn = doc.getElementsByTagName("imageYn").item(0);
			item.setImageYn(imageYn.getTextContent());
		}catch (Exception e) {
			item.setImageYn("");
		}
		
		try
		{
			Node imageUrl = doc.getElementsByTagName("imageUrl").item(0);
			item.setImageUrl(imageUrl.getTextContent());
		}catch (Exception e) {
			item.setImageUrl("");
		}
		
>>>>>>> 1f92856f37eea6e0ead4c0023cd33d6a22e1fd66
		return item;
	}
}
