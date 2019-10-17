package sopo.cn.test191007;

import sopo.cn.dao191011.BaseDao;
import sopo.cn.model191008.Books;

public class TestGetDataByBeanUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM book WHERE id = ?;";
			System.out.println(BaseDao.getDataByBeanUtils( Books.class, sql, 6973));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
