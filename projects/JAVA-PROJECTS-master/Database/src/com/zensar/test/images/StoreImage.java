package com.zensar.test.images;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreImage {

	public static void main(String[] args) {

		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();

			String sql = "INSERT INTO HR.IMAGE VALUES(?,?)";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "sky");

			FileInputStream fs = new FileInputStream(".\\src\\source\\sky.jpg");
			psmt.setBinaryStream(2, fs, fs.available());

			int result = psmt.executeUpdate();
			if (result > 0)
				System.out.println("Added successfully");
			else
				System.out.println("Failed to add");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

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
