package net.julnamoo.herimarque.client.soap.request;

import iros.gsb.constant.WebSvcType;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Information for soap client.
 * @author Julie_air
 *
 */
public class Constants {
	
	Logger logger = LoggerFactory.getLogger(Constants.class.getSimpleName());

	/** Set up default value **/
	public static WebSvcType serviceType = WebSvcType.SOAP;
	
//	public static String julID = "netinamu42";
//	public static String julKey = "6i1zuQWddVTMFSqlHyhnbw8VL7uvfuHHt3Pth12H3VuFrUvdzFRcfYBZRfTE0Eidf/hV3otAz36MR1F/VDWUDw==";
//	
//	public static String bylID = "bkang721";
//	public static String bylKey = "MPHu5G24D83bt0QFJcqYcmY28G6N2dXPLmCHmOSoYrdRTJX1Upr7y+IGIw9XaLLYzt+zap7UUVqbTwamrRzScw==";
//	
//	public static String ageRequestURI = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AgeCrltsService";
//	public static String areaRequestURI = "http://openapi.cha.go.kr:80/openapi/soap/crlts/AreaCrltsService";
	
	public static String id;
	public static String serviceKey;
	public static String ageRequestURI;
	public static String areaRequestURI;
	
	public Constants()
	{
		init();
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
			
			String areaRequestURL = props.getProperty("url.area");
			this.areaRequestURI = areaRequestURL;
			
			String ageRequestURL = props.getProperty("url.age");
			this.ageRequestURI = ageRequestURL;
			
			logger.info("set up request id with {}", this.id);
			
		} catch (Exception e) 
		{
			logger.error(e.getMessage(), e.getCause());
		}finally
		{
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
}
