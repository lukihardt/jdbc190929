package check;

import java.util.Date;

public class Check {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		long t1 = new Date().getTime();
		long ts = System.currentTimeMillis();
		System.out.println(t1);
		System.out.println(ts);
		
		Thread.sleep(5000);
		
		long t2 = new Date().getTime();
		System.out.println();
		
		System.out.println( "Ê±¼ä¼ä¸ô:" + ( t2 - t1)/1000);
	}

}
