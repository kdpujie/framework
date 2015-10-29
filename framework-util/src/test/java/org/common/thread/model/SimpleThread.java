package org.common.thread.model;

public class SimpleThread implements Runnable {

	private String name;
	
	public SimpleThread(String name){
		this.name = name;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" 执行:"+this.name);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
