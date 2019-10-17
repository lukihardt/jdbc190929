package sopo.cn.dao191011;

import java.io.IOException; 
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

@Deprecated
public class DataSourceFactory {

	public static Connection getDBCPConnection() throws IOException {
		Properties properties = new Properties();
		InputStream in = DataSourceFactory.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		//////因最新的dbcp不兼容java 8 以上的，此实验终止
		return null;
	}
	
}
