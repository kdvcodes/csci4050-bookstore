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
 * Servlet implementation class adminAddBook
 */
public class adminAddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminAddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String coverPicture = request.getParameter("coverPicture");
		String genre = request.getParameter("genre");
		String bookISBN = request.getParameter("isbn");
		String bookTitle = request.getParameter("title");
		String bookAuthor  = request.getParameter("author");
		String bookYear = request.getParameter("year");
		String bookPrice = request.getParameter("price");
		String bookQuantity = request.getParameter("quantity");
		String description = request.getParameter("description");
		
		if(bookPrice.equals("")) {
			bookPrice = "0";
		} // if
		
		if(bookQuantity.equals("")) {
			bookQuantity = "0";
		} // if
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			int bookCheckCount = 0;
			String checkBookExistQuery = "select bookISBN from bookstore.book where bookISBN = " + bookISBN + ";";
			PreparedStatement checkBookExistStatement = con.prepareStatement(checkBookExistQuery);
			ResultSet checkBookExistRS = checkBookExistStatement.executeQuery();
			
			while(checkBookExistRS.next()) {
				bookCheckCount += 1;
			} // while
			
			if(bookCheckCount > 0) {
				request.setAttribute("failAddingBook", "Book already exist in database!");
				request.getRequestDispatcher("/manageBooks.jsp").forward(request, response);
				return;
			} // if
			
			String bookAddQuery = "INSERT INTO `bookstore`.`book` (`bookISBN`, `bookName`, `bookAuthor`, `bookYear`, `bookDesc`, `bookPrice`, `bookCoverImg`, `bookGenre`, `bookAvailability`, `bookStock`) "
					+ "VALUES ('" + bookISBN + "', '" + bookTitle + "', '" + bookAuthor + "', '" + bookYear + "', '" + description + "', '" + bookPrice + "', '" + coverPicture + "', '" + genre + "', '1', '" + bookQuantity + "');";
			PreparedStatement bookAddStatement = con.prepareStatement(bookAddQuery);
			bookAddStatement.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // doPost

}
