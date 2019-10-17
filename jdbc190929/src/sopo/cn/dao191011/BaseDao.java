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
	 * ͨ�õ���ɾ��
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
	 * ��ȡ�����ѯ������һ����¼�Ķ���ͨ�õ�
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
		T entity = clazz.getDeclaredConstructor().newInstance();// jdk9�Ժ��·�ʽ
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

			// �������
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
			
			//�û�ռλ��
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
	 * ��ȡ�����ѯ�����Ķ�����¼,����һ����¼����ļ���
	 * �ѸĽ�Ϊʹ��c3p0
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
				// ������1��ʼ
				for (int i = 1; i <= columnCount; i++) {
					columnLabel = rsmd.getColumnLabel(i);
					Object columnValue = rs.getObject(columnLabel);
					columnHashMap.put(columnLabel, columnValue);
				}
				listColumnHashMaps.add(columnHashMap);

//				System.out.println(rs.getObject(1));
			}
			System.out.println(listColumnHashMaps);

			// ����entityList
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

	//////////////////////////// �ο�����
//	private List<Map<String, Object>> convertList(ResultSet rs) throws SQLException {
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		ResultSetMetaData md = rs.getMetaData();// ��ȡ����
//		int columnCount = md.getColumnCount();// ��ȡ�е�����
//		while (rs.next()) {
//			Map<String, Object> rowData = new HashMap<String, Object>();// ����Map
//			for (int i = 1; i <= columnCount; i++) {
//				if (md.getColumnName(i).contains("date")) {
//					if (rs.getObject(i) != null) {
//						rowData.put(md.getColumnName(i), rs.getObject(i).toString().substring(0, 19));// ��ȡ������ֵ
//					} else {
//						rowData.put(md.getColumnName(i), "");
//					}
//				} else {
//					if (rs.getObject(i) != null) {
//						rowData.put(md.getColumnName(i), rs.getObject(i));// ��ȡ������ֵ
//					} else {
//						rowData.put(md.getColumnName(i), "");
//					}
//				}
//			}
//			list.add(rowData);
//		}
//		return list;
//	}

	// ��ȡ�����ѯ������ĳ���ֶε�ֵ����ĳ��ͳ���ֶ�(count(*))
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
