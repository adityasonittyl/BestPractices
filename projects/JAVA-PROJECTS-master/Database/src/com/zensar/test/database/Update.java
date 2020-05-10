package com.zensar.test.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.zensar.test.images.ConnectionProvider;

public class Update {

	public static void main(String[] args) {

		Connection con = null;
		Statement smt = null;
		int count = 0;

		try {
			con = ConnectionProvider.getConnection();

			smt = con.createStatement();
			count = smt
					.executeUpdate("UPDATE HR.EMPLOYEE SET name='SKY' WHERE employeeId=1");
			if (count > 0) {
				System.out.println("Updated successfully");
			} else {
				System.out.println("Failed to insert");
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
