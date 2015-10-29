package org.common.thread.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.common.util.thread.ExecutorServiceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * <br>Date:2015年9月21日 下午1:27:53
 * @author pujie
 */
public class IncrementThread {

	private boolean name;
	private static Lock lock = new ReentrantLock(false);
	public static AtomicInteger ac = new AtomicInteger(0);
	public static int c = 0;
	private ExecutorService executor;
	
	public static void inrement1(){
		ac.incrementAndGet();
	}
	public static void inrement2(){
		lock.lock();
		c ++;
		lock.unlock();
	}
	
	@Before
	public void setUp() throws Exception {
		executor = Executors.newFixedThreadPool(500);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testCPlusPlus2() throws InterruptedException{
		for(int i=0;i<100000;i++){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					IncrementThread.inrement2();
				}
			});
		}
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("testCPlusPlus2 执行结果："+IncrementThread.c);
	}
	@Test
	public void testCPlusPlus1()throws InterruptedException{
		for(int i=0;i<100000;i++){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					IncrementThread.inrement1();
				}
			});
		}
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("testCPlusPlus1 执行结果："+IncrementThread.ac.intValue());
	}

}
