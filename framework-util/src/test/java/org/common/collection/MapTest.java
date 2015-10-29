package org.common.collection;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

	private Map<Integer, String> defaultMap;
	private Map<Integer, String> definedMap;
	public static int MAP_SIZE = 500;
	
	@Before
	public void setUp() throws Exception {
		defaultMap = new HashMap<Integer, String>(MAP_SIZE);
		definedMap = new HashMap<Integer, String>(MAP_SIZE,0.85f);
		for(int i=0;i<MAP_SIZE;i++){
			defaultMap.put(i, i+"");
			definedMap.put(i, i + "");
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		long start = System.nanoTime();
		for(int i=0;i<MAP_SIZE;i++){
			defaultMap.get(i);
		}
		long end = System.nanoTime();
		System.out.println("默认加载因子，获取速度："+(end - start));
		start = System.nanoTime();
		for(int i=0;i<MAP_SIZE;i++){
			definedMap.get(i);
		}
		end = System.nanoTime();
		System.out.println("调整加载因子，获取速度："+(end - start));
	}

}
