package com.zensar.test.oracle.callablestatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Procedure {

	public static void main(String[] args) {

		Connection con = null;
		java.sql.CallableStatement cs = null;

		try {
			String sql = "{call HR.ADDITION(?,?,?)}";
			con = ConnectionProvider.getConnection();
			cs = con.prepareCall(sql);
			cs.registerOutParameter(3, Types.INTEGER);

			cs.setInt(1, 10);
			cs.setInt(2, 20);
			cs.execute();

			int result = cs.getInt(3);
			System.out.println(result);

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
