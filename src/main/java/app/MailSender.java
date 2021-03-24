package app;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import utils.Settings;

public class MailSender {

	private String userName = Settings.getValue("userName");
	private String password = Settings.getValue("password");
	private String from = Settings.getValue("from");
	private String to = Settings.getValue("to");
	
	private Properties props = new Properties();
	{
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
	}

	private Session getSession() {
		return Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
	}

	public void sendMail(String message) {
		Message msg = new MimeMessage(getSession());
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(message);
			msg.setText(message);
			Transport.send(msg);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("message has been sent");
	}

}
