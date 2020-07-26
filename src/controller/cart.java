 package controller;

import java.io.IOException;
import java.sql.Connection;

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
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("user")) {
					userEmail = cookie.getValue();
				} else {
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				} // if else
			} // for
		} // if
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String getUserIdQuery = "select userId from bookstore.user where userEmail = '" + userEmail + "';";
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
