package org.common.thread;

import static org.junit.Assert.*;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * volatile测试<br>
 * 锁提供了两种主要特性：互斥（mutual exclusion） 和可见性（visibility）。volatile能只能实现锁的部分功能，即可见性。无法确保操作的原子性。<br>
 * 用锁的关键：分清<对象锁><类锁>。如果对象锁，要注意是不是同一个对象锁。
 * <br>Date:2015年12月17日 下午3:00:21
 * @author pujie
 */
public class VolatileTest {

	static int a=0,b=0;
	static boolean flag = true;
	private ExecutorService executor;
	static ReentrantLock lock=new ReentrantLock(); 
	@Before
	public void setUp() throws Exception {
		executor = Executors.newCachedThreadPool();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException, BrokenBarrierException {
		final int taskNum = 50;
	    final CyclicBarrier cb=new CyclicBarrier(taskNum+1);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("读取开始：a="+a+" ,b="+b);
				while(flag){
					printNoEqu();
				}
				System.out.println("读取结束：a="+a+" ,b="+b);
			}
		}).start();
		for(int i=0;i<taskNum;i++){ //
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						inc();
//						System.out.println("线程"+Thread.currentThread().getName()+"到达集合地点，当前已有"+(cb.getNumberWaiting()+1)+"个已到达."+
//						        (cb.getNumberWaiting()==taskNum?"都到齐了，继续走啊":"正在等候"));
						cb.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		cb.await();
		flag = false;
		System.out.println("主线程退出");
	}
	/**
	 * 当i不等于j时，打印.
	 * 非原子操作
	 * <br>Date:2015年12月17日 下午3:07:00
	 * @author pujie
	 */
	static void printNoEqu(){
		lock.lock();
		int tempA =a,tempB=b;
		lock.unlock();
		if(tempA !=tempB){
			System.out.println("a="+tempA+" ,b="+tempB);
		}
	}
	/**
	 * 操作的原子性
	 * <br>Date:2015年12月17日 下午3:03:54
	 * @author pujie
	 */
	static  void inc() {
		lock.lock();
        a++;
        b++;
        lock.unlock();
    }
}
