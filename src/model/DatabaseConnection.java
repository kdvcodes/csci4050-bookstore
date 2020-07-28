package model;

import java.sql.*;

public class DatabaseConnection {
	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "bookstore";
		String dbUsername = "root";
		String dbPassword = "9995";
		String dbOptions = "?serverTimezone=UTC&useSSL=false";
		
		Class.forName(dbDriver);
		Connection connection = DriverManager.getConnection(dbURL + dbName + dbOptions, dbUsername, dbPassword);
		
		return connection;
	}
}
