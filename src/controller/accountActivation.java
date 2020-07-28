package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

/**
 * Servlet implementation class accountActivation
 */
public class accountActivation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public accountActivation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryString = request.getQueryString();
		String userEmail = queryString.split("=")[1];
		String userId = "";
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
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
			
			// activate user based on userId UPDATE `bookstore`.`user` SET `userActivated` = '1' WHERE (`userId` = '447');
			try {
				String userActivationQuery = "UPDATE `bookstore`.`user` SET `userActivated` = '1' WHERE (`userId` = '" + userId + "');";
				PreparedStatement userActivationStatement = con.prepareStatement(userActivationQuery);
				userActivationStatement.execute();
				
				request.setAttribute("activationMessage", "Your account is successfully activated. Thank you!");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			} catch(Exception e) {
				System.out.println("Errror activating user from accountActivation");
				e.printStackTrace();
			} // try catch
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error initialize DB connection from editProfile");
			e.printStackTrace();
		} // try catch
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
