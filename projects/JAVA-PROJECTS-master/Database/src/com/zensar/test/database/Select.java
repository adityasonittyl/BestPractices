package com.zensar.test.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Select {

	public static void main(String[] args) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionProvider.getConnection();
			smt = con.createStatement();
			rs = smt.executeQuery("Select * from employee");
		
			while (rs.next() == true) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getInt(3));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

}
