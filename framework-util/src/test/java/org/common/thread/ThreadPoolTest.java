package org.common.thread;

import java.util.concurrent.ExecutorService;

import org.common.thread.model.HashSetContains;
import org.common.thread.model.SimpleThread;
import org.common.thread.model.StrContains;
import org.common.thread.model.ThreadLocalDemo;
import org.common.util.thread.ExecutorServiceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThreadPoolTest {

	private ExecutorService executor;
	@Before
	public void setUp() throws Exception {
//		executor = Executors.newSingleThreadScheduledExecutor();
//		executor = new ThreadPoolExecutor(2, 32, 500,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
		ExecutorServiceFactory.initialize();
		executor = ExecutorServiceFactory.getExecutor();
//		ExecutorServiceFactory.printExecutorInfo();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleThread() {
/*		for(int i=0;i<50;i++){
			System.out.println("提交任务："+i);
			executor.execute(new SimpleThread(i+""));
		}
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}
	@Test
	public void  testSpeed(){
		String element = "abc";
		int size = 25;
		int times = 10000000;
		executor.execute(new HashSetContains(element, size, times));
		executor.execute(new StrContains(element, size, times));
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("任务执行完毕，线程池退出");
	}
	@Test
	public void testThreadLocal(){
	/*	executor.execute(new ThreadLocalDemo());
		executor.execute(new ThreadLocalDemo());
		executor.execute(new ThreadLocalDemo());
		executor.execute(new ThreadLocalDemo());
		executor.execute(new ThreadLocalDemo());
		executor.execute(new ThreadLocalDemo());
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("任务执行完毕，线程池退出");*/
	}

}
