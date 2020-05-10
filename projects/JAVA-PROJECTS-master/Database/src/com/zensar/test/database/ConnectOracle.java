package com.zensar.test.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectOracle {

	public static void main(String[] args) {

		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE", "system", "zensar");
			System.out.println("Connected");

			smt = con.createStatement();
			rs = smt.executeQuery("SELECT * FROM hr.employee");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
