package com.zensar.test.oracle.callablestatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ConnectOracle {

	public static void main(String[] args) {

		Connection con = null;

		try {
			con = ConnectionProvider.getConnection();

			String sql ="{?= call HR.FIRST_FX(?,?)}";
			java.sql.CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.setInt(2, 3);
			cs.execute();
			
			System.out.println(cs.getInt(1)+" "+cs.getInt(3));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
