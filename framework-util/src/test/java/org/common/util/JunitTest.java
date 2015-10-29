package org.common.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {

	public static AtomicInteger c = new AtomicInteger(1);
	@Before
	public void setUp(){
		System.out.println("初始化方法......");
	}
	
	@After
	public void tearDown(){
		System.out.println("资源回收方法......");
	}
	@Test
	public void testMethod(){
		c.incrementAndGet();
	}
}
