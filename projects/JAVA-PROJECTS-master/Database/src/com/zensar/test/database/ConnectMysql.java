package com.zensar.test.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMysql {

	public static void main(String[] args) {

		Connection con = null;

		try {
			// 1st way to load class - Preferred
			Class.forName("com.mysql.jdbc.Driver");

			// 2nd way to load class - Not Preferred
			// Driver d = new com.mysql.jdbc.Driver();
			// DriverManager.registerDriver(d);

			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mydb", "root", "root");

			System.out.println("Connected");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
