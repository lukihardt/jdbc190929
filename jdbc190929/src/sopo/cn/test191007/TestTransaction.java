package sopo.cn.test191007;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import sopo.cn.dao191011.BaseDao;
import sopo.cn.utils191007.MyDBUtils;

public class TestTransaction {

	public void noTransaction() {
		// TODO Auto-generated method stub
		String sql = "update account set balance = 9999 where id = 1;";
		BaseDao.iud(sql);
		
		int a = 10/0;
		System.out.println(a);
		
		String sql2 = "update account set balance = 6666 where id = 2;";
		BaseDao.iud(sql2);
	}
	
	@Test
	public void havaTranaction() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		String sql = "update account set balance = 9999 where id = 1;";
		String sql2 = "update account set balance = 6666 where id = 2;";
		try {
			connection = MyDBUtils.getInstance().getConnectionViaDM();
			connection.setAutoCommit(false);
			preparedStatement1 = connection.prepareStatement(sql);
			preparedStatement1.executeUpdate();
//			System.out.println(10/0);
			preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.executeUpdate();
			
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
			preparedStatement1.close();
			preparedStatement2.close();
		}
	}

}
