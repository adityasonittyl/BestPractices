package com.zensar.test.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatement {

	public static void main(String[] args) {

		Connection con = null;
		java.sql.PreparedStatement pstmt = null;
		Scanner scanner = null;
		int id = 0;
		String name = null;
		int salary = 0;
		String decision = null;

		try {
			do {
				scanner = new Scanner(System.in);
				System.out.println("Enter employeeId");
				id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter name");
				name = scanner.nextLine();
				System.out.println("Enter salary");
				salary = scanner.nextInt();
				con = ConnectionProvider.getConnection();
				String sql = "INSERT INTO employee(employeeId,name,salary)values(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setInt(3, salary);
				if (pstmt.executeUpdate() != 0) {
					System.out.println("Inserted successfully");
				} else {
					System.out.println("Failed to insert");
				}
				System.out.println("Do you wish to continue?(Y/N)");
				decision = scanner.next();
			} while (decision.equalsIgnoreCase("y"));

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
