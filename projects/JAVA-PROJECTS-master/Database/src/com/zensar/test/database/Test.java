package com.zensar.test.database;

import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		Connection con = ConnectionProvider.getConnection();
		if (con == null) {
			System.out.println("Not connected");
		} else {
			System.out.println("Connected");
			
		}

	}

}
