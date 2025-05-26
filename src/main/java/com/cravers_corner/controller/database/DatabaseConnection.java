package com.cravers_corner.controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	    private final static String databaseName = "cravers_corner";
		private final static String username= "root";
		private final static String password= "";
		private final static String jdbcURL= "jdbc:mysql://localhost:3306/" + databaseName; 
		
		public static Connection getConnection() throws SQLException, ClassNotFoundException{ 
			
			Connection conn = null; 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection(jdbcURL, username, password); 
			return conn;
			
		}
		
		public static void main(String[] args) {
			
			Connection con = null;
			try {
				con = DatabaseConnection.getConnection();
				
				if (con != null) {
					System.out.println("Connection successfull!");
					
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}



