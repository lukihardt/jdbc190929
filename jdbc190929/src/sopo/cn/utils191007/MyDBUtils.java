package sopo.cn.utils191007;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.Driver;

import sopo.cn.model191008.Books;

public class MyDBUtils {
	private MyDBUtils() {
	}

	// 单例模式
	private static MyDBUtils conu = null;

	public static MyDBUtils getInstance() {
		if (conu == null) {
			return new MyDBUtils();
		} else {
			return conu;
		}
	}

	public Connection getConnection()
			throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;

		// 读取类路径的jdbc.properties
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		@SuppressWarnings("deprecation")
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		Connection connection = driver.connect(jdbcUrl, info);
		return connection;
	}
	
	public static Connection getConnectionByC3p0() {
		Connection connection = null;
		DataSource dataSource = new ComboPooledDataSource("mysql");
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

	public Connection getConnectionViaDM() throws IOException, ClassNotFoundException, SQLException {
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		Connection connection = null;

		// 创建Properties对象
		Properties properties = new Properties();
		// 并将配置文件载入到Properties对象的实例里,
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		// 并读出配置文件里的值
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");

		// 加载数据库驱动
		Class.forName(driverClass);
		// 通过DriverManager的getConnection方法拿到数据库的连接
		connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}

	/**
	 * 增改删方法
	 * 
	 * @param sql
	 */
	public void iud(String sql) {
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
	 * 
	 * @param connection
	 * @param statement
	 */
	public static void close2param(Connection connection, Statement statement) {
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

	public static void close3param(Connection connection, Statement statement, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
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

	// not good yet
	public void iudPreparedStatement(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnectionViaDM();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close2param(connection, preparedStatement);
		}
	}

	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Books getBooks(String sql, Object... args) {
		Books books = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = MyDBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				books = new Books();
				books.setId(rs.getInt(1));
				books.setB_name(rs.getString(2));
				books.setB_price(rs.getDouble(3));
				books.setAuthor_id(rs.getInt(4));
				books.setPublish_date(rs.getDate("publish_date"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close3param(connection, preparedStatement, rs);
		}
		return books;
	}

	public static <T> T getData(Class<T> clazz, String sql, Object... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T entity = clazz.getDeclaredConstructor().newInstance();//jdk9以后新方式
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			//// 上面的
			connection = MyDBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			rs = preparedStatement.executeQuery();
			////

			// 得到ResultSetMetaData对象, 调用ResultSet的getMetaData()方法
			ResultSetMetaData rsmd = rs.getMetaData();
			// sql语句中包含了多少列
			int columnCount = rsmd.getColumnCount();
			HashMap<String, Object> columnHashMap = new HashMap<>();
			// 获取指定列的别名,索引从1开始
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String columnLabel = rsmd.getColumnLabel(i);
					Object columnValue = rs.getObject(columnLabel);
					columnHashMap.put(columnLabel, columnValue);
				}
			}
			System.out.println(columnHashMap);
			for (Entry<String, Object> entry : columnHashMap.entrySet()) {
				String key = entry.getKey();
				Object object = entry.getValue();
				Field field = entity.getClass().getDeclaredField(key);
				field.setAccessible(true);
				field.set(entity, object);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close3param(connection, preparedStatement, rs);
		}
		return entity;
	}
}
