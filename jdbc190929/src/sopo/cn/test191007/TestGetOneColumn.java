package sopo.cn.test191007;

import java.lang.reflect.InvocationTargetException;

import sopo.cn.dao191011.BaseDao;

public class TestGetOneColumn {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		String sql = "SELECT ? FROM book WHERE id = ?;";  ☆占位符只能替换值类型，不能替换表名、字段名或者其他关键词。☆
		String sql = "SELECT count(*) FROM book WHERE id < ?;";
		String i = BaseDao.getOneColumn(String.class, sql, 8888);//count(*)在这里当作String类型输出
		System.out.println(i);
	}
}
