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

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;

public class AgeDetailRequestSender {

	private static Logger logger = LoggerFactory.getLogger(AgeDetailRequestSender.class.getSimpleName());
	
	private String requestURL = "";
	
	public AgeDetailRequestSender(String url)
	{
		this.requestURL = url;
	}
	
//	public void run()
//	{
//		DocumentBuilder tdb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//		InputSource tis = new InputSource(new FileInputStream(new File("response/ageList.json")));
//
//		Gson gson = new Gson();
//		List<Item> ageList = new ArrayList<Item>();
//		ageList = gson.fromJson(new FileReader(new File("response/ageList.json")), ArrayList.class);
//		
//		/** Get detail information of the item **/
//		String dreq = FileUtils.readFileToString(new File("request/age_detail_request.xml"));
//		String oldItemCd = null;
//		String oldCrltsNo = null;
//		String oldCtrdCd = null;
//		
//		for(Item item : ageList)
//		{
//			//get values
//			String itemCd = item.getItemCd();
//			String crltsNo = item.getCrltsNo();
//			String ctrdCd = item.getCtrdCd();
//			
//			//set parameters
//			if(dreq.contains("code"))
//			{
//				dreq = dreq.replace("code", itemCd);
//			}else
//			{
//				dreq = dreq.replace(oldItemCd, itemCd);
//			}
//			
//			if(dreq.contains("number"))
//			{
//				dreq = dreq.replace("number", crltsNo);
//			}else
//			{
//				dreq = dreq.replace(oldCrltsNo, crltsNo);
//			}
//			
//			if(dreq.contains("locale"))
//			{
//				dreq = dreq.replace("locale", ctrdCd);
//			}else
//			{
//				dreq = dreq.replace(oldCtrdCd, ctrdCd);
//			}
//			
//			oldItemCd = itemCd;
//			oldCrltsNo = crltsNo;
//			oldCtrdCd = ctrdCd;
//			
//			if(!checkNumber())
//			{
//				logger.info("Current request number is {}. Sleep this thread for a day.....", number);
//				logger.info("Set the request number to zero");
//				initNumber();
//				Thread.currentThread().sleep(1000*60);
//				logger.info("Awake the Thread! Continue to send the request");
//				
//				//for test
//				return;
//			}
//			
//			String response = client.send(WebSvcType.SOAP, requestURI, dreq, null);
//			
//			// Set the value to the instance
//			DocumentBuilder docbuilBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			InputSource is = new InputSource(new StringReader(response));
//			Document doc = docbuilBuilder.parse(is);
//			
//			//Parse the item xml
//			Node iNode = doc.getElementsByTagName("return").item(0);
//			NodeList fields = iNode.getChildNodes();
//			for(int fnum = 0; fnum < fields.getLength(); ++fnum)
//			{
//				Node field = fields.item(fnum);
//				
//				String fname = field.getNodeName();
//				String value = field.getTextContent();
//				
//				try
//				{
//					//set detail information
//					item = setValue(item, fname, value);
//				}catch (Exception e) 
//				{
//					logger.error(e.getMessage(), e.getCause());
////					logger.info("Remove {}, name is {}", item.getCrltsNoNm(), item.getCrltsNm());
//					// If there is blocking in set the value, remove the object from the list
//					ageList.remove(item);
//				}
//			}
//		}
//	}
}
