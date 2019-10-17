package sopo.cn.test191007;

import sopo.cn.dao191011.BaseDao;
import sopo.cn.model191008.Books;

public class TestgetListData {
	public static void main(String[] args) {
		String sql = "SELECT * FROM book WHERE id = ? OR id = ?;";
		try {
			System.out.println(BaseDao.getListData( Books.class, sql, 345, 222181));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
