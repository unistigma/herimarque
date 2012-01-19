package net.julnamoo.herimarque.client.soap;

import iros.gsb.constant.WebSvcType;
import iros.gsb.sbe.api.IntegrationClientAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Send the request to the requestURL with the requestMsg.
 * Then, parse the response msg and convert it to JSON then store it.
 * 
 * @author Julie_air
 *
 */
public class RequestThread implements Runnable{

	Logger logger = LoggerFactory.getLogger(RequestThread.class.getSimpleName());
	String requestURL;
	String requestMsg;
	
	public void run() 
	{
		IntegrationClientAPI client;
		
		try 
		{
			client = new IntegrationClientAPI();
		} catch (Exception e) 
		{
			logger.error("Cannot finish the request to {} with {}", requestURL, requestMsg);
			
			return;
		}
		
		String response = client.send(WebSvcType.SOAP, requestURL, requestMsg, null);
		
	}

	private void toJson(String response)
	{
		
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}
	
}
