package net.julnamoo.soap.test.IntegratedClient;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());
	
	public static void main( String[] args ) throws Exception {
		/*System.out.println( "Hello World Enterprise Application Client!" );

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

		SOAPHeader header = soapEnvelope.getHeader();
		SOAPElement headElement = header.addChildElement(soapEnvelope.createName("ComMsgHeader", "head", "http://apache.org/headers"));
		headElement.addChildElement("ServiceKey").addTextNode("J0dBIBcUMESTQ7Rs7bt4U+AMcATmUzND5pvbuT3u7LLSY1Qy0AwcZFgtFNuEtkFoE7Xb6WlA1pionwBEYCsJiQ==");

		SOAPBody body = soapEnvelope.getBody();
		SOAPElement bodyElement = body.addChildElement(soapEnvelope.createName("getAgeCrltsList", "open", "http://openapi.cha.go.kr/openapi/soap/crlts/AgeCrltsService"));
		SOAPElement beLstReq = bodyElement.addChildElement("AgeCrltsList");
		 */
		IntegrationClientAPI api = new IntegrationClientAPI();
		String filename = "";

		
//		filename = "age_request.xml";
		filename="spec_request.xml";
		
		WebSvcType wstype = WebSvcType.SOAP;
//		String url = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AgeCrltsService";
		String url = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AreaCrltsService";
		String reqStr = FileUtils.readFileToString(new File(filename));
		Map headerCnt = null;

		String rep = api.send(wstype, url, reqStr, headerCnt);
		
		FileUtils.writeStringToFile(new File("response.xml"), rep, "UTF-8");
		
	}
}
