package sopo.cn.utils191007;

import java.io.IOException;  
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

public class DBUtils {
	private DBUtils() {}
	
	//单例模式
	private static DBUtils conu = null;
	public static DBUtils getInstance() {
		if (conu == null) {
			return new DBUtils();
		} else {
			return conu;
		}
	}
	
	public Connection getConnection() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//读取类路径的jdbc.properties
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		@SuppressWarnings("deprecation")
		Driver driver = (Driver)Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put( "user", user);
		info.put( "password", password);
		Connection connection = driver.connect(jdbcUrl, info);
		return connection;
	}
	
	public Connection getConnectionViaDM() throws IOException, ClassNotFoundException, SQLException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		Connection connection = null;
		
		//创建Properties对象
		Properties properties = new Properties();
		//并将配置文件载入到Properties对象的实例里,
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		//并读出配置文件里的值
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		//加载数据库驱动
		Class.forName(driverClass);
		//通过DriverManager的getConnection方法拿到数据库的连接
		connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}
	
	/**
	 * 增改删方法
	 * @param sql
	 */
	public void iud( String sql) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = getConnectionViaDM();
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
//			try {
//				if (statement != null) {
//					statement.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
			
			close2param(connection, statement);
		}
	}
	
	/**
	 * 关闭的工具方法（2个参数）
	 * @param connection
	 * @param statement
	 */
	public void close2param( Connection connection, Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void close3param( Connection connection, Statement statement, ResultSet rs) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
