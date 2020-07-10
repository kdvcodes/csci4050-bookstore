package controller;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexController
 */
public class GetBookNameOLD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookNameOLD() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.getRequestDispatcher("index.jsp").forward(request, response);
        String url = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "9995";
        
        try(PrintWriter writer = response.getWriter()) {
        	Connection connection = DriverManager.getConnection(url, user, password);
        	System.out.println("Connected to MySQL Server!");
        	
        	Statement statement = connection.createStatement();
        	String query = "select * from book where bookId = 1";
        	ResultSet resultSet = statement.executeQuery(query);
        	
//        	while(resultSet.next()) {
//        		writer.println(resultSet.getString("bookName"));
//        	}
        	
        	writer.println("book1 title");
        	
        	connection.close();
        	statement.close();
        	resultSet.close();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        request.getRequestDispatcher("index.jsp").include(request, response);
    } // processRequest

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
