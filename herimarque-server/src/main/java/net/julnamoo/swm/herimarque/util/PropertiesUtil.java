package net.julnamoo.swm.herimarque.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil 
{
	public static String getValueFromProperties(String fileName, String property) 
	{
		Properties props = new Properties();
		String value = null;
		InputStream is = PropertiesUtil.class.getResourceAsStream("/" + fileName);
		try 
		{
			props.load(is);
			value = props.getProperty(property);
			is.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
			value = "";
		}

		return value;
	}
}
