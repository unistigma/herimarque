package net.julnamoo.herimarque.client.soap;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

	static Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());

	public static void main( String[] args ) throws Exception {
<<<<<<< HEAD
		new AreaRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/AreaCrltsService").run();
		new AgeRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/AgeCrltsService").run();
=======
		System.out.println("Select the request category.");
		System.out.println("1. Area\t2.Age\t3.Kind");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int category = Integer.parseInt(br.readLine());
		
		switch (category) {
		case 1:
			System.out.println("Run AreaRequestSender");
			new AreaRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/AreaCrltsService").run();
			break;

		case 2:
			System.out.println("Run AgeRequestSender");
			new AgeRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/AgeCrltsService").run();
			break;
			
		case 3 : 
			System.out.println("Run KindRequestSender");
			new KindRequestSender("http://openapi.cha.go.kr/openapi/soap/crlts/KndCrltsService").run();
			break;
		default:
			break;
		}
>>>>>>> 1f92856f37eea6e0ead4c0023cd33d6a22e1fd66
	}
}
