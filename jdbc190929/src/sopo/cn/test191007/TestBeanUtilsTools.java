package sopo.cn.test191007;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import sopo.cn.model191008.Books;

public class TestBeanUtilsTools {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		Books booksBean = new Books();
		BeanUtils.setProperty( booksBean, "id", 123); 
		BeanUtils.setProperty( booksBean, "b_name", "thinking in j"); 
		BeanUtils.setProperty( booksBean, "b_price", 87.7); 
		BeanUtils.setProperty( booksBean, "author_id", 110001); 
		BeanUtils.setProperty( booksBean, "publish_date", "2019-05-05"); 
		
		System.out.println(booksBean.toString());;
		System.out.println(BeanUtils.getProperty(booksBean, "b_price"));
	}

}
