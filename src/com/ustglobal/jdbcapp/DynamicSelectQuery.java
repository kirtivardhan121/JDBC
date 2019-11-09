package com.ustglobal.jdbcapp;

import java.sql.*;
import com.mysql.jdbc.Driver;
public class DynamicSelectQuery {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/ust_ty_db?user=root&password=root";
		String sql = "select * from employee_info where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			conn = DriverManager.getConnection(url);
			
			pstmt = conn.prepareStatement(sql);
			String empid = args[0];
			int id = Integer.parseInt(empid);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int eid = rs.getInt("id");
				int sal= rs.getInt("sal");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				System.out.println("Id : " +id);
				System.out.println("Name: " +name);
				System.out.println("Salary: " +sal);
				System.out.println("Gender: " +gender);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {						
				if(conn!=null) {
					conn.close();
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
