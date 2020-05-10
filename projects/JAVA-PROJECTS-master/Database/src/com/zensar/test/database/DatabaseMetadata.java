package com.zensar.test.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetadata {

	private static Connection con = null;

	public static void main(String[] args) {
		con = ConnectionProvider.getConnection();
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			System.out.println("Database Name: "
					+ dbmd.getDatabaseProductName());
			System.out.println("\nDatabase version: "
					+ dbmd.getDatabaseProductVersion());
			System.out.println("\nDriver Name: " + dbmd.getDriverName());
			System.out.println("\nDriver Version: " + dbmd.getDriverVersion());

			System.out.println("\nSQL Keywords: " + dbmd.getSQLKeywords());
			ResultSet rs = dbmd.getTableTypes();
			
			
			System.out.println("\nTable Types: ");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			String table[] = { "TABLE", "VIEW" };
			ResultSet rs1 = dbmd.getTables(null, null, null, table);

			System.out.println("\nTables in Database:");
			while (rs1.next()) {
				System.out.println(rs1.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
