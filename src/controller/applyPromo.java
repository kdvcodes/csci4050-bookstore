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
 * Servlet implementation class applyPromo
 */
public class applyPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applyPromo() {
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
		String queryString = request.getQueryString();
		String userId = queryString.split("&")[0].split("=")[1];
		double totalPrice = Double.parseDouble(queryString.split("&")[1].split("=")[1]);
		double shipping = Double.parseDouble(queryString.split("&")[2].split("=")[1]);
		double tax = Double.parseDouble(queryString.split("&")[3].split("=")[1]);
		String promoCodeInput = request.getParameter("promoCodeInput");
		String promoCode = "";
		String promoAmount = "";
		int count = 0;
		
		request.setAttribute("userId", userId);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("shipping", shipping);
		request.setAttribute("tax", tax);
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String promoCodeQuery = "select promoCode from bookstore.promo where promoCode = " + promoCodeInput + ";";
			PreparedStatement promoCodeStatement = con.prepareStatement(promoCodeQuery);
			ResultSet promoCodeRS = promoCodeStatement.executeQuery();
			
			while(promoCodeRS.next()) {
				promoCode += promoCodeRS.getString("promoCode");
				count += 1;
			} // while
			
			if(count == 0) {
				System.out.println("count 0");
				request.setAttribute("promoError", "Promo code does not exist!");
				request.getRequestDispatcher("checkout.jsp?userId=" + userId + "&totalPrice=" + totalPrice + "&shipping=" + shipping + "&tax=" + tax + "&promotion=false").forward(request, response);
				return;
			} else {
				System.out.println("count not 0");
				String promoAmountQuery = "select promoDiscount from bookstore.promo where promoCode = " + promoCodeInput + ";";
				PreparedStatement promoAmountStatement = con.prepareStatement(promoAmountQuery);
				ResultSet promoAmountRS = promoAmountStatement.executeQuery();
				
				while(promoAmountRS.next()) {
					promoAmount += promoAmountRS.getString("promoDiscount");
				} // while
				
//				request.setAttribute("promoCode", promoCode);
//				request.setAttribute("promotionAmount", promoAmount);
				request.getRequestDispatcher("checkout.jsp?userId=" + userId + "&totalPrice=" + totalPrice + "&shipping=" + shipping + "&tax=" + tax + "&promotion=true&promoCode=" + promoCode + "&promoAmount=" + promoAmount).forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
	} // doPost

}
