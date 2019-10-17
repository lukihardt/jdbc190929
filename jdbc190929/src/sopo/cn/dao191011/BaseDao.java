package sopo.cn.dao191011;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import sopo.cn.utils191007.MyDBUtils;

public class BaseDao {

	/**
	 * 通用的增删改
	 * 
	 * @param sql
	 * @param args
	 */
	public static void iud(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = MyDBUtils.getInstance().getConnectionViaDM();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close2param(connection, preparedStatement);
		}
	}

	/**
	 * 获取满足查询条件的一条记录的对象，通用的
	 * 
	 * @param <T>
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T> T getData(Class<T> clazz, String sql, Object... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		T entity = clazz.getDeclaredConstructor().newInstance();// jdk9以后新方式
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

			// 放入对象
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

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T> T getDataByBeanUtils(Class<T> clazz, String sql, int args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		T t = clazz.getDeclaredConstructor().newInstance();
		
		try {
			
			connection = MyDBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			//置换占位符
			preparedStatement.setObject(1, args);	
			
			rs = preparedStatement.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int countColumn = rsmd.getColumnCount();
			
			while (rs.next()) {
				for (int i = 0; i < countColumn; i++) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
//					System.out.println(columnLabel);
					BeanUtils.setProperty( t, columnLabel, rs.getObject( i + 1));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close3param(connection, preparedStatement, rs);
		}
		
		return t;
	}

	/**
	 * 获取满足查询条件的多条记录,返回一个记录对象的集合
	 * 已改进为使用c3p0
	 * @param <T>
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T> List<T> getListData(Class<T> clazz, String sql, Object... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		List<T> entityList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = MyDBUtils.getConnectionByC3p0();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			rs = preparedStatement.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			List<HashMap<String, Object>> listColumnHashMaps = new ArrayList<>();

			String columnLabel = "";
			while (rs.next()) {
				HashMap<String, Object> columnHashMap = new HashMap<>();
				// 索引从1开始
				for (int i = 1; i <= columnCount; i++) {
					columnLabel = rsmd.getColumnLabel(i);
					Object columnValue = rs.getObject(columnLabel);
					columnHashMap.put(columnLabel, columnValue);
				}
				listColumnHashMaps.add(columnHashMap);

//				System.out.println(rs.getObject(1));
			}
			System.out.println(listColumnHashMaps);

			// 放入entityList
			for (int i = 0; i < listColumnHashMaps.size(); i++) {
				T entity = clazz.getDeclaredConstructor().newInstance();
				for (Entry<String, Object> entry : listColumnHashMaps.get(i).entrySet()) {
					String key = entry.getKey();
					Object object = entry.getValue();
					Field field = entity.getClass().getDeclaredField(key);
					field.setAccessible(true);
					field.set(entity, object);
				}
				entityList.add(entity);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close3param(connection, preparedStatement, rs);
		}
		return entityList;
	}

	//////////////////////////// 参考资料
//	private List<Map<String, Object>> convertList(ResultSet rs) throws SQLException {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		ResultSetMetaData md = rs.getMetaData();// 获取键名
//		int columnCount = md.getColumnCount();// 获取行的数量
//		while (rs.next()) {
//			Map<String, Object> rowData = new HashMap<String, Object>();// 声明Map
//			for (int i = 1; i <= columnCount; i++) {
//				if (md.getColumnName(i).contains("date")) {
//					if (rs.getObject(i) != null) {
//						rowData.put(md.getColumnName(i), rs.getObject(i).toString().substring(0, 19));// 获取键名及值
//					} else {
//						rowData.put(md.getColumnName(i), "");
//					}
//				} else {
//					if (rs.getObject(i) != null) {
//						rowData.put(md.getColumnName(i), rs.getObject(i));// 获取键名及值
//					} else {
//						rowData.put(md.getColumnName(i), "");
//					}
//				}
//			}
//			list.add(rowData);
//		}
//		return list;
//	}

	// 获取满足查询条件的某个字段的值或者某个统计字段(count(*))
	@SuppressWarnings("unchecked")
	public static <T> T getOneColumn(Class<T> classType, String sql, Object... args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		T t = classType.getDeclaredConstructor().newInstance();

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
			System.out.println("--" + t.getClass());

			System.out.println(classType.getCanonicalName());
			System.out.println(classType.getName());
			System.out.println(classType.getSimpleName());

			while (rs.next()) {
				if (classType.getSimpleName().equals("String")) {
					t = (T) rs.getString(1);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			MyDBUtils.close3param(connection, preparedStatement, rs);
		}
		return t;
	}
}
