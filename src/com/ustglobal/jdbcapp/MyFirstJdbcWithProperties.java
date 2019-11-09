package com.ustglobal.jdbcapp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class MyFirstJdbcWithProperties {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		FileReader reader = null;
		

		try {
			reader = new FileReader("db.properties");
			Properties prop = new Properties();
			prop.load(reader);
			
			//Step 1 - Register Driver
			
			Class.forName(prop.getProperty("driver-class-name"));
			
			//step 2 - Get the connection
			
			String url = prop.getProperty("url");
			conn = DriverManager.getConnection(url, prop);
			
			//step 3 - Issue SQL Query
			
			stmt = conn.createStatement();
			String sql = prop.getProperty("select-query");
			rs = stmt.executeQuery(sql);

			//step 4 - Read the result
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sal = rs.getInt("sal");
				String gender = rs.getString("gender");

				System.out.println("Id: " +id);
				System.out.println("Name: " +name);
				System.out.println("Salary: " +sal);
				System.out.println("Gender: " +gender);
				System.out.println("******************");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {						//step 5 - Close all JDBC Objects.-
				if(conn!=null) {
					conn.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
