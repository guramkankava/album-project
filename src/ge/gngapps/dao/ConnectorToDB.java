package ge.gngapps.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorToDB {
	
	// Driver to work with DB
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// Address to DB
	private static final String DB_URL = "jdbc:mysql://localhost:3306/albumProject";
	
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			return DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
