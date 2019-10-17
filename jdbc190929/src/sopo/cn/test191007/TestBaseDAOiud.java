package sopo.cn.test191007;

import java.util.GregorianCalendar;

import sopo.cn.dao191011.BaseDao;

public class TestBaseDAOiud {

//	public static void main(String[] args) { // TODO Auto-generated method stub
//		BaseDao baseDao = new BaseDao();
//		String sql = "INSERT INTO book VALUES( ?, ?, ?, ?, ?);";
//		baseDao.iud(sql, 781, "jdbc", 33.1, 4941317,
//				new java.sql.Date(new GregorianCalendar(2010, 2 - 1, 1).getTime().getTime()));
//	}
	
	public static void main(String[] args) { // TODO Auto-generated method stub
		String sql = "INSERT INTO book VALUES( ?, ?, ?, ?, ?);";
		BaseDao.iud(sql, 164, "jvm", 88.8, 12984,
				new java.sql.Date(new GregorianCalendar(2011, 1-1, 1).getTime().getTime()));
	}

}
