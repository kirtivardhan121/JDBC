package com.ustglobal.jdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DynamicInsertQuery {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//Step 1 - Load the Driver

			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			//step 2 - Get the Connection

			String url = "jdbc:mysql://localhost:3306/ust_ty_db?user=root&password=root";
			conn = DriverManager.getConnection(url);

			//step 3 - Issue SQL Query

			String sql = "insert into employee_info values (?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			String empid = args[0];
			int id = Integer.parseInt(empid);
			pstmt.setInt(1, id);
			
			String name = args[1];
			pstmt.setString(2, name);
			
			String empsal = args[2];
			int sal = Integer.parseUnsignedInt(empsal);
			pstmt.setInt(3, sal);
			
			String gender = args[3];
			pstmt.setString(4, gender);
			
			int count = pstmt.executeUpdate();
			
			//step 4 - Read the result

			System.out.println(count + " Row(s) inserted");

		}catch (SQLException e) {
			e.printStackTrace();

		} finally {					//step 5 - close all the JDBC Object.
			try {
				if (conn!=null) {
					conn.close();
				}
				if (pstmt!=null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}


	}//end of main()
}//end of ExecuteInsertQuery 
