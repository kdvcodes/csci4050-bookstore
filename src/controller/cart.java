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
		String userEmail = "";
		String userId = "";
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
			ArrayList<String> bookCoverInCart = new ArrayList<String>();
			
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
				} // while
				
				String getBookCoverInCartQuery = "select bookCoverImg from bookstore.book where bookISBN = '" + booksInCart.get(i) + "';";
				PreparedStatement getBookCoverInCartStatement = con.prepareStatement(getBookCoverInCartQuery);
				ResultSet getBookCoverInCartRS = getBookCoverInCartStatement.executeQuery();
				
				while(getBookCoverInCartRS.next()) {
					bookCoverInCart.add(getBookCoverInCartRS.getString("bookCoverImg"));
				} // while
			} // for
			
			if(carts.size() > 0) {
				String cartItemsString = "";
				for(int i = 0; i < carts.size(); i++) {
					String borderOpt = "border-0";
//					if(i == 0) {
//						borderOpt = "";
//					} // if
					cartItemsString += "<tr>\n" + 
							"	                            <form method=\"post\" id=\""
							+ "u" + booksInCart.get(i)
							+ "\" action=\"updateCart?userId="
							+ userId + "&bookISBN="
									+ booksInCart.get(i)
							+ "\">\n" + 
							"	                             <th scope=\"row\" class=\"" + borderOpt + "\">\n" + 
							"	                                    <div class=\"p-2\">\n" + 
							"	                                        <img src=\"images/books/" + bookCoverInCart.get(i) + "\" alt=\"\" width=\"70\" class=\"img-fluid rounded shadow-sm\">\n" + 
							"	                                        <div class=\"ml-3 d-inline-block align-middle\">\n" + 
							"	                                            <h5 class=\"mb-0\"> <a href=\"#\" class=\"text-dark d-inline-block align-middle\">"
							+ bookNamesInCart.get(i)
							+ "</a></h5><span class=\"text-muted font-weight-normal font-italic d-block\"></span>\n" + 
							"	                                        </div>\n" + 
							"	                                    </div>\n" + 
							"	                                </th>\n" + 
							"	                                <td class=\"" + borderOpt + " align-middle\"><strong>$"
									+ bookPriceInCart.get(i)
									+ "</strong></td>\n" + 
							"	                                <td class=\"" + borderOpt + " align-middle\">\n" + 
							"	                                    <strong>\n" + 
							"	                                        <div class=\"def-number-input number-input safari_only mb-0 w-10 \">\n" + 
							"	                                            <input class=\"quantity\" min=\"0\" name=\"quantity\" value=\""
							+ bookQuantityInCart.get(i)
							+ "\" type=\"number\">\n" + 
							"	                                            <button class=\"quantity\" type=\"submit\" form=\""
							+ "u" + booksInCart.get(i)
							+ "\">Update</button>\n" + 
							"	                                            \n" + 
							"	                                        </div>\n" + 
							"	                                    </strong>\n" +  "</form>\n" +
							"	                                </td>\n" +
							"	                                <td class=\"" + borderOpt + " align-middle\"><form method=\"post\" id=\""
									+ "r" + booksInCart.get(i)
									+ "\" name=\"remove\" action=\"removeFromCart?userId="
									+ userId
									+ "&bookISBN="
									+ booksInCart.get(i)
									+ "\">\n" + 
									"	                                		<button class=\"remove\" type=\"submit\" form=\""
									+ "r" + booksInCart.get(i)
									+ "\">Remove</button>\n" + 
									"	                                	</form></td>" +
							"                               \n" + 
							"                            </tr>";
				} // for
				
				request.setAttribute("cartItems", cartItemsString);
			} else {
				request.setAttribute("cartItems", "No item in cart!");
			} // if else
			
			double totalPrice = 0;
			double shipping = 10;
			double taxRate = 0.06;
			double tax = 0;
			double subTotal = 0;
			
			for(int i = 0; i < carts.size(); i++) {
				totalPrice += (Double.parseDouble(bookPriceInCart.get(i)) * Double.parseDouble(bookQuantityInCart.get(i)));
			} // for
			
			tax = ((totalPrice + shipping) * taxRate);
			subTotal = (totalPrice + shipping) + tax;
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("shipping", shipping);
			request.setAttribute("tax", tax);
			request.setAttribute("subTotal", subTotal);
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
