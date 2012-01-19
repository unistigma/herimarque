package net.julnamoo.herimarque.client.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());

	public static void main( String[] args ) throws Exception {
		new AreaRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/AreaCrltsService").run();
		new AgeRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/AgeCrltsService").run();
		new ResponseParser().run();
	}
}
