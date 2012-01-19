package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import com.google.gson.Gson;

/**
 * Build the request xml with each code.
 * It needs modules; excel reader and each xml builder for each service.
 * After finish to build xml then send the request and parse it.
 * Also, convert item value to JSON object with Item POJO.
 * 
 * Implementation class follows for each service 
 * @author Julie_air
 *
 */

public abstract class RequestSender {
	
	/** Fields to build and send the request and parse the response **/
	//Some of them are must be SET!
	String requestURI;
	List<String> codes;
	String pageSize;
	String reqMsg;
	List<Item> item;
	Gson gson;
	
	public RequestSender(String requestURI) throws Exception
	{
		pageSize = "9999";
		this.requestURI = requestURI;
	}
	
	//read codes from the excel
	public abstract List<String> getCodes() throws IOException, BiffException;
	
	//write the request SOAP xml with each template written with service and operation
	//The file name formatted to serivce_operation_request.xml, like area_list_request.xml
	public abstract String buildRequest() throws IOException;
	
//	//send the request to the wsdl server and set the response msg to resMsg field.
//	public String sendRequest()
//	{
//		return client.send(WebSvcType.SOAP, requestURI, reqMsg, null);
//	}
	
	//Response msg is converted to DOM then transfer to json or lucene document.
//	public abstract String pasreResponse();
	
}
