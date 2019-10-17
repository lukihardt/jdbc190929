package sopo.cn.test191007;

import java.io.File; 
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import sopo.cn.utils191007.MyDBUtils;

public class BlobDemo {

	public static final String driverUrl = "com.mysql.cj.jdbc.Driver";
	public static final String dbUrl = "jdbc:mysql://localhost:3306/books?serverTimezone=Asia/Shanghai";
	public static final String dbUser = "root";
	public static final String dbPass = "F2NRnjVsKe";
	
	public static void main(String[] args) throws Exception{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String name = "444444";
		String sql = "INSERT INTO users( `username`, `password`, `blobdata`) VALUES( ?, ?, ?);";
		Class.forName(driverUrl);
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		preparedStatement = connection.prepareStatement(sql);
		
		File file = new File("F:" + File.separator + "testblob.jpg");
		InputStream inputStream = null;
		inputStream = new FileInputStream(file);
		
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, "123456");
		preparedStatement.setBinaryStream(3, inputStream, file.length());
		
		preparedStatement.executeUpdate();
		
		MyDBUtils.close2param(connection, preparedStatement);
	}
	
}

