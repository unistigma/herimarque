package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Create the soap request for culture soap request api
 * @author Julie
 *
 */

public class CreateListRequest {

	Logger logger = LoggerFactory.getLogger(CreateListRequest.class.getSimpleName());

	/** Request information field**/
	public static String id;
	public static String serviceKey;
	public static String reqURI;
	public static String code;

	/** For client setup **/
	private IntegrationClientAPI apiClient;
	private String service;
	private Document reqEnvelope;
	private String pageMgSize = "100";

	/**
	 * Constructor for request runner.
	 * Must select the service of the type of the service.
	 * 
	 * Service parameter will be one of 'area' or 'age'.
	 * 
	 * @param service
	 * @throws Exception
	 */
	public CreateListRequest(String service, String code) 
	{

		try 
		{
			apiClient = new IntegrationClientAPI();
		} catch (Exception e) 
		{
			logger.error(e.getMessage(), e.getCause());
			
			return;
		}
		this.service = service;
		this.code = code;

		init();
		logger.info("Success to set up request information of {} with {} service", id, service);
	}

	/**
	 * Loading the resources from property file
	 * 
	 */
	protected void init() 
	{
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		if(cl == null) cl = ClassLoader.getSystemClassLoader();

		URL url = cl.getResource("");
		Properties props = new Properties();

		FileInputStream fis = null;

		try
		{
			fis = new FileInputStream(url.getPath() + "\\request.properties");
			props.load(new BufferedInputStream(fis));
		} catch (Exception e) 
		{
			logger.error(e.getMessage(), e.getCause());
		}
		
		/** set up request parameter **/
		logger.debug("Set up request parameter");
		String id = props.getProperty("req.id", "netinamu42");
		this.id = id;

		String serviceKey = props.getProperty("req.key", "6i1zuQWddVTMFSqlHyhnbw8VL7uvfuHHt3Pth12H3VuFrUvdzFRcfYBZRfTE0Eidf/hV3otAz36MR1F/VDWUDw==");
		this.serviceKey = serviceKey;

		if(service.equalsIgnoreCase(ServiceType.AGE.toString()))
		{
			this.reqURI = props.getProperty("url.age", "http://openapi.cha.go.kr:80/openapi/soap/crlts/AgeCrltsService");

		}else if (service.equalsIgnoreCase(ServiceType.AREA.toString()))
		{
			this.reqURI = props.getProperty("url.area", "http://openapi.cha.go.kr:80/openapi/soap/crlts/AreaCrltsService");
		}

		logger.debug("Set up request id with {}", this.id);
	}

	/**
	 * Set up the request soap envelope form for each service
	 * 
	 * @throws ParserConfigurationException 
	 */
	public void buildRequest() throws ParserConfigurationException
	{

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		// set up root element
		Element rootElement = document.createElement("soapenv:Envelope");
		rootElement.setAttribute("xmlns:soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		rootElement.setAttribute("xmlns:head", "http://apache.org/headers");

		if(service.equalsIgnoreCase(ServiceType.AGE.toString()))
		{
			//process for age code request
			rootElement.setAttribute("xmlns:ser", "http://service.agecrlts.crlts.cha/");
			document.appendChild(rootElement);

			document.appendChild(getHeader(document));
			document.appendChild(getAgeBody(document));
		}else if (service.equalsIgnoreCase(ServiceType.AREA.toString()))
		{
			//process for area code request
			rootElement.setAttribute("xmlns:ser", "http://service.areacrlts.crlts.cha/");
			document.appendChild(rootElement);

			document.appendChild(getHeader(document));
			document.appendChild(getAreaBody(document));
		}

		// set my document for sending the request
		reqEnvelope = document;
	}

	private Element getHeader(Document document)
	{
		Element headerEnv = document.createElement("soapenv:Header");
		Element header = document.createElement("head:ComMsgHeader");

		Element requestMsgID = document.createElement("RequestMsgID");
		requestMsgID.setTextContent(this.id);
		header.appendChild(requestMsgID);

		Element serviceKey = document.createElement("ServiceKey");
		serviceKey.setTextContent(this.serviceKey);
		header.appendChild(serviceKey);

		headerEnv.appendChild(header);

		return headerEnv;
	}

	private Element getAreaBody(Document document)
	{
		Element bodyRoot = document.createElement("soapenv:Body");

		//set up the service operation for getting list
		Element operation = document.createElement("ser:getAreaCrltsList");

		Element arg = document.createElement("arg0");
		Element ctrdCd = document.createElement("ctrdCd");

		// need to read excel and set the code number
		ctrdCd.setTextContent(code);
		arg.appendChild(ctrdCd);

		Element pageMg = document.createElement("pageMg");
		pageMg.setTextContent(pageMgSize);
		arg.appendChild(pageMg);

		operation.appendChild(arg);
		bodyRoot.appendChild(operation);

		return bodyRoot;
	}

	private Element getAgeBody(Document document)
	{
		Element bodyRoot = document.createElement("soapenv:Body");

		//set up the service operation for getting list
		Element operation = document.createElement("ser:getAgeCrltsList");

		Element arg = document.createElement("arg0");
		Element ctrdCd = document.createElement("ctrdCd");

		// need to read excel and set the code number
		ctrdCd.setTextContent(code);
		arg.appendChild(ctrdCd);

		Element pageMg = document.createElement("pageMg");
		pageMg.setTextContent(pageMgSize);
		arg.appendChild(pageMg);

		operation.appendChild(arg);
		bodyRoot.appendChild(operation);

		return bodyRoot;
	}

	/**
	 * Transform the document to SOAP request msg then send it.
	 * Return the response msg.
	 * 
	 * buildRequest() must be called before this method.
	 * @return
	 * @throws TransformerException
	 */
	public String sendRequest() throws TransformerException
	{
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer transformer = transfac.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		DOMSource source = new DOMSource(reqEnvelope);
		transformer.transform(source, result);

		String msg = writer.toString();
		if(this.apiClient != null)
		{
			String response = apiClient.send(WebSvcType.SOAP, this.reqURI, msg, null);
			return response;
		}else
		{
			return null;
		}
	}
}
