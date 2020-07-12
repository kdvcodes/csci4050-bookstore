package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getFeatureBook
 */
public class getFeatureBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFeatureBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		
		for(int i = 0; i < 4; i++) {
			writer.println("<div class=\"col-lg-3 col-md-6\">");
			writer.println("<div class=\"item\">");
			writer.println("<a href=\"product_single.jsp\">");
			writer.println("<img src=\"images/harry1.jpeg\" alt=\"img\" width=\"200\" height=\"300\">");
			writer.println("</a>");
		} // for
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
