package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

/**
 * Servlet implementation class removeFromCart
 */
public class removeFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("removeFromCart::doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryString = request.getQueryString();
		System.out.println("removeFromCart::doPost:queryString: " + queryString);
		String userId = queryString.split("&")[0].split("=")[1];
		String bookISBN = queryString.split("&")[1].split("=")[1];
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String removeBookFromCartQuery = "delete from bookstore.cart where cartBookId = " + bookISBN + " and cartUserId = " + userId + ";";
			PreparedStatement removeBookFromCartStatement = con.prepareStatement(removeBookFromCartQuery);
			removeBookFromCartStatement.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
	} // doPost

}
