package org.common.thread.model;

import java.util.Random;

import org.apache.log4j.helpers.ThreadLocalMap;
import org.common.util.NumberUtil;

public class ThreadLocalDemo implements Runnable {

	static ThreadLocal<Student> session = new ThreadLocal<Student>(){

		@Override
		protected Student initialValue() {
			Student s = new Student();
			s.setId(Thread.currentThread().getId());
			s.setName(Thread.currentThread().getName());
			s.setCode(new Random().nextInt(5000));
			return s;
		}
		
	};
	
	@Override
	public void run() {
//		System.out.println(Thread.currentThread().getName()+":start ");
		try {
			Student s = session.get();
			Thread.sleep(200);
			System.out.println(Thread.currentThread().getName()+" : "+s.getId()+" "+s.getName()+" "+s.getCode());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
