package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.registrationConfirmation.SMTPAuthenticator;
import model.DatabaseConnection;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class forgotPassword
 */
public class forgotPassword extends HttpServlet {
	public static class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("imabookstore@gmail.com","Weareima2020");
		}
	}
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello from email generator!");
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			if(request.getParameter("email") != null) {
				int count = 0;
				
				String emailCheckQuery = "SELECT count(userEmail) FROM bookstore.user where userEmail = \"" + request.getParameter("email") + "\";";
				PreparedStatement emailCheckStatement = con.prepareStatement(emailCheckQuery);
				ResultSet rs = emailCheckStatement.executeQuery();
				
				while(rs.next()) {
					count += Integer.parseInt(rs.getString("count(userEmail)"));
				} // while
				
				if(count < 1) {
					// handle error, email exist
					request.setAttribute("emailError", "Email does not is not registered with a user yet!\n");
					request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
					return;
				} // if
			}
		} catch(Exception e) {
			System.out.println("Failed on forgotPassword servlet!");
		}
		
		if(request.getParameter("email") != null) {
			Properties props = new Properties();
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			SMTPAuthenticator auth = new SMTPAuthenticator();
			Session sess = Session.getInstance(props,auth);
			MimeMessage msg=new MimeMessage(sess);
			try {
				msg.setContent("To reset your password, please click on this <a href='127.0.0.1:8080/csci4050-bookstore/createNewPassword.jsp?email=" + request.getParameter("email") + "'>link</a>. If the link is not presented, please copy and paste the following link into your browser. 127.0.0.1:8080/csci4050-bookstore/createNewPassword.jsp?userEmail=" + request.getParameter("email"),"text/html");
				msg.setSubject("Book store account recovery");
				
				System.out.println("Email to send to:" + request.getParameter("email"));
				msg.setFrom(new InternetAddress("imabookstore@gmail.com"));
				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(request.getParameter("email")));
				System.out.println("Done setting msg");
				
				Transport trans= sess.getTransport("smtp");
				trans.connect("smtp.gmail.com",465,"imabookstore@gmail.com","Weareima2020");
				trans.sendMessage(msg,msg.getAllRecipients());
				trans.close();
				
				request.setAttribute("passwordRequest", "Please check your email for instruction on resetting your password!");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			} catch(Exception e) {
				
				
		        System.out.println("Result: " + e.getMessage() + "\n");
		     
			}
		} // if
		
	}

}
