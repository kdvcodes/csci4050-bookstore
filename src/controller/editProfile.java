package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editProfile
 */
public class editProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String userEmail = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")) userEmail = cookie.getValue();
			} // for
		} // if
		System.out.println("Current user: " + userEmail);
		
		if(userEmail == null) {
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} // if
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello From EditProfile Servlet!");
		
		PrintWriter writer = response.getWriter();
		String firstName = request.getParameter("firstName"), currentFirstName = "";
		String lastName = request.getParameter("lastName"), currentLastName = "";
		String street = request.getParameter("street"), currentStreet = "";
		String city = request.getParameter("city"), currentCity = "";
		String state = request.getParameter("state"), currentState = "";
		String zip = request.getParameter("zip"), currentZip = "";
		String paymentType = request.getParameter("paymentMethod"), currentPaymentType = "";
		String nameOnCard = request.getParameter("nameOnCard"), currentNameOnCard = "";
		String cardNumber = request.getParameter("cardNumber"), currentCardNumber = "";
		String expiration = request.getParameter("expiration"), currentExpiration = "";
		String cvv = request.getParameter("cvv"), currentCvv = "";
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirm = request.getParameter("newPasswordConfirm");
		String currentPasswordConfirm = request.getParameter("currentPasswordConfirm"), currentPassword = "";
		String promotion = request.getParameter("promotion");
		
		String userId = "";
		boolean personalInformationUpdate, addressInformationUpdate, paymentInformationUpdate;
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			// GETTING INFORMATION
			// getting currentPassword from db
			try {
				String userPasswordQuery = "select userPassword from bookstore.user where userEmail = '" + userEmail + "';";
				PreparedStatement userPasswordStatement = con.prepareStatement(userPasswordQuery);
				ResultSet rs = userPasswordStatement.executeQuery();
				
				while(rs.next()) {
					currentPassword += rs.getString("userPassword");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentPassword from editProfile");
				e.printStackTrace();
			} // try catch
			
			if(!currentPasswordConfirm.equals(currentPassword)) {
				request.setAttribute("passwordMismatchError", "Incorrect current password. Please try again.");
				request.getRequestDispatcher("editProfile.jsp").forward(request, response);
				return;
			} // if
			
			// getting userId from db
			try {
				String userIdQuery = "select userId from bookstore.user where userEmail = '" + userEmail + "';";
				PreparedStatement userIdStatement = con.prepareStatement(userIdQuery);
				ResultSet rs = userIdStatement.executeQuery();
				
				while(rs.next()) {
					userId += rs.getString("userId");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving userId from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting currentFirstName from db
			try {
				String currentFirstNameQuery = "select firstName from bookstore.user where userEmail = '" + userEmail + "';";
				PreparedStatement currentFirstNameStatement = con.prepareStatement(currentFirstNameQuery);
				ResultSet rs = currentFirstNameStatement.executeQuery();
				
				while(rs.next()) {
					currentFirstName += rs.getString("firstName");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentFirstName from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting currentLastName from db
			try {
				String currentLastNameQuery = "select lastName from bookstore.user where userEmail = '" + userEmail + "';";
				PreparedStatement currentLastNameStatement = con.prepareStatement(currentLastNameQuery);
				ResultSet rs = currentLastNameStatement.executeQuery();
				
				while(rs.next()) {
					currentLastName += rs.getString("lastName");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentLastName from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting currentStreet from db
			try {
				String currentStreetQuery = "select addressStreet from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement currentStreetStatement = con.prepareStatement(currentStreetQuery);
				ResultSet rs = currentStreetStatement.executeQuery();
				
				while(rs.next()) {
					currentStreet += rs.getString("addressStreet");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentStreet from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting city from db
			try {
				String currentCityQuery = "select addressCity from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement currentCityStatement = con.prepareStatement(currentCityQuery);
				ResultSet rs = currentCityStatement.executeQuery();
				
				while(rs.next()) {
					currentCity += rs.getString("addressCity");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentCity from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting state from db
			try {
				String currentStateQuery = "select addressState from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement currentStateStatement = con.prepareStatement(currentStateQuery);
				ResultSet rs = currentStateStatement.executeQuery();
				
				while(rs.next()) {
					currentState += rs.getString("addressState");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentState from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting zip from db
			try {
				String currentZipQuery = "select addressZipcode from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement currentZipStatement = con.prepareStatement(currentZipQuery);
				ResultSet rs = currentZipStatement.executeQuery();
				
				while(rs.next()) {
					currentZip += rs.getString("addressZipcode");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentZip from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting paymentCardType from db
			try {
				String paymentCardOwnerNameQuery = "select paymentCardOwnerName from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement paymentCardOwnerNameStatement = con.prepareStatement(paymentCardOwnerNameQuery);
				ResultSet rs = paymentCardOwnerNameStatement.executeQuery();
				
				while(rs.next()) {
					currentNameOnCard += rs.getString("paymentCardOwnerName");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentNameOnCard from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting paymentCardType from db
			try {
				String paymentCardNumberQuery = "select paymentCardNum from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement paymentCardNumberStatement = con.prepareStatement(paymentCardNumberQuery);
				ResultSet rs = paymentCardNumberStatement.executeQuery();
				
				while(rs.next()) {
					currentCardNumber += rs.getString("paymentCardNum");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentCardNumber from editProfile");
				e.printStackTrace();
			} // try catch
			
//			// getting paymentCardType from db
//			try {
//				String paymentCardNumberQuery = "select paymentCardNum from bookstore.payment where paymentId = '" + userId + "';";
//				PreparedStatement paymentCardNumberStatement = con.prepareStatement(paymentCardNumberQuery);
//				ResultSet rs = paymentCardNumberStatement.executeQuery();
//				
//				while(rs.next()) {
//					currentCardNumber += rs.getString("paymentCardNum");
//				} // while
//			} catch(Exception e) {
//				System.out.println("Errror retrieving currentCardNumber from editProfile");
//				e.printStackTrace();
//			} // try catch
			
			// getting paymentCardType from db
			try {
				String currentPaymentExpirationQuery = "select paymentCardExpirationDate from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement currentPaymentExpirationStatement = con.prepareStatement(currentPaymentExpirationQuery);
				ResultSet rs = currentPaymentExpirationStatement.executeQuery();
				
				while(rs.next()) {
					currentExpiration += rs.getString("paymentCardExpirationDate");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentExpiration from editProfile");
				e.printStackTrace();
			} // try catch
			
			// getting paymentCardType from db
			try {
				String currentCvvQuery = "select paymentCardSecurityCode from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement currentCvvStatement = con.prepareStatement(currentCvvQuery);
				ResultSet rs = currentCvvStatement.executeQuery();
				
				while(rs.next()) {
					currentCvv += rs.getString("paymentCardSecurityCode");
				} // while
			} catch(Exception e) {
				System.out.println("Errror retrieving currentCvv from editProfile");
				e.printStackTrace();
			} // try catch
			
			
			// PROCESSING INFORMATION
			// process personal information
			try {
				String personalInformationQuery = "UPDATE `bookstore`.`user` SET `userFirstName` = '" + (firstName.length() > 0 && !firstName.equals(currentFirstName)? firstName:currentFirstName) + "', `userLastName` = '" + (lastName.length() > 0 && !lastName.equals(currentLastName)? lastName:currentLastName) + "' WHERE (`userId` = '" + userId + "');";
				PreparedStatement personalInformationStatement = con.prepareStatement(personalInformationQuery);
				personalInformationStatement.execute();
				personalInformationUpdate = true;
			} catch(Exception e) {
				System.out.println("Error processing personal information from editProfile");
				e.printStackTrace();
			} // try catch
			
			// process address information
			try {
				String addressInformationQuery = "UPDATE `bookstore`.`address` SET `addressStreet` = '" + (street.length() > 0 && !street.equals(currentStreet)? street:currentStreet) + "', `addressCity` = '" + (city.length() > 0 && !city.equals(currentCity)? city:currentCity) + "', `addressState` = '" + (state.length() > 0 && !state.equals(currentState)? state:currentState) + "', `addressZipcode` = '" + (zip.length() > 0 && !zip.equals(currentZip)? zip:currentZip) + "' WHERE (`addressId` = '" + userId + "');";
				PreparedStatement addressInformationStatement = con.prepareStatement(addressInformationQuery);
				addressInformationStatement.execute();
				addressInformationUpdate = true;
			} catch(Exception e) {
				System.out.println("Error processing address information from editProfile");
				e.printStackTrace();
			} // try catch
			
			// process payment information
			try {
				String paymentInformationQuery = "UPDATE `bookstore`.`payment` SET `paymentCardType` = '" + (paymentType.length() > 0 && !paymentType.equals(currentPaymentType)? paymentType:currentPaymentType) + "', `paymentCardOwnerName` = '" + (nameOnCard.length() > 0 && !nameOnCard.equals(currentNameOnCard)? nameOnCard:currentNameOnCard) + "', `paymentCardNum` = '" + (cardNumber.length() > 0 && !cardNumber.equals(currentCardNumber)? cardNumber:currentCardNumber) + "', `paymentCardExpirationDate` = '" + (expiration.length() > 0 && !expiration.equals(currentExpiration)? expiration:currentExpiration) + "', `paymentCardSecurityCode` = '" + (cvv.length() > 0 && !cvv.equals(currentCvv)? cvv:currentCvv) + "' WHERE (`paymentId` = '" + userId + "');";
				PreparedStatement paymentInformationStatement = con.prepareStatement(paymentInformationQuery);
				paymentInformationStatement.execute();
				paymentInformationUpdate = true;
			} catch(Exception e) {
				System.out.println("Error processing payment information from editProfile");
				e.printStackTrace();
			} // try catch
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error initialize DB connection from editProfile");
			e.printStackTrace();
		} // try catch
	} // doPost

}
