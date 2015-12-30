package org.common.thread;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.common.thread.lock.AtomicLongTest;
import org.common.thread.lock.LockTest;
import org.common.thread.lock.SynchronizedTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * java锁机制测试
 * <br>Date:2015年9月30日 上午11:52:14
 * @author pujie
 * <result>lock的性能优于其他两个
 */
public class ThreadLockTest {

	private ExecutorService executor;
	
	@Before
	public void setUp() throws Exception {
		executor = Executors.newCachedThreadPool();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testLockSpeed()throws Exception{
		int round = 500000;
		for(int i=18;i>0;i=i-2){
			int threadNum = 10 * i;
			System.out.println("==================================");
			System.out.println("round:"+round+" thread:"+threadNum);
			testStart(threadNum,round);
		}
	}
	public void testStart(int threadNum,int rount) throws Exception{
		new SynchronizedTest("synchronized", threadNum, rount, executor).speedTime();
		        new LockTest("        lock",threadNum, rount, executor).speedTime();
		  new AtomicLongTest("  atomicLong",threadNum, rount, executor).speedTime();
	}
	/**
	 * CyclicBarrier 功能测试
	 * <br>Date:2015年9月30日 上午11:53:48
	 * @author pujie
	 * @throws BrokenBarrierException 
	 * @throws InterruptedException 
	 */
	public void testCyclicBarrier() throws InterruptedException, BrokenBarrierException {
		final CyclicBarrier cb=new CyclicBarrier(31);
		final Random random = new Random();
		for(int i=0;i<30;i++){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep((long)random.nextInt(10000));
						System.out.println("线程"+Thread.currentThread().getName()+"到达集合地点，当前已有"+(cb.getNumberWaiting()+1)+"个已到达."+
						        (cb.getNumberWaiting()==30?"都到齐了，继续走啊":"正在等候"));
						cb.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
		cb.await();
		executor.shutdown();
	}

}
