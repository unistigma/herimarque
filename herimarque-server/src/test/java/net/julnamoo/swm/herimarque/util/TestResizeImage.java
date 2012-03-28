package net.julnamoo.swm.herimarque.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.Test;

public class TestResizeImage {

	@Test
	public void resize() throws IOException
	{
		System.out.println(TestResizeImage.class.getClassLoader().getResource("profile.jpg").toString());
		File o = new File(TestResizeImage.class.getClassLoader().getResource("profile.jpg").toString());
		BufferedImage origin = ImageIO.read(TestResizeImage.class.getClassLoader().getResourceAsStream("profile.jpg"));
		
		int height = origin.getHeight();
		int width = origin.getWidth();

		int newHeight = height * 256 / width;
		BufferedImage result = new BufferedImage(256, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = result.createGraphics();
		graphic.setComposite(AlphaComposite.Src);
		graphic.drawImage(origin, 0, 0, 256, newHeight, null);
		graphic.dispose();

		File target = new File("resizeTest.png");
		ImageIO.write(result, "png", target);
		
		Assert.assertEquals("png", "resizeTest.png".substring("resizeTest.png".length()-3));
	}
}
