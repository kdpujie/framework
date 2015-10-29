package org.common.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest extends TestTemplate {
	
	protected AtomicLong value=new AtomicLong(0);
	
	public AtomicLongTest(String _name, int _threadNum, int _round,
			ExecutorService _es) {
		super(_name, _threadNum, _round, _es);
		// TODO Auto-generated constructor stub
	}

	@Override
	void computing() {
		value.incrementAndGet();
	}

	@Override
	long getValue() {
		
		return value.get();
	}

}
