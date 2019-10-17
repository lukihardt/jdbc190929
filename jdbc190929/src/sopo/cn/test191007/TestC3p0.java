package sopo.cn.test191007;

import org.junit.Test;

import sopo.cn.dao191011.BaseDao;
import sopo.cn.model191008.Books;

public class TestC3p0 {

	@Test
	public void c3p0() throws Exception {
		
		String sql = "SELECT * FROM book WHERE id = ? OR id = ?;";
		System.out.println(BaseDao.getListData(Books.class, sql, 345, 222181));;
	}
}
