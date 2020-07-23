package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String book1Title, book2Title, book3Title, book4Title;
		String book1Price, book2Price, book3Price, book4Price;
		String book1Cover, book2Cover, book3Cover, book4Cover;
		String bookSource = "images/books/";
		String book1Id = "439708184";
		String book2Id = "2";
		String book3Id = "3";
		String book4Id = "4";
		ArrayList<String> bookId = new ArrayList<String>();
		bookId.add("439708184");
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			for(int i = 0; i < bookId.size(); i++) {
				
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
