package sopo.cn.test191007;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import org.junit.Test;

import sopo.cn.utils191007.MyDBUtils;

public class TestBatch {

	@Test
	public void noBatch(){
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO students VALUES( ?, ?, '男', '2017-1-1', 11661, 31);";
		
		long t1 = new Date().getTime();
		System.out.println("开始时间:" + t1);
		
		try {
			connection = MyDBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < 10000; i++) {
				
				preparedStatement.setInt( 1, i + 9);
				preparedStatement.setString( 2, String.valueOf( i + 9));
				preparedStatement.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close2param(connection, preparedStatement);
		}
		
		long t2 = new Date().getTime();
		System.out.println("结束时间:" + t2);
		System.out.println("间隔" + ( t2 - t1) / 1000 + "秒");
	}
	
	@Test
	public void haveBatch() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO students VALUES( ?, ?, '男', '2017-1-1', 11661, 31);";
		
		long t1 = new Date().getTime();
		System.out.println("开始时间:" + t1);
		
		try {
			connection = MyDBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < 10000; i++) {
				preparedStatement.setInt( 1, i + 9);
				preparedStatement.setString( 2, String.valueOf( i + 9));
				preparedStatement.addBatch();
				
				if (i % 1000 == 0) {
					preparedStatement.executeBatch();
					preparedStatement.clearBatch();
				}
				
				if (i == 9999) {
					preparedStatement.executeBatch();
					preparedStatement.clearBatch();
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close2param(connection, preparedStatement);
		}
		
		long t2 = new Date().getTime();
		System.out.println("结束时间:" + t2);
		System.out.println("间隔" + ( t2 - t1) / 1000 + "秒");
	}

}
