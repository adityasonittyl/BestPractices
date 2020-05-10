package com.zensar.test.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdate {

	static Connection con = null;
	static Statement smt = null;
	static java.sql.PreparedStatement psmt = null;

	public static void main(String[] args) {
		try {
			String sql = "Select * from employee";
			/*
			 * Can not issue SELECT via executeUpdate()
			 */
			String sql2 = "Insert into employee values(10,\"AAA\",4000)";
			String sql3 = "Update employee set name=\"SKY\" where employeeId=10";
			String sql4 = "Delete from employe where employeeId=100";

			con = ConnectionProvider.getConnection();
			smt = con.createStatement();
			psmt = con.prepareStatement("Insert into employee values(?,?,?)");

			con.setAutoCommit(false);
			// smt.addBatch(sql);
			smt.addBatch(sql2);
			smt.addBatch(sql3);
			smt.addBatch(sql4);
			int[] result = smt.executeBatch();
			for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}

			psmt.setInt(1, 100);
			psmt.setString(2, "BBB");
			psmt.setInt(3, 9000);
			psmt.addBatch();
			psmt.executeBatch();

			con.commit();
			System.out.println("Batch complete");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
