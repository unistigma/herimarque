package net.julnamoo.swm.herimarque.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.junit.Test;

public class TestFileURI {

	@Test
	public void testURI() throws URISyntaxException
	{
//		File f = new File("/Volumes/Julie_air");
//		URI uri = new URI(f.getAbsolutePath());
//		System.out.println(uri);
		
		String title ="t   itl   e".replace(" ", "");
		StringBuilder pathBuilder = new StringBuilder("dir");
		String filePath = pathBuilder.append(File.separatorChar).append("maps").append(File.separatorChar)
				.append(title).toString();
		
		Assert.assertEquals(filePath, "dir/maps/title");
		filePath = pathBuilder.append(File.separatorChar).append("map.png").toString();
		Assert.assertEquals(filePath, "dir/maps/title/map.png");
		filePath = filePath.replace("maps", "imgs");
		Assert.assertEquals(filePath, "dir/imgs/title/map.png");
	}
	
}
