package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

/**
 * Servlet implementation class books
 */
public class books extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public books() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String bookSource = "images/books/";
		ArrayList<String> books = new ArrayList<String>();
		ArrayList<String> covers = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> prices = new ArrayList<String>();
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String bookQuery = "select bookISBN from bookstore.book;";
			
			PreparedStatement bookStatement = con.prepareStatement(bookQuery);
			ResultSet bookRS = bookStatement.executeQuery();
			
			while(bookRS.next()) {
				books.add(bookRS.getString("bookISBN"));
			} // while

			for(int i = 0; i < books.size(); i++) {
				String coverQuery = "select bookCoverImg from bookstore.book where bookISBN ='"+ books.get(i) +"';";
				String bookNameQuery = "select bookName from bookstore.book where bookISBN='" + books.get(i) + "';";
				String bookPriceQuery = "select bookPrice from bookstore.book where bookISBN='" + books.get(i) + "';";
				
				PreparedStatement coverStatement = con.prepareStatement(coverQuery);
				ResultSet coverRS = coverStatement.executeQuery();
				
				while(coverRS.next()) {
					covers.add(coverRS.getString("bookCoverImg"));
				} // while
				
				PreparedStatement bookNameStatement = con.prepareStatement(bookNameQuery);
				ResultSet bookNameRS = bookNameStatement.executeQuery();
				
				while(bookNameRS.next()) {
					names.add(bookNameRS.getString("bookName"));
				} // while
				
				PreparedStatement bookPriceStatement = con.prepareStatement(bookPriceQuery);
				ResultSet bookPriceRS = bookPriceStatement.executeQuery();
				
				while(bookPriceRS.next()) {
					prices.add(bookPriceRS.getString("bookPrice"));
				} // while
				
			} // for
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
		
		if(books.size() > 0) {
			for(int i = 0; i < names.size(); i++) {
				writer.println("<div class=\"col-lg-3 col-md-6\">");
				writer.println("<div class=\"item\">");
				writer.println("<a href=/csci4050-bookstore/product_single.jsp?bookISBN=" + books.get(i) + ">");
				writer.println("<img src='" + bookSource + covers.get(i) + "' alt=\"img\" width=\"200\" height=\"300\">");
				writer.println("</a>");
				writer.println("<h4>" + names.get(i) + "</h4>");
				writer.println("<h6><span class=\"price\">$" + prices.get(i) + "</span></h6>");
				writer.println("</div>");
				writer.println("</div>");
			} // for
		} // if
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
