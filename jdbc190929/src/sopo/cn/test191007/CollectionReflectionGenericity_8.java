package sopo.cn.test191007;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
		
		System.out.println("------------------");
		
		//获取类的包名
		System.out.println(booksClass.getPackage().getName());
		
		System.out.println("------------------");
		
		//获取类的所有public方法
		Method[] methodStrings = booksClass.getMethods();
		for (int i = 0; i < methodStrings.length; i++) {
			System.out.println(methodStrings[i]);
		}
		
		System.out.println("------------------");
		
		//获取指定方法
		System.out.println(booksClass.getMethod("setId", int.class));
		
		System.out.println("------------------");
		
		//获取到的所有public字段,然而都是private的
		Field[] fields = booksClass.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}
		
		System.out.println("------------------");
		
		//获取所有的方法
		Method[] andPrivateMtd = booksClass.getDeclaredMethods();
		for (int i = 0; i < andPrivateMtd.length; i++) {
			System.out.println(andPrivateMtd[i]);
		}
		
		System.out.println("------------------");
		
		//获取所有属性
		Field[] andPrivateFields = booksClass.getDeclaredFields();
		for (int i = 0; i < andPrivateFields.length; i++) {
			System.out.println(andPrivateFields[i]);
		}
		
		//获取属性值
		for (int i = 0; i < andPrivateFields.length; i++) {
			andPrivateFields[i].setAccessible(true);
		}
		
		System.out.println(andPrivateFields[0].get(books2));
		System.out.println(andPrivateFields[1].get(books2));
		System.out.println(andPrivateFields[2].get(books2));
		System.out.println(andPrivateFields[3].get(books2));
		System.out.println(andPrivateFields[4].get(books2));
		
		//"空击"
//		Collection<String> collection = new ArrayList<>();
//		Collection collection2 = new ArrayList<String>();
//		Collection<String> collection3 = new ArrayList<>();
//		Collection<String> collection4 = new ArrayList<String>();
		Person<Integer> p1 = new Person<>();
		Person<Float> p2 = new Person<>();
		//空击变实战...
		System.out.println("------------------");
		System.out.println("------------------");
		printlnP(p1);
		printlnP(p2);
	}
	
	//"空击"
	public void printlnP(Person<?> person) {
		System.out.println(person);
	}
	
	// "空击"
	public<T> T genericMethod() {
		T t = null;
		return t;
	}
	
}

	//"空击"
		//定义类的时候使用泛型
	class Person<T>{
		
	}
	class Person2<T> {
		private T age;
		public Person2(T age) {
			this.age = age;
		}
		public void printAge() {
			System.out.println(getAge());
		}
		public T getAge() {
			return age;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
