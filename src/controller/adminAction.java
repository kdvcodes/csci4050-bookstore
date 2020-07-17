package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class adminAction
 */
public class adminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userEmail = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")) userEmail = cookie.getValue();
			} // for
		} // if
		System.out.println("Current user: " + userEmail);
		
		if(userEmail == null) {
//			response.sendRedirect("message.jsp");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} // if
		
		try {
			int count = 0;
			
			Connection con = DatabaseConnection.initializeDatabase();
			String adminEmailCheckQuery = "select userType from bookstore.user where userEmail = \"" + userEmail + "\";";
			PreparedStatement adminEmailCheckStatement = con.prepareStatement(adminEmailCheckQuery);
			ResultSet rs = adminEmailCheckStatement.executeQuery();
			
			while(rs.next()) {
				count += Integer.parseInt(rs.getString("userType"));
			} // while
			
			if(count == 0) {
				// handle error, email exist
				request.setAttribute("userTypeError", "You are not an admin user!!\n");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			} // if
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
