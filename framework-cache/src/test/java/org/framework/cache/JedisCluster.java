package org.framework.cache;

import org.framework.cache.redis.JedisClusterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JedisCluster {

	private redis.clients.jedis.JedisCluster jc;
	
	@Before
	public void setUp() throws Exception {
		JedisClusterFactory.init();
		jc = JedisClusterFactory.getcluster("dsp");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		jc.get("");
	}

}
