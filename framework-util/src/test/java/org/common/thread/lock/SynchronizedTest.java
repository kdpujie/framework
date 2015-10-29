package org.common.thread.lock;

import java.util.concurrent.ExecutorService;

public class SynchronizedTest extends TestTemplate {
	
	protected long value;
	public SynchronizedTest(String _name, int _threadNum, int _round,
			ExecutorService _es) {
		super(_name, _threadNum, _round, _es);
		// TODO Auto-generated constructor stub
	}

	@Override
	synchronized void computing() {
		this.value ++;
	}

	@Override
	synchronized  long getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

}
