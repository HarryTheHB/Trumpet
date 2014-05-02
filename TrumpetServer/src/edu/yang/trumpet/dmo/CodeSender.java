package edu.yang.trumpet.dmo;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CodeSender {
	private String host = "";
	private String from = "";
	private String password = "";
	private String to = "";
	private int code;

	// Common variables
	public CodeSender(String host, String from, String password, String to){
		this.host = host;
		this.from = from;
		this.password = password;
		this.to = to;
	}
	public static void main(String[] args) {
		CodeSender s = new CodeSender("Smtp.gmail.com", "oose.trumpet.services@gmail.com", "hlz636474", "ydai9@jhu.edu" );
		s.send();
	}
	public int getCode(){
		return code;
	}

	public void send() {
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@jhu.edu$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(to);
		boolean isMatched = matcher.matches();
		if (isMatched == true) {
			System.out.print("match");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");
			//props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.quitwait", "false");
			// Get session
			Session session = Session.getInstance(props, new GMailAuthenticator(from, password));

			try {
				// Instantiate a message
				Message msg = new MimeMessage(session);

				// Set the FROM message
				msg.setFrom(new InternetAddress(from));
				// The recipients can be more than one so we use an array but
				// you
				// can
				// use 'new InternetAddress(to)' for only one address.
				InternetAddress[] address = { new InternetAddress(to) };
				msg.setRecipients(Message.RecipientType.TO, address);
				// Set the message subject and date we sent it.
				msg.setSubject("Welcome to Trumpet");
				msg.setSentDate(new Date());

				// Set message content
				code = (int) (Math.random()*10000);
				System.out.print(code);
				msg.setText("This is your verification code: " + code);
				// Send the message
				Transport.send(msg);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
		else{
			System.out.print("not matched");
		}
	}

}