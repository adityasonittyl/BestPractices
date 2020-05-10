package com.zensar.test.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Alter {

	public static void main(String[] args) {

		Connection con = null;
		Statement smt = null;
		int count = 0;

		try {
			con = ConnectionProvider.getConnection();

			smt = con.createStatement();
			//String sql = "ALTER TABLE employee ADD PRIMARY KEY(employeeId)";
			String sql2 = "ALTER TABLE employee DROP PRIMARY KEY"; 
			count = smt.executeUpdate(sql2);
			if (count > 0) {
				System.out.println("Altered successfully");
			} else {
				System.out.println("Failed to alter");
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
