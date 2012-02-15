package net.julnamoo.swm.herimarque.util;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ConstantsBean {

	
	private String mapsRepo;
	
	@PostConstruct
	public void init()
	{
		URL cl = ClassLoader.getSystemResource("");
	}
	
}