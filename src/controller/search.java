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
 * Servlet implementation class search
 */
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		if(request.getParameterMap().containsKey("bookSearch")) {
//			String bookSearch = request.getParameter("bookSearch");
//			PrintWriter writer = response.getWriter();
//			writer.println(bookSearch);
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String bookSource = "images/books/";
		String searchCategory = request.getParameter("searchCategory");
		String searchPhrase = request.getParameter("searchPhrase");
		ArrayList<String> books = new ArrayList<String>();
		ArrayList<String> covers = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> prices = new ArrayList<String>();
		
		System.out.println("searchCategory: " + searchCategory + " searchPhrase: " + searchPhrase);
		
		if(searchCategory.equals("title")) {
			try {
				Connection con = DatabaseConnection.initializeDatabase();
				
				String bookQuery = "select bookISBN from bookstore.book where bookName like '%" + searchPhrase + "%';";
				
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
		} // if
		
		if(searchCategory.equals("author")) {
			try {
				Connection con = DatabaseConnection.initializeDatabase();
				
				String bookQuery = "select bookISBN from bookstore.book where bookAuthor like '%" + searchPhrase + "%';";
				
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
		} // if
		
		if(searchCategory.equals("isbn")) {
			try {
				Connection con = DatabaseConnection.initializeDatabase();
				
				String bookQuery = "select bookISBN from bookstore.book where bookISBN like '%" + searchPhrase + "%';";
				
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
		} // if
		
		System.out.println("Number of books: " + books.size());
		
		String responseString = "?books=" + books.size();
		if(books.size() > 0) {
			for(int i = 0; i < names.size(); i++) {
//				writer.println("<div class=\"col-lg-3 col-md-6\">");
//				writer.println("<div class=\"item\">");
//				writer.println("<a href=/csci4050-bookstore/product_single.jsp?bookISBN=" + books.get(i) + ">");
//				writer.println("<img src='" + bookSource + covers.get(i) + "' alt=\"img\" width=\"200\" height=\"300\">");
//				writer.println("</a>");
//				writer.println("<h4>" + names.get(i) + "</h4>");
//				writer.println("<h6><span class=\"price\">$" + prices.get(i) + "</span></h6>");
//				writer.println("</div>");
//				writer.println("</div>");
				
//				responseString += ""
//						+ "<div class=\"col-lg-3 col-md-6\">"
//						+ "<div class=\"item\">"
//						+ "<a href=/csci4050-bookstore/product_single.jsp?bookISBN=" + books.get(i) + ">"
//						+ "<img src='" + bookSource + covers.get(i) + "' alt=\"img\" width=\"200\" height=\"300\">"
//						+ "</a>"
//						+ "<h4>" + names.get(i) + "</h4>"
//						+ "<h6><span class=\"price\">$" + prices.get(i) + "</span></h6>"
//						+ "</div>"
//						+ "</div>";
				
				responseString += "&isbn=" + books.get(i);
			} // for
		} // if
		request.getRequestDispatcher("search.jsp" + responseString).forward(request, response);
	} // doPost

}
