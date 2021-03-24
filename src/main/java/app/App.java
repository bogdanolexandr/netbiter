package app;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class App {

	public static void main(String[] args) throws IOException {
		
		
		MailSender mail = new MailSender();
		mail.sendMail("alarm");
		
		
			
	
//		
//		Connection.Response response = 
//				Jsoup.connect("http://10.0.2.91/cgi-bin/read.cgi?page=templates/main.html&id=1&path=Page;id=1")
//				.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
//				.timeout(10*1000)
//				.data("page", "http://10.0.2.91/cgi-bin/read.cgi?page=templates/main.html&id=1&path=Page;id=1")
//				.data("template", "")
//				.data("script1", "login")
//				.data("passwd1", "6518a2ab041f9387fd30d19b9f43ae61")
//				.data("userid1", "admin")
//				.data("passwd", "admin")
//				.followRedirects(true)
//				.execute();
//		Document document = Jsoup
//				.connect("http://10.0.2.93/cgi-bin/read.cgi?page=templates/alarm_status.html")
//				.cookies(response.cookies())
//				.get();
//		
//		System.out.println(document.select("img"));
	}

}
