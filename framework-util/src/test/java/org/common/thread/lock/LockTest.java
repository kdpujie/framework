package org.common.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest extends TestTemplate {
	
	ReentrantLock lock=new ReentrantLock(); 
	protected long value;
	public LockTest(String _name, int _threadNum, int _round,ExecutorService _es) {
		super(_name, _threadNum, _round, _es);
	}

	@Override
	void computing() {
		try {
			lock.lock();
			this.value ++;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}

	@Override
	long getValue() {
		long tmp = 0;
		try {
			lock.lock();
			tmp = this.value;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return tmp;
	}

}
