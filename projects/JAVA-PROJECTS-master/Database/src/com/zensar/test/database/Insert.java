package com.zensar.test.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.zensar.test.images.ConnectionProvider;

public class Insert {

	public static void main(String[] args) {

		Connection con = null;
		Statement smt = null;
		Scanner scanner = null;
		int count = 0;
		int id = 0;
		String name = null;
		int salary = 0;

		try {
			scanner = new Scanner(System.in);
			System.out.println("Enter employeeId");
			id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter name");
			name = scanner.nextLine();
			System.out.println("Enter salary");
			salary = scanner.nextInt();

			con = ConnectionProvider.getConnection();
			smt = con.createStatement();

			String query ="INSERT INTO HR.employee(employeeId,name,salary) "
					+ "values("
					+ id
					+ ",'"
					+ name
					+ "',"
					+ salary
					+ ")";
			count = smt.executeUpdate(query);
			if (count > 0) {
				System.out.println("Inserted successfully");
			} else {
				System.out.println("Failed to insert");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
					scanner.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

}
