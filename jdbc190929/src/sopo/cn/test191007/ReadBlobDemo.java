package sopo.cn.test191007;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sopo.cn.utils191007.MyDBUtils;

public class ReadBlobDemo {

	public static final String DRIVER_STRING = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/books?serverTimezone=Asia/Shanghai";
	public static final String DBUSER = "root";
	public static final String DBPASS = "F2NRnjVsKe";

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM users WHERE username = ?;";
		Class.forName(DRIVER_STRING);
		connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, "444444");

		rs = preparedStatement.executeQuery();

		if (rs.next()) {
			String username = rs.getString(1);
			String password = rs.getString(2);
			System.out.println("username" + username);
			System.out.println("password" + password);
			InputStream inputStream = rs.getBinaryStream(3);
			File file = new File("T:" + File.separator + "out.jpg");

			OutputStream outputStream = null;
			outputStream = new FileOutputStream(file);

			int temp = 0;

			while ((temp = inputStream.read()) != -1) {
				outputStream.write(temp);
			}
			inputStream.close();
			outputStream.close();
		}

		MyDBUtils.close3param(connection, preparedStatement, rs);

	}

}
