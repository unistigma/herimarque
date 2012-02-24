package net.julnamoo.swm.herimarque.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

	Logger logger = LoggerFactory.getLogger(ContentService.class);
	
	public void uploadMap(InputStream uploadedInputStream, String uploadedFileLocation)
	{
		logger.debug("Start to write map {}", uploadedFileLocation);
		
		//It needs downgrade the resolution of the map picture
		try
		{
			OutputStream os = new FileOutputStream(new File(uploadedFileLocation));
			
			int read = 0;
			int size = 0;

			byte[] buff = new byte[2048];
			
			while((read = uploadedInputStream.read(buff)) > 0)
			{
				size += read;
				os.write(buff, 0, read);
			}
			
			os.flush();
			os.close();
			
			logger.debug("{} >> Total size : {}", uploadedFileLocation, size);
			logger.info("Complete to save {}", uploadedFileLocation);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
