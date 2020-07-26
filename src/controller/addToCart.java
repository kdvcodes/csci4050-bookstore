package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addToCart
 */
public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCart() {
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
		Random rng = new Random();
		boolean bookISBNExist = false;
		boolean userIdExist = false;
		int currentBookNum = 0;
		String userEmail = "";
		String userId = "";
		String bookISBN = request.getQueryString().split("=")[1];
		System.out.println("From add to cart::bookISBN: " + bookISBN);
		Cookie[] cookies = request.getCookies();
		
		for(int i = 0; i < cookies.length; i++) {
			System.out.println("addToCart::checking cookies: " + cookies[i].getName() + " : "+ cookies[i].getValue());
		}
		
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("user")) {
					userEmail = cookie.getValue();
				} // if
			} // for
		} // if
		
		if(userEmail.equals("")) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		} // if
		
		try {
			int userCount = 0;
			int bookNumber = 0;
			int cartId = 0;
			Connection con = DatabaseConnection.initializeDatabase();
			
			while(cartId == 0) {
				int count = 0;
				
				cartId = rng.nextInt(999);
				String cartIdCheckQuery = "select count(cartId) from bookstore.cart where cartId = \"" + cartId + "\";";
				PreparedStatement cartIdCheckStatement = con.prepareStatement(cartIdCheckQuery);
				ResultSet rs = cartIdCheckStatement.executeQuery();
				
				while(rs.next()) {
					count += Integer.parseInt(rs.getString("count(cartId)"));
				} // while
				
				if(count >= 1) {
					cartId = 0;
				} // if			
			} // while
			
			String checkUserIdQuery = "select userId from bookstore.user where userEmail = '" + userEmail + "';";
			PreparedStatement checkUserIdStatement = con.prepareStatement(checkUserIdQuery);
			ResultSet checkUserIdRS = checkUserIdStatement.executeQuery();
			
			while(checkUserIdRS.next()) {
				userCount += 1;
				userId += checkUserIdRS.getString("userId");
			} // while
			
			String bookNumberCheckQuery = "select bookStock from bookstore.book where bookISBN = '" + bookISBN + "';";
			PreparedStatement bookNumberCheckStatement = con.prepareStatement(bookNumberCheckQuery);
			ResultSet bookNumberCheckRS = bookNumberCheckStatement.executeQuery();
			
			while(bookNumberCheckRS.next()) {
				bookNumber += Integer.parseInt(bookNumberCheckRS.getString("bookStock"));
			} // while
			
			String cartExistQuery = "select cartBookNumber from bookstore.cart where cartBookId = " + bookISBN + " and cartUserId = " + userId + ";";
			PreparedStatement cartExistStatement = con.prepareStatement(cartExistQuery);
			ResultSet cartExistRS = cartExistStatement.executeQuery();
			
			while(cartExistRS.next()) {
				currentBookNum += Integer.parseInt(cartExistRS.getString("cartBookNumber"));
			} // while
			
			System.out.println("Cart info:\n\tCartId: " + cartId + "\n\tBookId: " + bookISBN + "\n\tQuantity: 1" + "\n\tUserId: " + userId);
			
			if(bookNumber >= 1 && currentBookNum == 0) {
				String putItemToCartQuery = "INSERT INTO `bookstore`.`cart` (`cartId`, `cartBookId`, `cartBookNumber`, `cartUserId`) VALUES ('" + cartId + "', '" + bookISBN + "', '" + 1 + "', '" + userId + "');";
				PreparedStatement putItemToCartStatement = con.prepareStatement(putItemToCartQuery);
				putItemToCartStatement.execute();
				
				request.setAttribute("addedToCart", "Book successfully added to cart!");
				request.getRequestDispatcher("/product_single.jsp?bookISBN=" + bookISBN).forward(request, response);
			} else if(bookNumber >= 1 && currentBookNum != 0) {
				int oneMoreBook = currentBookNum + 1;
				String putItemToCartQuery = "UPDATE `bookstore`.`cart` SET `cartBookNumber` = '" + oneMoreBook + "' WHERE cartBookId = " + bookISBN + " and cartUserId = " + userId + ";";
				PreparedStatement putItemToCartStatement = con.prepareStatement(putItemToCartQuery);
				putItemToCartStatement.execute();
				
				request.setAttribute("addedToCart", "Book successfully added to cart!");
				request.getRequestDispatcher("/product_single.jsp?bookISBN=" + bookISBN).forward(request, response);
			} else {
				request.setAttribute("bookNotInStockMessage", "This book is not available in stock right now. Please try again later!");
				request.getRequestDispatcher("/product_single.jsp?bookISBN=" + bookISBN).forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // doPost

}
