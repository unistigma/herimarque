package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class TestAreaListSimpleRequest {

	String requestf;
	String reqMsg;
	String requesturl;
	String responsef;
	
	@Before
	public void setup() throws IOException
	{
		requestf = "request/area_list_request.xml";
		requesturl = "http://openapi.cha.go.kr/openapi/soap/crlts/AreaCrltsService";
		responsef = "test/area_list_response.xml";
		
		reqMsg = FileUtils.readFileToString(new File(requestf));
		reqMsg = reqMsg.replace("code", "11");
		reqMsg = reqMsg.replace("size", "100000");
		reqMsg = reqMsg.replace("pagenum", "1");
		
	}
	
	@Test
	public void test() throws Exception
	{
		IntegrationClientAPI api = new IntegrationClientAPI();
		
		WebSvcType wstype = WebSvcType.SOAP;
		Map headerCnt = null;

		String rep = api.send(wstype, requesturl, reqMsg, headerCnt);
		
		FileUtils.writeStringToFile(new File(responsef), rep, "UTF-8");
	}
}
