package sopo.cn.test191007;

import java.lang.reflect.InvocationTargetException;

import sopo.cn.dao191011.BaseDao;

public class TestGetOneColumn {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		String sql = "SELECT ? FROM book WHERE id = ?;";  ��ռλ��ֻ���滻ֵ���ͣ������滻�������ֶ������������ؼ��ʡ���
		String sql = "SELECT count(*) FROM book WHERE id < ?;";
		String i = BaseDao.getOneColumn(String.class, sql, 8888);//count(*)�����ﵱ��String�������
		System.out.println(i);
	}
}
