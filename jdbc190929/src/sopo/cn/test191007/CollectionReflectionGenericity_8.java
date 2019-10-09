package sopo.cn.test191007;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.GregorianCalendar;

import org.junit.Test;

import sopo.cn.model191008.Books;

public class CollectionReflectionGenericity_8 {
	@Test
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> booksClass = Class.forName("sopo.cn.model191008.Books");
		//通过Class类信息来创建对象: newInstance()
		@SuppressWarnings("deprecation")
		Books books1 = (Books) booksClass.newInstance();
		System.out.println(books1);
		//通过Class类信息获取和使用构造方法: getConstructors()和getConstructor()
		Constructor<?> constructor = booksClass.getConstructor(int.class, String.class, double.class, int.class, java.sql.Date.class);
		Books books2 = (Books) constructor.newInstance( 1451, "name1231", 11.2, 13284, new java.sql.Date(new GregorianCalendar( 2014, 1-1, 1).getTime().getTime()));
		System.out.println(books2);
	}
}
