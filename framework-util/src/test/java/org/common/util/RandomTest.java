package org.common.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * 随机方法测试
 * <br>Date:2015年9月2日 下午2:04:36
 * @author pujie
 */
public class RandomTest {

	private Map<Integer, Integer> map ;
	private Map<Integer, Integer> s;
	private Random rd = new Random();
	
	@Before
	public void setUp(){
		map = new HashMap<Integer, Integer>();
		s = new HashMap<Integer, Integer>();
//		map.put(101, 20);
//		map.put(102, 8);
//		map.put(104, 1);
//		map.put(103, 2);
		map.put(105, 1);
	}
	
	@Test
	public void testRandomSpeed(){
		Entry<Integer, Integer> e = null;
		long start = System.currentTimeMillis();
		for(int i=0;i<100000;i++){
			e = unequalRandom(map);
			count(e.getKey());
		}
		long end = start = System.currentTimeMillis();
		System.out.println("耗时："+(end - start));
		for(Entry<Integer, Integer> entry:s.entrySet()){
			System.out.println("groupId = "+entry.getKey() +", 次数 = " +entry.getValue());
		}
		
	}
	public int count(Integer key){
		int count=1;
		if(s.containsKey(key)){
			count = s.get(key) + 1;
		}
		s.put(key, count);
		return count;
	}
	public Entry<Integer, Integer> unequalRandom(Map<Integer, Integer> map){
		int sum = 0;
		for(Integer i:map.values()){
			sum += i;
		}
		int random = Math.abs(rd.nextInt()%sum);
		int index =0;
		Entry<Integer, Integer> e = null;
		for(Entry<Integer, Integer> entry:map.entrySet()){
			index += entry.getValue();
			if(random < index){
				e = entry;break;
			}
		}
		return e;
	}
}
