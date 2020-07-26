 package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cart
 */
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String userEmail = "";
	String userId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("user")) {
					userEmail = cookie.getValue();
				} // if
			} // for
			
			if(userEmail.equals("")) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		} // if
		
		try {
			ArrayList<String> carts = new ArrayList<String>();
			ArrayList<String> booksInCart = new ArrayList<String>();
			ArrayList<String> bookQuantityInCart = new ArrayList<String>();
			ArrayList<String> bookNamesInCart = new ArrayList<String>();
			ArrayList<String> bookPriceInCart = new ArrayList<String>();
			
			Connection con = DatabaseConnection.initializeDatabase();
			
			String getUserIdQuery = "select userId from bookstore.user where userEmail = '" + userEmail + "';";
			PreparedStatement getUserIdStatement = con.prepareStatement(getUserIdQuery);
			ResultSet getUserIdRS = getUserIdStatement.executeQuery();
			
			while(getUserIdRS.next()) {
				userId += getUserIdRS.getString("userId");
			} //
			
			String getCartQuery = "select cartId from bookstore.cart where cartUserId = '" + userId + "';";
			PreparedStatement getCartStatement = con.prepareStatement(getCartQuery);
			ResultSet getCartRS = getCartStatement.executeQuery();
			
			while(getCartRS.next()) {
				carts.add(getCartRS.getString("cartId"));
			} // while
			
			for(int i = 0; i < carts.size(); i++) {
				String getBooksInCartQuery = "select cartBookId from bookstore.cart where cartId = '" + carts.get(i) + "';";
				PreparedStatement getBooksInCartStatement = con.prepareStatement(getBooksInCartQuery);
				ResultSet getBooksInCartRS = getBooksInCartStatement.executeQuery();
				
				while(getBooksInCartRS.next()) {
					booksInCart.add(getBooksInCartRS.getString("cartBookId"));
				} // while
				
				String getBookQuantityInCartQuery = "select cartBookNumber from bookstore.cart where cartId = '" + carts.get(i) + "';";
				PreparedStatement getBookQuantityInCartStatement = con.prepareStatement(getBookQuantityInCartQuery);
				ResultSet getBookQuantityInCartRS = getBookQuantityInCartStatement.executeQuery();
				
				while(getBookQuantityInCartRS.next()) {
					bookQuantityInCart.add(getBookQuantityInCartRS.getString("cartBookNumber"));
				} // while
				
				String getBookNameInCartQuery = "select bookName from bookstore.book where bookISBN = '" + booksInCart.get(i) + "';";
				PreparedStatement getBookNameInCartStatement = con.prepareStatement(getBookNameInCartQuery);
				ResultSet getBookNameInCartRS = getBookNameInCartStatement.executeQuery();
				
				while(getBookNameInCartRS.next()) {
					bookNamesInCart.add(getBookNameInCartRS.getString("bookName"));
				} // while
				
				String getBookPriceInCartQuery = "select bookPrice from bookstore.book where bookISBN = '" + booksInCart.get(i) + "';";
				PreparedStatement getBookPriceInCartStatement = con.prepareStatement(getBookPriceInCartQuery);
				ResultSet getBookPriceInCartRS = getBookPriceInCartStatement.executeQuery();
				
				while(getBookPriceInCartRS.next()) {
					bookPriceInCart.add(getBookPriceInCartRS.getString("bookPrice"));
				}
			} // for
			
			for(int i = 0; i < carts.size(); i++) {
				System.out.println("Name of book: " + bookNamesInCart.get(i) + " Quantity: " + bookQuantityInCart.get(i) + " Price: " + bookPriceInCart.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
