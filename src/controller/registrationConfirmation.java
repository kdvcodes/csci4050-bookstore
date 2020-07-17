package controller;

import java.net.http.HttpRequest;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

public class registrationConfirmation {
	public static class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("imabookstore@gmail.com","Weareima2020");
		}
	}
	
	protected static void sendRegistrationConfirmationEmail(HttpServletRequest request) {
		System.out.println("Hello from email generator!");
		if(request.getParameter("email") != null) {
			Properties props = new Properties();
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			SMTPAuthenticator auth = new SMTPAuthenticator();
			Session sess = Session.getInstance(props,auth);
			MimeMessage msg=new MimeMessage(sess);
			try {
				msg.setContent("Thank you for registering please click to activate <a href='127.0.0.1:8080/csci4050-bookstore/login.jsp'>click</a>","text/html");
				msg.setSubject("Book store account");
				
				System.out.println("Email to send to:" + request.getParameter("email"));
				msg.setFrom(new InternetAddress("imabookstore@gmail.com"));
				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(request.getParameter("email")));
				System.out.println("Done setting msg");
				
				Transport trans= sess.getTransport("smtp");
				trans.connect("smtp.gmail.com",465,"imabookstore@gmail.com","Weareima2020");
				trans.sendMessage(msg,msg.getAllRecipients());
				trans.close();
			} catch(Exception e) {
				
				
		        System.out.println("Result: " + e.getMessage() + "\n");
		     
			}
		} // if
	} // sendRegistrationConfirmationEmail method
}
