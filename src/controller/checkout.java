package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkout
 */
public class checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryString = request.getQueryString();
		String userId = queryString.split("&")[0].split("=")[1];
		double totalPrice = Double.parseDouble(queryString.split("&")[1].split("=")[1]);
		double shipping = Double.parseDouble(queryString.split("&")[2].split("=")[1]);
		double tax = Double.parseDouble(queryString.split("&")[3].split("=")[1]);
		double promoAmount = 0;
		double totalOrder = 0;
		String promoCode = "NONE";
		
		if(request.getParameterMap().containsKey("promo")) {
			promoAmount = Double.parseDouble(request.getParameter("promo"));
		} // if
		
		totalOrder = totalPrice + shipping + tax - promoAmount;
		
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("shipping", shipping);
		request.setAttribute("tax", tax);
		request.setAttribute("promoCode", promoCode);
		request.setAttribute("promoAmount", promoAmount);
		request.setAttribute("totalOrder", totalOrder);
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
