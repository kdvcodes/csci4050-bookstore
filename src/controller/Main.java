package controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String url = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "9995";
        
        
        Connection connection;
		try {
			connection = DriverManager.getConnection(url, user, password);
	        System.out.println("Connected to MySQL Server!");
	        
	        Statement statement = connection.createStatement();
	        String query = "select * from book where bookId = 1";
	        ResultSet resultSet = statement.executeQuery(query);
	        	
	        while(resultSet.next()) {
	        	System.out.println(resultSet.getString("bookName"));
	        }
	        
	        connection.close();
	        statement.close();
	        resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
