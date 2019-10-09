package sopo.cn.test191007;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import sopo.cn.utils191007.DBUtils;

public class TestMain {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		DBUtils dbUtils = DBUtils.getInstance();
		
		// get Connection via "Driver"
		System.out.println(dbUtils.getConnection());
		
		// get Connection via "DriverManager"
		System.out.println(dbUtils.getConnectionViaDM());
		
		// test iud method
		dbUtils.iud("INSERT INTO book VALUES( '801', 'springboot', '56.1', '49463879', '2015-1-1');");
		
	}
	
	@Test
	public void testclose2() {
		DBUtils dbUtils = DBUtils.getInstance();
		dbUtils.iud("INSERT INTO book VALUES( '07819', 'spring', '53.9', '946894', '2017-1-1');");
		// iud方法会调用close2param方法进行关闭操作
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGregorianCalendar() {
		System.out.println( new java.sql.Date(new Date(2018, 1, 1).getTime()));
	}

}
