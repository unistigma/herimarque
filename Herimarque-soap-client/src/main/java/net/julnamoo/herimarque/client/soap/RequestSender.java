package net.julnamoo.herimarque.client.soap;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import iros.gsb.sbe.api.IntegrationClientAPI;


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

	Logger logger = LoggerFactory.getLogger(RequestSender.class.getSimpleName());
	
	/** Fields to build and send the request **/
	String requestURI;
	String pageSize;
	IntegrationClientAPI client;
	
	int number;

	public RequestSender() throws Exception
	{
		pageSize = "9999";
		number = 0; //number of request. 100 request per Day
		client = new IntegrationClientAPI();
	}

	protected Item setValue(Item item, String field, String value) throws Exception
	{
		//convert each field to object field with inflection api
		Class target = null;

		try 
		{
			target = Class.forName(item.getClass().getName());

		} catch (ClassNotFoundException e) 
		{
			//internal error
			e.printStackTrace();
			throw new Exception("Cannot convert object to Item instance");
		}

		Field[] fields = target.getDeclaredFields();

		for(Field f : fields)
		{
			if(f.getName().equals(field))
			{
				f.setAccessible(true);

				f.set(item, value);
				
//				logger.debug("Set ({}, {})", item, value);
				return item;
			}
		}

		return null;
	}

	
	public boolean checkNumber()
	{
		return ((number == 100 | number > 99) ? false : true);
	}

	
	public void initNumber()
	{
		number = 0;
	}
}
