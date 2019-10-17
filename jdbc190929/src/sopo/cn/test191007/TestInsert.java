package sopo.cn.test191007;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import sopo.cn.utils191007.MyDBUtils;

public class TestInsert {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		Connection connection = MyDBUtils.getInstance().getConnection();
		
		String sql = "INSERT INTO book VALUES( '257', 'springBoot', '39.1', '3279113', '2019-1-1');";
		Statement statement = (Statement) connection.createStatement();
		statement.executeUpdate(sql);
		statement.close();
		connection.close();
		
	}
}
