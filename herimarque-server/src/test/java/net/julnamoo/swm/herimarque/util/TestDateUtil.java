package net.julnamoo.swm.herimarque.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

public class TestDateUtil {

	@Test
	public void test() throws ParseException
	{
//		Date d = DateFormat.getInstance().parse("2012/03/01/01:29:43");
//		Date d = SimpleDateFormat.getInstance().parse("2012/03/01/01:29:43");
		Date d1 = new Date();
		System.out.println(d1.toString());
		String s = DateFormat.getInstance().format(d1);
		Date d = DateFormat.getInstance().parse(s);
//		Date d = DateFormat.getInstance().parse(s);
		System.out.println(d.toString());
	}
}
