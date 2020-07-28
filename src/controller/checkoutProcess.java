package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.registrationConfirmation.SMTPAuthenticator;

/**
 * Servlet implementation class checkoutProcess
 */
public class checkoutProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkoutProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String checkoutAmount = request.getParameter("checkoutButton");
		String newAddress = request.getParameter("newAddress");
		String newPaymentCheck = request.getParameter("newPayment");
		String userEmail = "";
		String userId = "";
		String firstName = "";
		String lastName = "";
		String street = "";
		String city = "";
		String state = "";
		String zip = "";
		String cardType = "";
		String cardName = "";
		String cardNumber = "";
		String cardExp = "";
		String cardCVV = "";
		Random rng = new Random();
		boolean newOrderNumber = false;
		int orderNumber = 0;
		ArrayList<String> transactionId = new ArrayList<String>();
		ArrayList<String> carts = new ArrayList<String>();
		ArrayList<String> booksInCart = new ArrayList<String>();
		ArrayList<String> bookQuantityInCart = new ArrayList<String>();
		ArrayList<String> bookNamesInCart = new ArrayList<String>();
		ArrayList<String> bookPriceInCart = new ArrayList<String>();
		ArrayList<String> bookCoverInCart = new ArrayList<String>();
		
		System.out.println("addrCheck: " + newAddress + " pmtCheck: " + newPaymentCheck);
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("user")) {
					userEmail = cookie.getValue();
				} // if
			} // for
			
			if(userEmail.equals("")) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		} // if
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			// userId
			String userIdQuery = "select userId from bookstore.user where userEmail = '" + userEmail + "';";
			PreparedStatement userIdStatement = con.prepareStatement(userIdQuery);
			ResultSet userIdRS = userIdStatement.executeQuery();
			
			while(userIdRS.next()) {
				userId += userIdRS.getString("userId");
			} // while
		} catch(Exception e) {
			e.printStackTrace();
		} // try catch
		
		if(newAddress == null) {
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			street = request.getParameter("street");
			city = request.getParameter("city");
			state = request.getParameter("state");
			zip = request.getParameter("zip");
		} else {
			try {
				Connection con = DatabaseConnection.initializeDatabase();
				
				// firstName
				String firstNameQuery = "select userFirstName from bookstore.user where userId = '" + userId + "';";
				PreparedStatement firstNameStatement = con.prepareStatement(firstNameQuery);
				ResultSet firstNameRS = firstNameStatement.executeQuery();
				
				while(firstNameRS.next()) {
					firstName += firstNameRS.getString("userFirstName");
				} // while
				
				// lastName
				String lastNameQuery = "select userLastName from bookstore.user where userId = '" + userId + "';";
				PreparedStatement lastNameStatement = con.prepareStatement(lastNameQuery);
				ResultSet lastNameRS = lastNameStatement.executeQuery();
				
				while(lastNameRS.next()) {
					lastName += lastNameRS.getString("userLastName");
				} // while
				
				// street
				String streetQuery = "select addressStreet from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement streetStatement = con.prepareStatement(streetQuery);
				ResultSet streetRS = streetStatement.executeQuery();
				
				while(streetRS.next()) {
					street += streetRS.getString("addressStreet");
				} // while
				
				// city
				String cityQuery = "select addressCity from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement cityStatement = con.prepareStatement(cityQuery);
				ResultSet cityRS = cityStatement.executeQuery();
				
				while(cityRS.next()) {
					city += cityRS.getString("addressCity");
				} // while
				
				// state
				String stateQuery = "select addressState from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement stateStatement = con.prepareStatement(stateQuery);
				ResultSet stateRS = stateStatement.executeQuery();
				
				while(stateRS.next()) {
					state += stateRS.getString("addressState");
				} // while
				
				// zip
				String zipQuery = "select addressZipcode from bookstore.address where addressId = '" + userId + "';";
				PreparedStatement zipStatement = con.prepareStatement(zipQuery);
				ResultSet zipRS = zipStatement.executeQuery();
				
				while(zipRS.next()) {
					zip += zipRS.getString("addressZipcode");
				} // while
			} catch(Exception e) {
				e.printStackTrace();
			} // try catch
		} // if else
		
		if(newPaymentCheck == null) {
			cardType = request.getParameter("paymentMethod");
			cardName = request.getParameter("nameOnCard");
			cardNumber = request.getParameter("cardNumber");
			cardExp = request.getParameter("expiration");
			cardCVV = request.getParameter("cvv");
		} else {
			try {
				Connection con = DatabaseConnection.initializeDatabase();
				
				// cardType
				String cardTypeQuery = "select paymentCardType from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement cardTypeStatement = con.prepareStatement(cardTypeQuery);
				ResultSet cardTypeRS = cardTypeStatement.executeQuery();
				
				while(cardTypeRS.next()) {
					cardType += cardTypeRS.getString("paymentCardType");
				} // while
				
				// cardName
				String cardNameQuery = "select paymentCardOwnerName from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement cardNameStatement = con.prepareStatement(cardNameQuery);
				ResultSet cardNameRS = cardNameStatement.executeQuery();
				
				while(cardNameRS.next()) {
					cardName += cardNameRS.getString("paymentCardOwnerName");
				} // while
				
				// cardNumber
				String cardNumberQuery = "select paymentCardNum from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement cardNumberStatement = con.prepareStatement(cardNumberQuery);
				ResultSet cardNumberRS = cardNumberStatement.executeQuery();
				
				while(cardNumberRS.next()) {
					cardNumber += cardNumberRS.getString("paymentCardNum");
				} // while
				
				// cardExp
				String cardExpQuery = "select paymentCardExpirationDate from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement cardExpStatement = con.prepareStatement(cardExpQuery);
				ResultSet cardExpRS = cardExpStatement.executeQuery();
				
				while(cardExpRS.next()) {
					cardExp += cardExpRS.getString("paymentCardExpirationDate");
				} // while
				
				// cardCVV
				String cardCVVQuery = "select paymentCardSecurityCode from bookstore.payment where paymentId = '" + userId + "';";
				PreparedStatement cardCVVStatement = con.prepareStatement(cardCVVQuery);
				ResultSet cardCVVRS = cardCVVStatement.executeQuery();
				
				while(cardCVVRS.next()) {
					cardCVV += cardCVVRS.getString("paymentCardSecurityCode");
				} // while
			} catch(Exception e) {
				e.printStackTrace();
			} // try catch
		} // if else
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			String getCartQuery = "select cartId from bookstore.cart where cartUserId = '" + userId + "';";
			PreparedStatement getCartStatement = con.prepareStatement(getCartQuery);
			ResultSet getCartRS = getCartStatement.executeQuery();
			
			while(getCartRS.next()) {
				carts.add(getCartRS.getString("cartId"));
			} // while
			
			for(int i = 0; i < carts.size(); i++) {
				String getBooksInCartQuery = "select cartBookId from bookstore.cart where cartId = '" + carts.get(i) + "';";
				PreparedStatement getBooksInCartStatement = con.prepareStatement(getBooksInCartQuery);
				ResultSet getBooksInCartRS = getBooksInCartStatement.executeQuery();
				
				while(getBooksInCartRS.next()) {
					booksInCart.add(getBooksInCartRS.getString("cartBookId"));
				} // while
				
				String getBookQuantityInCartQuery = "select cartBookNumber from bookstore.cart where cartId = '" + carts.get(i) + "';";
				PreparedStatement getBookQuantityInCartStatement = con.prepareStatement(getBookQuantityInCartQuery);
				ResultSet getBookQuantityInCartRS = getBookQuantityInCartStatement.executeQuery();
				
				while(getBookQuantityInCartRS.next()) {
					bookQuantityInCart.add(getBookQuantityInCartRS.getString("cartBookNumber"));
				} // while
				
				String getBookNameInCartQuery = "select bookName from bookstore.book where bookISBN = '" + booksInCart.get(i) + "';";
				PreparedStatement getBookNameInCartStatement = con.prepareStatement(getBookNameInCartQuery);
				ResultSet getBookNameInCartRS = getBookNameInCartStatement.executeQuery();
				
				while(getBookNameInCartRS.next()) {
					bookNamesInCart.add(getBookNameInCartRS.getString("bookName"));
				} // while
				
				String getBookPriceInCartQuery = "select bookPrice from bookstore.book where bookISBN = '" + booksInCart.get(i) + "';";
				PreparedStatement getBookPriceInCartStatement = con.prepareStatement(getBookPriceInCartQuery);
				ResultSet getBookPriceInCartRS = getBookPriceInCartStatement.executeQuery();
				
				while(getBookPriceInCartRS.next()) {
					bookPriceInCart.add(getBookPriceInCartRS.getString("bookPrice"));
				} // while
				
				String getBookCoverInCartQuery = "select bookCoverImg from bookstore.book where bookISBN = '" + booksInCart.get(i) + "';";
				PreparedStatement getBookCoverInCartStatement = con.prepareStatement(getBookCoverInCartQuery);
				ResultSet getBookCoverInCartRS = getBookCoverInCartStatement.executeQuery();
				
				while(getBookCoverInCartRS.next()) {
					bookCoverInCart.add(getBookCoverInCartRS.getString("bookCoverImg"));
				} // while
			} // for
		} catch (Exception e) {
			e.printStackTrace();
		} // try catch
		
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			while(newOrderNumber == false) {
				int count = 0;
				
				orderNumber = rng.nextInt(999);
				String orderNumberCheckCheckQuery = "select count(orderId) from bookstore.order where orderId = \"" + orderNumber + "\";";
				PreparedStatement orderNumberCheckStatement = con.prepareStatement(orderNumberCheckCheckQuery);
				ResultSet rs = orderNumberCheckStatement.executeQuery();
				
				while(rs.next()) {
					count += Integer.parseInt(rs.getString("count(orderId)"));
				} // while
				
				if(count > 0) {
					newOrderNumber = false;
				} else {
					newOrderNumber = true;
				} // if
			} // while
			
			for(int i = 0; i < carts.size(); i++) {
				boolean newTransactionNumber = false;
				int newTran = 0;
				
				while(newTransactionNumber == false) {
					int count = 0;
					
					newTran = rng.nextInt(999);
					String transactionNumberCheckQuery = "select count(transactionId) from bookstore.transaction where transactionId = '" + newTran + "';";
					PreparedStatement transactionNumberCheckStatement = con.prepareStatement(transactionNumberCheckQuery);
					ResultSet tranRS = transactionNumberCheckStatement.executeQuery();
					
					while(tranRS.next()) {
						count += Integer.parseInt(tranRS.getString("count(transactionId)"));
					} // while
					
					if(count > 0) {
						newTransactionNumber = false;
					} else {
						newTransactionNumber = true;
						transactionId.add(newTran + "");
					}
				} // while
			} // for
		} catch (Exception e) {
			e.printStackTrace();
		} // try catch 
		
		System.out.println("orderNumber: " + orderNumber + " userId: " + userId);
		// PROCESS ORDER DETAILS
		try {
			for(int i = 0; i < carts.size(); i++) {
				Connection con = DatabaseConnection.initializeDatabase();
				
				// fill up order table
				if(i == 0) {
					String orderUpdateQuery = "INSERT INTO `bookstore`.`order` (`orderId`, `orderUserId`, `orderTotal`, `addressStreet`, `addressCity`, `addressState`, `addressZip`, `paymentType`, `paymentCardNumber`, `paymentCardName`, `paymentCardExp`, `paymentCardCVV`) "
							+ "VALUES ('" + orderNumber + "', '" + userId + "', '" + checkoutAmount + "', '" + street + "', '" + city + "', '" + state + "', '" + zip + "', '" + cardType + "', '" + cardNumber + "', '" + cardName + "', '" + cardExp + "', '" + cardCVV + "');";
					PreparedStatement orderUpdateStatement = con.prepareStatement(orderUpdateQuery);
					orderUpdateStatement.execute();
				}
				
				// fill up transaction table
				String transactionUpdateQuery = "INSERT INTO `bookstore`.`transaction` (`transactionId`, `transactionOrderId`, `transactionBookId`, `transactionBookNum`)"
						+ " VALUES ('" + transactionId.get(i) + "', '" + orderNumber + "', '" + booksInCart.get(i) + "', '" + bookQuantityInCart.get(i) + "');";
				PreparedStatement transactionUpdateStatement = con.prepareStatement(transactionUpdateQuery);
				transactionUpdateStatement.execute();
				
				// delete book cart
				String cartUpdateQuery = "DELETE FROM `bookstore`.`cart` WHERE (`cartId` = '" + carts.get(i) + "');";
				PreparedStatement cartUpdateStatement = con.prepareStatement(cartUpdateQuery);
				cartUpdateStatement.execute();
			} // for
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(userEmail != null || !userEmail.equals("")) {
			Properties props = new Properties();
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			SMTPAuthenticator auth = new SMTPAuthenticator();
			Session sess = Session.getInstance(props,auth);
			MimeMessage msg=new MimeMessage(sess);
			try {
				msg.setContent("Thank you for your order. Your order number is " + orderNumber +". Further information about your order will be sent to you once the order is shipped","text/html");
				msg.setSubject("Order " + orderNumber + " Confirmation");
				
				System.out.println("Email to send to:" + request.getParameter("email"));
				msg.setFrom(new InternetAddress("imabookstore@gmail.com"));
				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(userEmail));
				System.out.println("Done setting msg");
				
				Transport trans= sess.getTransport("smtp");
				trans.connect("smtp.gmail.com",465,"imabookstore@gmail.com","Weareima2020");
				trans.sendMessage(msg,msg.getAllRecipients());
				trans.close();
			} catch(Exception e) {
				
				
		        System.out.println("Result: " + e.getMessage() + "\n");
		     
			}
		} // if
		
		System.out.println("Order " + orderNumber + " finished");
		request.setAttribute("orderNumber", orderNumber);
		request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
		
	} // doPost

}
