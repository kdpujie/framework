package org.common.thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

abstract public class TestTemplate {

	private String name;
	final private CyclicBarrier cb;
	private ExecutorService es ;
	protected int threadNum;
	final protected int round;
	
	
	abstract void computing();
	abstract long getValue();
	
	public TestTemplate(String _name,int _threadNum,int _round,ExecutorService _es){
		this.name = _name;
		this.threadNum = _threadNum;
		this.round = _round;
		this.es = _es;
		this.cb = new CyclicBarrier(this.threadNum + 1);
	}
	public void speedTime() throws InterruptedException, BrokenBarrierException{
		long start = System.nanoTime();
		for(int i=0;i<this.threadNum;i++){
			es.execute(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<round;i++){
						getValue();
						computing();
					}
					try {
						cb.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
		cb.await();
		long end = System.nanoTime();
		System.out.println(name+" 耗时："+(end - start) +" value = "+getValue());
	}
}
