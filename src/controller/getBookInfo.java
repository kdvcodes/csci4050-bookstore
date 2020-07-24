package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getBookInfo
 */
public class getBookInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBookInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ?bookISBN=439708184
		PrintWriter writer = response.getWriter();
//		String queryString = request.getQueryString();
//		System.out.println("Query String: " + queryString);
//		String bookISBN = queryString.split("=")[1];
		String bookISBN = request.getParameter("bookISBN");
		System.out.println("bookISBN: " + bookISBN);
		String bookName = "";
		String bookPrice = "";
		String bookSource = "images/books/";
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String bookNameQuery = "select bookName from bookstore.book where bookISBN='" + bookISBN + "';";
			String bookPriceQuery = "select bookPrice from bookstore.book where bookISBN='" + bookISBN + "';";
			PreparedStatement bookNameStatement = con.prepareStatement(bookNameQuery);
			ResultSet bookNameRS = bookNameStatement.executeQuery();
			
			while(bookNameRS.next()) {
				bookName += bookNameRS.getString("bookName");
			} // while
			
			PreparedStatement bookPriceStatement = con.prepareStatement(bookPriceQuery);
			ResultSet bookPriceRS = bookPriceStatement.executeQuery();
			
			while(bookPriceRS.next()) {
				bookPrice += bookPriceRS.getString("bookPrice");
			} // while
			
			con.close();
			bookNameStatement.close();
			bookNameRS.close();
			bookPriceStatement.close();
			bookPriceRS.close();
			
			writer.println("<a href=/bookInfo?bookISBN=" + bookISBN + ">");
			writer.println("<img src='" + bookSource + bookISBN + ".jpg' alt=\"img\" width=\"200\" height=\"300\">");
			writer.println("</a>");
			writer.println("<h4>" + bookName + "</h4>");
			writer.println("<h6><span class=\"price\">$" + bookPrice + "</span></h6>");
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
