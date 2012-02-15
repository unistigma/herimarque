package net.julnamoo.swm.herimarque.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:rootContext2.xml" })
public class TestConstantsBeans {
	
	@Resource
	private ConstantsBean constants;
	
	
	
	@Service
	public class ConstantsBean {

		
		private String mapsRepo;
		
		@PostConstruct
		public void init() throws IOException
		{
			URL cl = ClassLoader.getSystemResource("herimarque.properties");
			 
			Properties props = PropertiesLoaderUtils.loadAllProperties(cl.getFile());
			
			if(props.contains("mapsRepo"))
			{
				
			}
		}
		
	}
	
}
