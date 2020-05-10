package com.zensar.test.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableRowsetUpdate {

	static Connection con = null;
	static Statement smt = null;
	static ResultSet rs, rs2 = null;

	public static void main(String[] args) {
		System.out.println("hi");
		con = ConnectionProvider.getConnection();
		try {
			smt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			rs = smt.executeQuery("Select * from employee where employeeId=6");
			rs.next();
			rs.updateString(2, "QQQ");
			rs.updateInt(3, 5000);
			rs.updateRow();

			rs2 = smt.executeQuery("Select * from employee");
			rs2.next();
			rs2.moveToInsertRow();
			rs2.updateInt(1, 10);
			rs2.updateString(2, "GGG");
			rs2.updateInt(3, 9000);
			rs2.insertRow();

			/*
			 * while (rs.next()) { System.out.println(rs.getInt(1) + " " +
			 * rs.getString(2) + " " + rs.getString(3)); }
			 */
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
