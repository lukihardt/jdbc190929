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

	// ����ģʽ
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

		// ��ȡ��·����jdbc.properties
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

		// ����Properties����
		Properties properties = new Properties();
		// ���������ļ����뵽Properties�����ʵ����,
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		// �����������ļ����ֵ
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcurl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");

		// �������ݿ�����
		Class.forName(driverClass);
		// ͨ��DriverManager��getConnection�����õ����ݿ������
		connection = DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}

	/**
	 * ����ɾ����
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
	 * �رյĹ��߷�����2��������
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
		T entity = clazz.getDeclaredConstructor().newInstance();//jdk9�Ժ��·�ʽ
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			//// �����
			connection = MyDBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			rs = preparedStatement.executeQuery();
			////

			// �õ�ResultSetMetaData����, ����ResultSet��getMetaData()����
			ResultSetMetaData rsmd = rs.getMetaData();
			// sql����а����˶�����
			int columnCount = rsmd.getColumnCount();
			HashMap<String, Object> columnHashMap = new HashMap<>();
			// ��ȡָ���еı���,������1��ʼ
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
