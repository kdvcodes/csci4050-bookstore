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
 * Servlet implementation class updateCart
 */
public class updateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCart() {
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
		String bookISBN = queryString.split("&")[1].split("=")[1];
		String numBook = request.getParameter("quantity");
		int bookInStock = 0;
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String checkBookStockQuery = "select bookStock from bookstore.book where bookISBN = '" + bookISBN + "';";
			PreparedStatement checkbookStockStatement = con.prepareStatement(checkBookStockQuery);
			ResultSet checkBookStockRS = checkbookStockStatement.executeQuery();
			
			while(checkBookStockRS.next()) {
				bookInStock += Integer.parseInt(checkBookStockRS.getString("bookStock"));
			} // while
			
			if(Integer.parseInt(numBook) <= 0) {
				// forward to remove book
			} else if(Integer.parseInt(numBook) > bookInStock) {
				String setBookCartToStockQuery = "UPDATE `bookstore`.`cart` SET `cartBookNumber` = '" + bookInStock + "' WHERE cartUserId = " + userId + " and cartBookId = " + bookISBN + ";";
				PreparedStatement setBookCartToStockStatement = con.prepareStatement(setBookCartToStockQuery);
				setBookCartToStockStatement.execute();
			} else {
				String setBookCartQuery = "UPDATE `bookstore`.`cart` SET `cartBookNumber` = '" + numBook + "' WHERE cartUserId = " + userId + " and cartBookId = " + bookISBN + ";";
				PreparedStatement setBookCartStatement = con.prepareStatement(setBookCartQuery);
				setBookCartStatement.execute();
			} // if else
			
			request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
	} // doPost

}
