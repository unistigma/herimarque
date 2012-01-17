package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());

	public static void main( String[] args ) throws Exception {
		/** Request information field**/
		String id;
		String serviceKey;
		String reqURI;
		
		/** For client setup **/
		IntegrationClientAPI apiClient;
		String service;
		
		apiClient = new IntegrationClientAPI();
		service = "area";
		id = "netinamu42";
		serviceKey = "6i1zuQWddVTMFSqlHyhnbw8VL7uvfuHHt3Pth12H3VuFrUvdzFRcfYBZRfTE0Eidf/hV3otAz36MR1F/VDWUDw==";
		
		reqURI = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AreaCrltsService";

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		// set up root element
		Element rootElement = document.createElement("soapenv:Envelope");
		rootElement.setAttribute("xmlns:soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		rootElement.setAttribute("xmlns:head", "http://apache.org/rootElements");
		rootElement.setAttribute("xmlns:ser", "http://service.areacrlts.crlts.cha/");
		document.appendChild(rootElement);


		//set up header parameter
		rootElement.appendChild(setupHeader(document));

		//set up body parameter
		rootElement.appendChild(setupBody(document));

		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer transformer = transfac.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		//		StreamResult result = new StreamResult(new FileWriter(new File("request.xml")));
		//		StreamResult result = new StreamResult(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		DOMSource source = new DOMSource(document);
		transformer.transform(source, new StreamResult(writer));


		String msg = writer.toString();

		String rcv = apiClient.send(WebSvcType.SOAP, reqURI, msg, null);
		System.out.println(rcv);
	}
	
	private static Element setupHeader(Document document)
	{
		Element headerEnv = document.createElement("soapenv:Header");
		Element header = document.createElement("head:ComMsgHeader");
		
		Element requestMsgID = document.createElement("RequestMsgID");
		requestMsgID.setTextContent("netinamu42");
		header.appendChild(requestMsgID);
		
		Element serviceKey = document.createElement("ServiceKey");
		serviceKey.setTextContent("6i1zuQWddVTMFSqlHyhnbw8VL7uvfuHHt3Pth12H3VuFrUvdzFRcfYBZRfTE0Eidf/hV3otAz36MR1F/VDWUDw==");
		header.appendChild(serviceKey);
		
		headerEnv.appendChild(header);
		
		return headerEnv;
	}
	
	private static Element setupBody(Document document)
	{
		Element bodyRoot = document.createElement("soapenv:Body");
		
		//set up the service operation for getting list
		Element operation = document.createElement("ser:getAreaCrltsList");
		
		Element arg = document.createElement("arg0");
		Element ctrdCd = document.createElement("ctrdCd");
		
		// need to read excel and set the code number
		ctrdCd.setTextContent("11");
		arg.appendChild(ctrdCd);
		
		Element pageMg = document.createElement("pageMg");
		pageMg.setTextContent("100");
		arg.appendChild(pageMg);
		
		operation.appendChild(arg);
		bodyRoot.appendChild(operation);
		
		return bodyRoot;
	}
}
