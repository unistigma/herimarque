package net.julnamoo.swm.herimarque.util;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Service
//@Provider
@Component
public class ConstantsBean {

	Logger logger = LoggerFactory.getLogger(ConstantsBean.class.getSimpleName());
	private String mapsRepo;

//	@Autowired
//	ApplicationContext context;
	
	@PostConstruct
	public void init() throws IOException
	{
		mapsRepo = "/media/Julie'sData/herimarqueRepo/maps";
		
//		URL cl = ClassLoader.getSystemResource("herimarque.properties");
//		URL cl = Thread.currentThread().getContextClassLoader().getResource("herimarque.properties");
//		URL cl = context.getClassLoader().getResource("herimarque.properties");
//		URL cl = new ClassPathResource("herimarque.properties").getURL();
		/*
		Properties props = null;
		try 
		{
//			logger.debug("load {}", cl);
//			props.load(new FileReader(new File(cl.getFile())));
//			props.load(new FileReader(new ClassPathResource("herimarque.properties").getFile()));
			if(props.contains("mapsRepo"))
			{
				mapsRepo = props.getProperty("mapsRepo");
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		*/
		logger.info("set mapsRepo, {}", mapsRepo);
	}

	public @Bean String getMapsRepo() {
		return mapsRepo;
	}
}