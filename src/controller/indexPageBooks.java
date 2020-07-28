package controller;

import java.io.IOException;
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
 * Servlet implementation class indexPageBooks
 */
public class indexPageBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexPageBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> bookNameList = new ArrayList<String>();
		ArrayList<String> bookPriceList = new ArrayList<String>();
		ArrayList<String> bookCoverList = new ArrayList<String>();
		String bookSource = "images/books/";
		String book1Id = "439708184";
		ArrayList<String> bookIdList = new ArrayList<String>();
		bookIdList.add("439708184");
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			for(int i = 0; i < bookIdList.size(); i++) {
				String bookName = "";
				String bookPrice = "";
				String bookNameQuery = "select bookName from bookstore.book where bookISBN='" + bookIdList.get(i) + "';";
				String bookPriceQuery = "select bookPrice from bookstore.book where bookISBN='" + bookIdList.get(i) + "';";
				String bookCoveryQuery = "";
				PreparedStatement bookNameStatement = con.prepareStatement(bookNameQuery);
				ResultSet bookNameRS = bookNameStatement.executeQuery();
				
				while(bookNameRS.next()) {
					bookName += bookNameRS.getString("bookName");
				} // while
				
				PreparedStatement bookpriceStatement = con.prepareStatement(bookPriceQuery);
				ResultSet bookPriceRS = bookpriceStatement.executeQuery();
				
				while(bookPriceRS.next()) {
					bookPrice += bookPriceRS.getString("bookPrice");
				}
				
				request.setAttribute("bookName", bookNameList.get(i));
				request.setAttribute("bookPrice", bookPriceList.get(i));
			} // for
		} catch(Exception e) {
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
