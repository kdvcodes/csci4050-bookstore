package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		double promoAmount = 0;
		double totalOrder = 0;
		String promoCode = "NONE";
		boolean addressExist = false;
		boolean paymentExist = false;
		String firstName = "";
		String lastName = "";
		String street = "";
		
		
		if(request.getParameterMap().containsKey("promo")) {
			promoAmount = Double.parseDouble(request.getParameter("promo"));
		} // if
		
		totalOrder = totalPrice + shipping + tax - promoAmount;
		
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
			} // if
			
			if(paymentExist) {
				request.setAttribute("paymentExist", "You already ahve a payment card associated with your account");
			} // if
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
		
		// pull info from database
		
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
