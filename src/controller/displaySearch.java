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
 * Servlet implementation class displaySearch
 */
public class displaySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public displaySearch() {
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
		String queryString = request.getQueryString();
		int numBook = Integer.parseInt(queryString.split("&")[0].split("=")[1]);
		System.out.println("Query String: " + queryString);
		System.out.println("number of books to display: " + numBook);
		
//		for(int i = 1; i < numBook; i++) {
//			books.add(queryString.split("&")[i].split("=")[1]);
//			System.out.println("added: " + queryString.split("&")[i].split("=")[1]);
//		} // for
//		System.out.println("size of books: " + books.size());
		
		int count = 1;
		do {
			books.add(queryString.split("&")[count].split("=")[1]);
			System.out.println("added: " + queryString.split("&")[count].split("=")[1]);
			System.out.println("count: " + count);
			count += 1;
		} while(count <= numBook);
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
