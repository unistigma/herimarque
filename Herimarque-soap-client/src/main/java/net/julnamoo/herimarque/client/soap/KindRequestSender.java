package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;

import java.io.File;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

public class KindRequestSender extends RequestSender {

	Logger logger = LoggerFactory.getLogger(KindRequestSender.class);
	
	private String dmsg;
	public KindRequestSender(String url) throws Exception 
	{
		super();
		requestURI = url;
		dmsg = FileUtils.readFileToString(new File("request/kind_detail_request.xml"));
	}

	public void run() throws Exception
	{
		File workbookFile = new File("request/kind.xls");
		Workbook workBook = Workbook.getWorkbook(workbookFile);
		Sheet codeSheet = workBook.getSheet(0);
		
		String msgTemplate = FileUtils.readFileToString(new File("request/kind_list_request.xml"));
		
		List<Item> kindList = new ArrayList<Item>();
		for(int i = 0; i < codeSheet.getRows(); ++i)
		{
			Cell cell = codeSheet.getCell(0, i);
			
			String requestMsg = new String(msgTemplate);
			String itemCd = cell.getContents();
			
			if(cell.getType() == CellType.LABEL || cell.getType() == CellType.NUMBER)
			{
				requestMsg = requestMsg.replace("code", itemCd);
				requestMsg = requestMsg.replace("size", pageSize);
				requestMsg = requestMsg.replace("pagenum", "1");
				
				logger.info("Sending request with code num {}", cell.getContents());
				String response = client.send(WebSvcType.SOAP, requestURI, requestMsg, null);
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

					/** set field value **/
					Item instance = new Item();
					//set ageCd
					instance.setItemCd(itemCd);
					
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
					
					//get others value
					instance = getDetail(instance);
					kindList.add(instance);
					logger.info("Add new instance : {}, {}", instance.getCrltsNm(), instance.getCrltsNoNm());
				}
			}
		}
		
		logger.info("Kind List size is {}", kindList.size());
		Gson gson = new Gson();
		String res = gson.toJson(kindList);
		FileUtils.writeStringToFile(new File("response/kindList.json"), res);
	}
	
	private Item getDetail(Item item) throws ParserConfigurationException, SAXException, IOException
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
		
		return item;
	}
}
