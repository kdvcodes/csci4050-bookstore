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

/**
 * Servlet implementation class setNewPassword
 */
public class setNewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String userEmail = "";   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setNewPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryString = request.getQueryString();
		userEmail = queryString.split("=")[1];
		
		System.out.println("Email to change password for: " + userEmail);
//		request.setAttribute("passwordChangeAccountMessage", "Changing password for account " + userEmail);
//		request.getRequestDispatcher("createNewPassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");
		String userId = "";
		
		if(newPassword.equals(newPasswordConfirmation)) {
			
		} else {
			request.setAttribute("passwordError", "Passwords do not match, please try again!");
			request.getRequestDispatcher("createNewPassword.jsp").forward(request, response);
			return;
		} // if else
		
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
				String setNewPasswordQuery = "UPDATE `bookstore`.`user` SET `userPassword` = '" + newPassword + "' WHERE (`userId` = '" + userId + "');";
				PreparedStatement setNewPasswordStatement = con.prepareStatement(setNewPasswordQuery);
				setNewPasswordStatement.execute();
				
				request.setAttribute("passwordRecoveryMessage", "Your new password is not set. Thank you!");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			} catch(Exception e) {
				System.out.println("Errror activating user from accountActivation");
				e.printStackTrace();
			} // try catch
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error initialize DB connection from editProfile");
			e.printStackTrace();
		} // try catch
	}

}
