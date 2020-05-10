package com.zensar.test.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaData {

	private static Connection con = null;
	private static Statement smt = null;
	private static ResultSet rs = null;
	private static java.sql.ResultSetMetaData rsmd = null;

	public static void main(String[] args) {
		con = ConnectionProvider.getConnection();
		try {
			smt = con.createStatement();
			rs = smt.executeQuery("Select * from employee");
			rsmd = rs.getMetaData();
			rsmd.getColumnCount();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(rsmd.getColumnName(i) + " ");
				System.out.print(rsmd.getColumnType(i) + " ");
				System.out.print(rsmd.getColumnTypeName(i) + " ");
				System.out.print(rsmd.getColumnDisplaySize(i) + " ");
				System.out.println(rsmd.getTableName(i));
			}
			while (rs.next() == true) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					System.out.print(rs.getString(i) + " ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
