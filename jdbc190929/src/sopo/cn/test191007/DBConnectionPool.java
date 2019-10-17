/*jdbc版本不兼容, "DBCP 2.7.0 compiles and runs under Java 8 only (JDBC 4.2)"
package sopo.cn.test191007;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

public class DBConnectionPool {

	@Test
	public void DBCPTest() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		
		//为DBCP数据源实例设置连接数据库必要的属性
		dataSource.setUsername("root");
		dataSource.setPassword("F2NRnjVsKe");
		dataSource.setUrl("jdbc:mysql://localhost:3306/books?serverTimezone=Asia/Shanghai");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		//设置可选的数据源属性
			//数据库连接池中初始化的连接数
		dataSource.setInitialSize(10);
			//同一时刻可以向数据库申请的最大的连接数
		dataSource.setMaxTotal(50);
			//数据库连接池中保存的最大的空闲连接数
		dataSource.setMaxIdle(5);
			//等待数据连接池分配的最长时间,毫秒,超时抛出异常
		dataSource.setMaxWaitMillis( 1000 * 5);
		
		//从数据源中获取数据库连接
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		dataSource.close();
		
	}
}
*/