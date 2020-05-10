package com.zensar.test.images;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetImage {

	public static void main(String[] args) {

		Connection con = null;
		FileOutputStream file = null;
		InputStream stream = null;
		int i;
		try {
			con = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM HR.IMAGE";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				i = 0;
				String fileName = ".\\src\\destination\\" + rs.getString(1)
						+ ".jpg";
				file = new FileOutputStream(fileName);
				stream = rs.getBinaryStream(2);
				while ((i = stream.read()) > -1) {
					file.write(i);
				}
			}

		} catch (SQLException e) {

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
