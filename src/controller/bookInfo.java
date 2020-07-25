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
 * Servlet implementation class bookInfo
 */
public class bookInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String queryString = request.getQueryString();
		System.out.println("Query String: " + queryString);
		String bookISBN = queryString.split("=")[1];
//		String bookISBN = request.getParameter("bookISBN");
//		System.out.println("bookISBN: " + bookISBN);
		String bookName = "";
		String bookPrice = "";
		String bookSource = "images/books/";
		String bookCover = "";
		String bookDesc = "";
		String bookYear = "";
		String bookAuthor = "";
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String bookNameQuery = "select bookName from bookstore.book where bookISBN='" + bookISBN + "';";
			String bookPriceQuery = "select bookPrice from bookstore.book where bookISBN='" + bookISBN + "';";
			String bookCoverQuery = "select bookCoverImg from bookstore.book where bookISBN ='" + bookISBN + "';";
			String bookDescQuery = "select bookDesc from bookstore.book where bookISBN = '" + bookISBN + "';";
			String bookAuthorQuery = "select bookAuthor from bookstore.book where bookISBN = '" + bookISBN + "';";
			String bookYearQuery = "select bookYear from bookstore.book where bookISBN = '" + bookISBN + "';";
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
			
			PreparedStatement bookCoverStatement = con.prepareStatement(bookCoverQuery);
			ResultSet bookCoverRS = bookCoverStatement.executeQuery();
			
			while(bookCoverRS.next()) {
				bookCover += bookCoverRS.getString("bookCoverImg");
			} // while
			
			PreparedStatement bookDescStatement = con.prepareStatement(bookDescQuery);
			ResultSet bookDescRS = bookDescStatement.executeQuery();
			
			while(bookDescRS.next()) {
				bookDesc += bookDescRS.getString("bookDesc");
			} // while
			
			PreparedStatement bookAuthorStatement = con.prepareStatement(bookAuthorQuery);
			ResultSet bookAuthorRS = bookAuthorStatement.executeQuery();
			
			while(bookAuthorRS.next()) {
				bookAuthor += bookAuthorRS.getString("bookAuthor");
			} // while
			
			PreparedStatement bookYearStatement = con.prepareStatement(bookYearQuery);
			ResultSet bookYearRS = bookYearStatement.executeQuery();
			
			while(bookYearRS.next()) {
				bookYear += bookYearRS.getString("bookYear");
			} // while
			
			con.close();
			bookNameStatement.close();
			bookNameRS.close();
			bookPriceStatement.close();
			bookPriceRS.close();
			bookCoverStatement.close();
			bookCoverRS.close();
			
			request.setAttribute("bookISBN", bookISBN);
			request.setAttribute("bookName", bookName);
			request.setAttribute("bookPrice", bookPrice);
			request.setAttribute("bookDesc", bookDesc);
			request.setAttribute("bookCoverImg", bookCover);
			request.setAttribute("bookAuthor", bookAuthor);
			request.setAttribute("bookYear", bookYear);
//			request.getRequestDispatcher("product_single.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
