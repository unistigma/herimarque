package net.julnamoo.swm.herimarque.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil 
{
	/**
	 * Get value from property file
	 * @param fileName - property file name
	 * @param property - wanted property
	 * @return value - property value from the property file
	 */
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

	/**
	 * Save the (property, value) to the file
	 * @param fileName - target file
	 * @param key - key for property
	 * @param value - value for property
	 */
	public static void alterValueFromProperties(String fileName, String key, String value)
	{
		Properties props = new Properties();
		InputStream is = PropertiesUtil.class.getResourceAsStream("/" + fileName);
		OutputStream os = null;
		try
		{
			props.load(is);
			props.setProperty(key, value);
			
			os = new FileOutputStream(new File(PropertiesUtil.class.getResource("/" + fileName).getFile()));
			props.store(os, key);
			is.close();
			os.close();
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
