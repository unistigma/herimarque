package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Create the soap request for culture soap request api
 * @author Julie
 *
 */

public class CreateRequest {

	Logger logger = LoggerFactory.getLogger(CreateRequest.class.getSimpleName());

	/** Request information field**/
	public static String id;
	public static String serviceKey;
	//	public static String ageRequestURI;
	//	public static String areaRequestURI;
	public static String reqURI;

	/** For client setup **/
	private IntegrationClientAPI apiClient;
	private String service;

	/**
	 * Constructor for request runner.
	 * Must select the service of the type of the service.
	 * 
	 * Service parameter will be one of 'area' or 'age'.
	 * 
	 * @param service
	 * @throws Exception
	 */
	public CreateRequest(String service) throws Exception
	{

		apiClient = new IntegrationClientAPI();
		this.service = service;

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

		} catch (Exception e) 
		{
			logger.error(e.getMessage(), e.getCause());
		}finally
		{
			//release resources
			if(fis != null)
			{
				fis = null;
			}

			if(props != null)
			{
				props = null;
			}
		}
	}

	/**
	 * Set up the request soap envelope form for each service
	 * 
	 * @throws ParserConfigurationException 
	 */
	public void setupRequest() throws ParserConfigurationException
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
		}else if (service.equalsIgnoreCase(ServiceType.AREA.toString()))
		{
			//process for area code request
			
			rootElement.setAttribute("xmlns:ser", "http://service.areacrlts.crlts.cha/");
			document.appendChild(rootElement);
		}
	}

	private Element setHeader(Document document)
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
}
