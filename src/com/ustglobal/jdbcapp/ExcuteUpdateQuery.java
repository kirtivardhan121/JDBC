package com.ustglobal.jdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExcuteUpdateQuery {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			//Step 1 - Load the Driver
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			//Step 2 - Get the Connection
			
			String url = "jdbc:mysql://localhost:3306/ust_ty_db?user=root&password=root";
			conn = DriverManager.getConnection(url);
			
			//Step 3 - Issue SQL Query
			
			String sql = "update employee_info set name = 'XYZ', sal = 50000, gender = 'O' where id = 6";
			stmt = conn.createStatement();
			int count = stmt.executeUpdate(sql);
			
			//step 4 - Read the result

			System.out.println(count + " Row(s) inserted");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
