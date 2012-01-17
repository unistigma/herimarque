package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCreateRequestJDOM {

	/** Request information field**/
	public String id;
	public String serviceKey;
	public String reqURI;
	
	/** For client setup **/
	private IntegrationClientAPI apiClient;
	private String service;
	
	@Before
	public void setup() throws Exception
	{
		apiClient = new IntegrationClientAPI();
		service = "area";
		id = "netinamu42";
		serviceKey = "6i1zuQWddVTMFSqlHyhnbw8VL7uvfuHHt3Pth12H3VuFrUvdzFRcfYBZRfTE0Eidf/hV3otAz36MR1F/VDWUDw==";
	}
	
	@Test
	public void areaReqTest() throws JDOMException, IOException
	{
		reqURI = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AreaCrltsService";
		
		SAXBuilder builder = new SAXBuilder();
//		Document document = builder.build(new File("request.xml"));
		Document document = new Document();
		// set up root element
		Element rootElement = new Element("soapenv:Envelope");
		rootElement.setAttribute("xmlns:soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		rootElement.setAttribute("xmlns:head", "http://apache.org/rootElements");
		rootElement.setAttribute("xmlns:ser", "http://service.areacrlts.crlts.cha/");
		document.setRootElement(rootElement);
		
		//set up header parameter
		rootElement.addContent(setupHeader());
		
		//set up body parameter
		rootElement.addContent(setupBody());
	
		XMLOutputter out = new XMLOutputter();
		out.output(document, new PrintWriter(new File("request.xml")));
	}
	
	private Element setupHeader()
	{
		Element headerEnv = new Element("soapenv:Header");
		Element header = new Element("head:ComMsgHeader");
		
		Element requestMsgID = new Element("RequestMsgID");
		requestMsgID.setText(this.id);
		header.addContent(requestMsgID);
		
		Element serviceKey = new Element("ServiceKey");
		serviceKey.setText(this.serviceKey);
		header.addContent(serviceKey);
		
		headerEnv.addContent(header);
		
		return headerEnv;
	}
	
	private Element setupBody()
	{
		Element bodyRoot = new Element("soapenv:Body");
		
		//set up the service operation for getting list
		Element operation = new Element("ser:getAreaCrltsList");
		
		Element arg = new Element("arg0");
		Element ctrdCd = new Element("ctrdCd");
		
		// need to read excel and set the code number
		ctrdCd.setText("11");
		arg.addContent(ctrdCd);
		
		Element pageMg = new Element("pageMg");
		pageMg.setText("100");
		arg.addContent(pageMg);
		
		operation.addContent(arg);
		bodyRoot.addContent(operation);
		
		return bodyRoot;
	}
	
	@After
	public void teardown()
	{
		apiClient = null;
	}
	
}
