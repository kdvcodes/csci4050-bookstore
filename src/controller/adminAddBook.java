package controller;

import java.io.IOException;
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
		String Description = request.getParameter("description");
	} // doPost

}
