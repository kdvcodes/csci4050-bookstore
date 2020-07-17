package controller;

//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(name = "register", urlPatterns =("/register"))
/**
 * Servlet implementation class register
 */
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("Hello From Register Servlet!!");
		
		Random rng = new Random();
		PrintWriter writer = response.getWriter();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String paymentMethod = request.getParameter("paymentMethod");
		String fullNameOnCard = request.getParameter("fullname");
		String cardNum = request.getParameter("cardNum");
		String expiration = request.getParameter("expiration");
		String cvv = request.getParameter("cvv");
		String promotion = request.getParameter("promotion");
		int userId = 0;
		int addressId = 0;
		int paymentId = 0;
		boolean userIdSuccess = false;
		boolean registerUser = false;
		boolean registerAddress = false;
		boolean registerPayment = false;
		boolean registerIntoBD = false;
		
		System.out.println("promotion value: " + promotion);
		
		if(password.equals(confirm)) {
			try {
				Connection con = DatabaseConnection.initializeDatabase();
				
				if(email.length() > 0) {
					int count = 0;
					
					String emailCheckQuery = "SELECT count(userEmail) FROM bookstore.user where userEmail = \"" + email + "\";";
					PreparedStatement emailCheckStatement = con.prepareStatement(emailCheckQuery);
					ResultSet rs = emailCheckStatement.executeQuery();
					
					while(rs.next()) {
						count += Integer.parseInt(rs.getString("count(userEmail)"));
					} // while
					
					if(count > 0) {
						// handle error, email exist
						request.setAttribute("emailError", "Email already used by another user!\n");
						request.getRequestDispatcher("register.jsp").forward(request, response);
					} // if
				} // if
				
				while(userIdSuccess == false) {
					int count = 0;
					
					userId = rng.nextInt(999);
					String userIdCheckQuery = "select count(userId) from bookstore.user where userId = \"" + userId + "\";";
					PreparedStatement userIdCheckStatement = con.prepareStatement(userIdCheckQuery);
					ResultSet rs = userIdCheckStatement.executeQuery();
					
					while(rs.next()) {
						count += Integer.parseInt(rs.getString("count(userId)"));
					} // while
					
					if(count > 0) {
						userIdSuccess = false;
					} else {
						userIdSuccess = true;
					} // if
				} // while
				
				if(userIdSuccess) {
					addressId = userId;
					paymentId = userId;
				} // if
				
				String userInfoQuery = "INSERT INTO `bookstore`.`user`"
						+ " (`userId`, `userFirstName`, `userLastName`, `userEmail`, `userPassword`, `userPromotion`, `userType`, `userAddressId`, `userPaymentId`, `userActivated`)"
						+ " VALUES ('"+ userId +"', '" + (firstName.length() > 0? firstName : "NULL") + "', '" + (lastName.length() > 0? lastName : "NULL") + "', '" + (email.length() > 0? email : "NULL") + "', '" + (password.length() > 0? password : "NULL") + "', '" + (promotion.length() < 1? "0":"1") + "', '" + 0 + "', '" + addressId + "', '" + paymentId + "', '" + 0 + "');";
				PreparedStatement userInfoStatement = con.prepareStatement(userInfoQuery);
				userInfoStatement.execute();
				registerUser = true;
				
				String addressInfoQuery = "INSERT INTO `bookstore`.`address`"
						+ " (`addressId`, `addressStreet`, `addressCity`, `addressState`, `addressZipcode`, `addressUserId`)"
						+ " VALUES ('" + addressId + "', '" + (address.length() > 0? address:"NULL") + "', '" + (city.length() > 0? city:"NULL") + "', '" + (state.equalsIgnoreCase("choose...")? "NULL":state) + "', '" + (zip.length() > 0? zip:"NULL") + "', '" + userId + "');";
				PreparedStatement addressInfoStatement = con.prepareStatement(addressInfoQuery);
				addressInfoStatement.execute();
				registerAddress = true;
				
				String paymentInfoQuery = "INSERT INTO `bookstore`.`payment`"
						+ " (`paymentId`, `paymentCardType`, `paymentCardOwnerName`, `paymentCardNum`, `paymentCardExpirationDate`, `paymentCardSecurityCode`, `paymentUserId`)"
						+ " VALUES ('" + paymentId + "', '" + paymentMethod + "', '" + (fullNameOnCard.length() > 0? fullNameOnCard:"NULL") + "', '" + (cardNum.length() > 0? cardNum:"NULL") + "', '" + (expiration.length() > 0? expiration:"NULL") + "', '" + (cvv.length() > 0? cvv:"NULL") + "', '" + userId + "');";
				PreparedStatement paymentInfoStatement = con.prepareStatement(paymentInfoQuery);
				paymentInfoStatement.execute();
				registerPayment = true;
			} catch (Exception e) {
				e.printStackTrace();
				
				System.out.println("Registration failed");
				request.setAttribute("registrationError", "Registration fail. Please try again later");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			} // try catch
			registerIntoBD = true;
		} else {
			System.out.println("Password mismatch");
			request.setAttribute("passwordError", "Password and confirmation password must match. Please try again.!\n");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} // if else
		
		request.setAttribute("doneRegistration", "Thank you for registering for IMA BookStore. Please check your email for confirmation email!\n");
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
		//sending registration email
		if(registerIntoBD) {
			registrationConfirmation.sendRegistrationConfirmationEmail(request);
		} // if
	} // doPost method
} // register class
