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
		//ͨ��Class����Ϣ����������: newInstance()
		@SuppressWarnings("deprecation")
		Books books1 = (Books) booksClass.newInstance();
		System.out.println(books1);
		//ͨ��Class����Ϣ��ȡ��ʹ�ù��췽��: getConstructors()��getConstructor()
		Constructor<?> constructor = booksClass.getConstructor(int.class, String.class, double.class, int.class, java.sql.Date.class);
		Books books2 = (Books) constructor.newInstance( 1451, "name1231", 11.2, 13284, new java.sql.Date(new GregorianCalendar( 2014, 1-1, 1).getTime().getTime()));
		System.out.println(books2);
		
		System.out.println("------------------");
		
		//��ȡ��İ���
		System.out.println(booksClass.getPackage().getName());
		
		System.out.println("------------------");
		
		//��ȡ�������public����
		Method[] methodStrings = booksClass.getMethods();
		for (int i = 0; i < methodStrings.length; i++) {
			System.out.println(methodStrings[i]);
		}
		
		System.out.println("------------------");
		
		//��ȡָ������
		System.out.println(booksClass.getMethod("setId", int.class));
		
		System.out.println("------------------");
		
		//��ȡ��������public�ֶ�,Ȼ������private��
		Field[] fields = booksClass.getFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i]);
		}
		
		System.out.println("------------------");
		
		//��ȡ���еķ���
		Method[] andPrivateMtd = booksClass.getDeclaredMethods();
		for (int i = 0; i < andPrivateMtd.length; i++) {
			System.out.println(andPrivateMtd[i]);
		}
		
		System.out.println("------------------");
		
		//��ȡ��������
		Field[] andPrivateFields = booksClass.getDeclaredFields();
		for (int i = 0; i < andPrivateFields.length; i++) {
			System.out.println(andPrivateFields[i]);
		}
		
		//��ȡ����ֵ
		for (int i = 0; i < andPrivateFields.length; i++) {
			andPrivateFields[i].setAccessible(true);
		}
		
		System.out.println(andPrivateFields[0].get(books2));
		System.out.println(andPrivateFields[1].get(books2));
		System.out.println(andPrivateFields[2].get(books2));
		System.out.println(andPrivateFields[3].get(books2));
		System.out.println(andPrivateFields[4].get(books2));
		
		//"�ջ�"
//		Collection<String> collection = new ArrayList<>();
//		Collection collection2 = new ArrayList<String>();
//		Collection<String> collection3 = new ArrayList<>();
//		Collection<String> collection4 = new ArrayList<String>();
		Person<Integer> p1 = new Person<>();
		Person<Float> p2 = new Person<>();
		//�ջ���ʵս...
		System.out.println("------------------");
		System.out.println("------------------");
		printlnP(p1);
		printlnP(p2);
	}
	
	//"�ջ�"
	public void printlnP(Person<?> person) {
		System.out.println(person);
	}
	
	// "�ջ�"
	public<T> T genericMethod() {
		T t = null;
		return t;
	}
	
}

	//"�ջ�"
		//�������ʱ��ʹ�÷���
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
	
	
	
	
	
	
	
	
	
	
	
	
	
