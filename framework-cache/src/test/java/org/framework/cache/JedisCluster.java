package org.framework.cache;

import java.util.Map;
import java.util.Map.Entry;

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
//		jc.del("t_c_201511232");
		Map<String, String> budget = jc.hgetAll("t_c_201511232");
		for(Entry<String, String> entry:budget.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		Map<String, String> blance = jc.hgetAll("t_cust_1");
		for(Entry<String, String> entry:blance.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}

}
