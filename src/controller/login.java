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
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String loginEmail = request.getParameter("loginEmail");
		String loginPassword  = request.getParameter("loginPassword");
		boolean userActivated = false;
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			// check email
			if(loginEmail.length() > 0) {
				int count = 0;
				String loginEmailCheckQuery = "select count(userEmail) from bookstore.user where userEmail = \"" + loginEmail + "\";";
				PreparedStatement loginEmailCheckStatement = con.prepareStatement(loginEmailCheckQuery);
				ResultSet emailRS = loginEmailCheckStatement.executeQuery();
				
				while(emailRS.next()) {
					count += Integer.parseInt(emailRS.getString("count(userEmail"));
				} // while
				
				// check password
				if(count == 1) {
					String realPassword = "";
					String loginPasswordCheckQuery = "select userPassword from bookstore.user where userEmail = \"" + loginEmail + "\";";
					PreparedStatement loginPasswordCheckStatement = con.prepareStatement(loginPasswordCheckQuery);
					ResultSet passwordRS = loginPasswordCheckStatement.executeQuery();
					
					while(passwordRS.next()) {
						realPassword += passwordRS.getString("userPassword");
					} // while
					
					// check activated
					if(realPassword.equals(loginPassword)) {
						int activatedValue = 0;
						String loginUserActivatedCheckQuery = "select userActivated from bookstore.user where userEmail = \"" + loginEmail + "\";";
						PreparedStatement loginUserActivatedCheckStatement = con.prepareStatement(loginUserActivatedCheckQuery);
						ResultSet activatedRS = loginUserActivatedCheckStatement.executeQuery();
						
						while(activatedRS.next()) {
							activatedValue += Integer.parseInt("userActivated");
						} // while
						
						if(activatedValue == 1) {
							userActivated = true;
						} // if
					}
				} else {
					// HANDLE ERRORS, USER EMAIL NOT LONGER THAN 0
				}
			} // if
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // doPost

}
