package sopo.cn.test191007;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.GregorianCalendar;

import org.junit.Test;

import sopo.cn.utils191007.DBUtils;

public class PreparedStatementDemo {
	Connection connection = null;
	PreparedStatement ps = null;
	int count = 0;
	@Test
	public void test() {
		// TODO Auto-generated method stub
		try {
			connection = DBUtils.getInstance().getConnection();
			String sql = "INSERT INTO book VALUES( ?, ?, ?, ?, ?);";
			ps = connection.prepareStatement(sql);
			ps.setInt( 1, 6973);
			ps.setString( 2, "svn");
			ps.setDouble( 3, 27.1);
			ps.setInt( 4, 194318);
			ps.setDate( 5, new java.sql.Date(new GregorianCalendar( 2014, 1-1, 1).getTime().getTime()));
			count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtils.close2param(connection, ps);
			System.out.println("insert success");
		}
	}

}
