package sopo.cn.test191007;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ReadBlobDemo2 {

	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/books?serverTimezone=Asia/Shanghai";
	public static final String USER = "root";
	public static final String DBPASS = "F2NRnjVsKe";
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int id = 444444;
		String sql = "SELECT * FROM users WHERE username = ?;";
		
		Class.forName(DBDRIVER);
		connection = DriverManager.getConnection(DBURL, USER, DBPASS);
		preparedStatement = connection.prepareStatement(sql); 
		preparedStatement.setInt(1, id);
		
		rs = preparedStatement.executeQuery();
		
		if (rs.next()) {
			String name = rs.getString(1);
			String pwd = rs.getString(2);
			System.out.println("username:"+name);
			System.out.println("password:"+pwd);
			
			Blob blob = rs.getBlob(3);
			
			File f = new File( "T:" + File.separator + "out2.jpg");
			
			OutputStream out = null;
			out = new FileOutputStream(f);
			
			out.write(blob.getBytes(1, (int) blob.length()));
			
			out.close();
		}
		
		connection.close();
		preparedStatement.close();
	}

}
