/*jdbc�汾������, "DBCP 2.7.0 compiles and runs under Java 8 only (JDBC 4.2)"
package sopo.cn.test191007;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

public class DBConnectionPool {

	@Test
	public void DBCPTest() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		
		//ΪDBCP����Դʵ�������������ݿ��Ҫ������
		dataSource.setUsername("root");
		dataSource.setPassword("F2NRnjVsKe");
		dataSource.setUrl("jdbc:mysql://localhost:3306/books?serverTimezone=Asia/Shanghai");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		//���ÿ�ѡ������Դ����
			//���ݿ����ӳ��г�ʼ����������
		dataSource.setInitialSize(10);
			//ͬһʱ�̿��������ݿ����������������
		dataSource.setMaxTotal(50);
			//���ݿ����ӳ��б�������Ŀ���������
		dataSource.setMaxIdle(5);
			//�ȴ��������ӳط�����ʱ��,����,��ʱ�׳��쳣
		dataSource.setMaxWaitMillis( 1000 * 5);
		
		//������Դ�л�ȡ���ݿ�����
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		dataSource.close();
		
	}
}
*/