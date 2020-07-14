package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "register", urlPatterns =("/register"))
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
		int userId = 1;
		
		if(password.equals(confirm)) {
			try {
				writer.println("very new code");				
				String userInfoQuery = "INSERT INTO `bookstore`.`user` (`userId`, `userFirstName`, `userLastName`, `userEmail`, `userPassword`, `userPromotion`, `userType`)"
						+ " VALUES ('"+ userId +"', '" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', '" + 1 + "', '" + 1 + "');";
				Connection con = DatabaseConnection.initializeDatabase();
				PreparedStatement statement = con.prepareStatement(userInfoQuery);
				statement.execute();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
