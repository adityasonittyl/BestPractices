package com.zensar.test.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatement {

	public static void main(String[] args) {

		Connection con = null;
		java.sql.CallableStatement cs = null;
		ResultSet rs = null;

		try {
			//String sql = "Select * from employee WHERE employeeId = ?";
			String sql = "{CALL EMPLOYEE(?)}";
			con = ConnectionProvider.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, 2);
			rs = cs.executeQuery();

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
