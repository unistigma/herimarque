package net.julnamoo.swm.herimarque.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConstantsBean {

	Logger logger = LoggerFactory.getLogger(ConstantsBean.class.getSimpleName());
	private String mapsRepo;

	@PostConstruct
	public void init() throws IOException
	{
		mapsRepo = "/media/Julie'sData/herimarqueRepo/maps";

		URL cl = ClassLoader.getSystemResource("herimarque.properties");

		Properties props = null;
		try 
		{
			props.load(new FileReader(new File(cl.getFile())));
			if(props.contains("mapsRepo"))
			{
				mapsRepo = props.getProperty("mapsRepo");
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		logger.info("set mapsRepo, {}", mapsRepo);
	}

	public String getMapsRepo() {
		return mapsRepo;
	}
}