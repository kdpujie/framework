package org.common.log;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		Logger log = Logger.getLogger("hello");
//		log.info("哈哈，复活了");
		Set<String> str = new HashSet<String>();
		str.add("a");
		str.add("b");
		System.out.println(str.toString());
	}

}
