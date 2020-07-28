package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

/**
 * Servlet implementation class checkout
 */
public class checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryString = request.getQueryString();
		String userId = queryString.split("&")[0].split("=")[1];
		double totalPrice = Double.parseDouble(queryString.split("&")[1].split("=")[1]);
		double shipping = Double.parseDouble(queryString.split("&")[2].split("=")[1]);
		double tax = Double.parseDouble(queryString.split("&")[3].split("=")[1]);
		boolean promotionExist = Boolean.parseBoolean(queryString.split("&")[4].split("=")[1]);
		double promoAmount = 0;
		double totalOrder = 0;
		String promoCode = "NONE";
		boolean addressExist = false;
		boolean paymentExist = false;
		String firstName = "";
		String lastName = "";
		String street = "";
		String city = "";
		String state = "";
		String zip = "";
		String cardType = "";
		String cardName = "";
		String cardNumber = "";
		String cardExp = "";
		String cardCVV = "";
		String promoError = "";
		
		if(request.getParameterMap().containsKey("promoError")) {
			promoError = request.getParameter("promoError");
		}
		
		if(promotionExist) {
			promoCode = queryString.split("&")[5].split("=")[1];
			promoAmount = Double.parseDouble(queryString.split("&")[6].split("=")[1]);
		}
		
//		if(request.getParameterMap().containsKey("promoCode")) {
//			promoCode = request.getParameter("promoCode");
//		}
//		System.out.println("Promo Code: " + promoCode);
//		if(request.getParameterMap().containsKey("promotionAmount")) {
//			System.out.println("Promo code exists!" + request.getParameter("promotionAmount"));
//			promoAmount = Double.parseDouble(request.getParameter("promotionAmount"));
//		} // if
//		
//		if(!promoError.equals("")) {
//			request.setAttribute("promoError", promoError);
//		}
		
		System.out.println("code: " + promoCode + " discount amount: " + promoAmount);
		
		totalOrder = totalPrice + shipping + tax - promoAmount;
		
		request.setAttribute("userId", userId);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("shipping", shipping);
		request.setAttribute("tax", tax);
		request.setAttribute("promoCode", promoCode);
		request.setAttribute("promoAmount", promoAmount);
		request.setAttribute("totalOrder", totalOrder);
		
		// check for addressExist and paymentExist
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			int addrCount = 0;
			int paymentCount = 0;
			
			// check if user already has address
			String checkUserAddressQuery = "select addressId from bookstore.address where addressUserId = " + userId + ";";
			PreparedStatement checkUserAddressStatement = con.prepareStatement(checkUserAddressQuery);
			ResultSet checkUserAddressRS = checkUserAddressStatement.executeQuery();
			
			while(checkUserAddressRS.next()) {
				addrCount += 1;
			} // 
			
			if(addrCount == 1) {
				addressExist = true;
			} // if
			
			// check if user already has address
			String checkUserPaymentQuery = "select paymentId from bookstore.payment where paymentUserId = " + userId + ";";
			PreparedStatement checkUserPaymentStatement = con.prepareStatement(checkUserPaymentQuery);
			ResultSet checkUserPaymentRS = checkUserPaymentStatement.executeQuery();
			
			while(checkUserPaymentRS.next()) {
				paymentCount += 1;
			} // 
			
			if(paymentCount == 1) {
				paymentExist = true;
			} // if
			
			// display message for component that exist
			if(addressExist) {
				request.setAttribute("addressExist", "You already have an address associated with your account");
				request.setAttribute("addressExistCheck", "checked='checked'");
			} // if
			
			if(paymentExist) {
				request.setAttribute("paymentExist", "You already ahve a payment card associated with your account");
				request.setAttribute("paymentExistCheck", "checked='checked'");
			} // if
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
		
		// pull info from database
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			// firstName
			String firstNameQuery = "select userFirstName from bookstore.user where userId = '" + userId + "';";
			PreparedStatement firstNameStatement = con.prepareStatement(firstNameQuery);
			ResultSet firstNameRS = firstNameStatement.executeQuery();
			
			while(firstNameRS.next()) {
				firstName += firstNameRS.getString("userFirstName");
			} // while
			
			// lastName
			String lastNameQuery = "select userLastName from bookstore.user where userId = '" + userId + "';";
			PreparedStatement lastNameStatement = con.prepareStatement(lastNameQuery);
			ResultSet lastNameRS = lastNameStatement.executeQuery();
			
			while(lastNameRS.next()) {
				lastName += lastNameRS.getString("userLastName");
			} // while
			
			// street
			String streetQuery = "select addressStreet from bookstore.address where addressId = '" + userId + "';";
			PreparedStatement streetStatement = con.prepareStatement(streetQuery);
			ResultSet streetRS = streetStatement.executeQuery();
			
			while(streetRS.next()) {
				street += streetRS.getString("addressStreet");
			} // while
			
			// city
			String cityQuery = "select addressCity from bookstore.address where addressId = '" + userId + "';";
			PreparedStatement cityStatement = con.prepareStatement(cityQuery);
			ResultSet cityRS = cityStatement.executeQuery();
			
			while(cityRS.next()) {
				city += cityRS.getString("addressCity");
			} // while
			
			// state
			String stateQuery = "select addressState from bookstore.address where addressId = '" + userId + "';";
			PreparedStatement stateStatement = con.prepareStatement(stateQuery);
			ResultSet stateRS = stateStatement.executeQuery();
			
			while(stateRS.next()) {
				state += stateRS.getString("addressState");
			} // while
			
			// zip
			String zipQuery = "select addressZipcode from bookstore.address where addressId = '" + userId + "';";
			PreparedStatement zipStatement = con.prepareStatement(zipQuery);
			ResultSet zipRS = zipStatement.executeQuery();
			
			while(zipRS.next()) {
				zip += zipRS.getString("addressZipcode");
			} // while
			
			// cardType
			String cardTypeQuery = "select " + "paymentCardOwnerName as paymentCardType" + " from bookstore.payment where paymentId = '" + userId + "';";
			PreparedStatement cardTypeStatement = con.prepareStatement(cardTypeQuery);
			ResultSet cardTypeRS = cardTypeStatement.executeQuery();
			
			while(cardTypeRS.next()) {
				cardType += cardTypeRS.getString("paymentCardType");
			} // while
			
			// cardName
			String cardNameQuery = "select " + "paymentCardOwnerName as paymentCardOwnerName" + " from bookstore.payment where paymentId = '" + userId + "';";
			PreparedStatement cardNameStatement = con.prepareStatement(cardNameQuery);
			ResultSet cardNameRS = cardNameStatement.executeQuery();
			
			while(cardNameRS.next()) {
				cardName += cardNameRS.getString("paymentCardOwnerName");
			} // while
			
			// cardNumber
			String cardNumberQuery = "select " + "aes_decrypt(paymentCardNum, 'card') as paymentCardNum" + " from bookstore.payment where paymentId = '" + userId + "';";
			PreparedStatement cardNumberStatement = con.prepareStatement(cardNumberQuery);
			ResultSet cardNumberRS = cardNumberStatement.executeQuery();
			
			while(cardNumberRS.next()) {
				cardNumber += cardNumberRS.getString("paymentCardNum");
			} // while
			
			// cardExp
			String cardExpQuery = "select " + "paymentCardExpirationDate as paymentCardExpirationDate" + " from bookstore.payment where paymentId = '" + userId + "';";
			PreparedStatement cardExpStatement = con.prepareStatement(cardExpQuery);
			ResultSet cardExpRS = cardExpStatement.executeQuery();
			
			while(cardExpRS.next()) {
				cardExp += cardExpRS.getString("paymentCardExpirationDate");
			} // while
			
			// cardCVV
			String cardCVVQuery = "select " + "aes_decrypt(paymentCardSecurityCode, 'card') as paymentCardSecurityCode" + " from bookstore.payment where paymentId = '" + userId + "';";
			PreparedStatement cardCVVStatement = con.prepareStatement(cardCVVQuery);
			ResultSet cardCVVRS = cardCVVStatement.executeQuery();
			
			while(cardCVVRS.next()) {
				cardCVV += cardCVVRS.getString("paymentCardSecurityCode");
			} // while
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
		
//		String firstName = "";
//		String lastName = "";
//		String street = "";
//		String city = "";
//		String state = "";
//		String zip = "";
//		String cardType = "";
//		String cardName = "";
//		String cardNumber = "";
//		String cardExp = "";
//		String cardCVV = "";
		
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("street", street);
		request.setAttribute("city", city);
		request.setAttribute("zip", zip);
		request.setAttribute("cardType", cardType);
		request.setAttribute("cardName", cardName);
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardExp", cardExp);
		request.setAttribute("cardCVV", cardCVV);
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
