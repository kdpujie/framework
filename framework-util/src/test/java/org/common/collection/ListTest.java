package org.common.collection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListTest {
	
	private List<Object> arrayList;
	private List<Object> linkedList;
	private Object obj1 = new Object();
	private Object obj2 = new Object();
	public static int LIST_SIZE = 5000000;

	@Before
	public void setUp() throws Exception {
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<Object>();
		long start = System.nanoTime();
		for(int i=0;i<LIST_SIZE;i++){
			arrayList.add(obj1);
		}
		long end = System.nanoTime();
		System.out.println("arrayList初始化耗时："+(end -start));
	    start = System.nanoTime();
		for(int i=0;i<LIST_SIZE;i++){
			linkedList.add(obj2);
		}
		end = System.nanoTime();
		System.out.println("linkedList初始化耗时："+(end -start));
	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 * 对比ArrayList和LinkedList add()操作耗时<br>
	 * 单个add插入操作，LinkedList优于ArrayList。
	 */
	@Test
	public void testAdd(){
		long start = System.nanoTime();
		for(int i=0;i<1;i++){
			arrayList.add(obj1);
		}
		long end = System.nanoTime();
		System.out.println("arrayList add()耗时："+(end -start));
	    start = System.nanoTime();
		for(int i=0;i<1;i++){
			linkedList.add(obj2);
		}
		end = System.nanoTime();
		System.out.println("linkedList add()耗时："+(end -start));
	}
	/**
	 * 对比ArrayList和LinkedList remove操作耗时<br>
	 * 小容量(测试5w容量)的遍历还是LinkedList优秀一些。随着容量增大，耗时超过ArrayList。
	 */
	@Test
	public void testRemove(){
		long start = System.nanoTime();
		arrayList.remove(0);
		long end = System.nanoTime();
		System.out.println("arrayList remove()耗时："+(end -start));
	    start = System.nanoTime();
		linkedList.remove(2500);
		end = System.nanoTime();
		System.out.println("linkedList remove()耗时："+(end -start));
	}
	/**
	 * 对比ArrayList和LinkedList耗时<br>
	 * 小容量(5w容量)的遍历还是LinkedList优秀一些。再大些LinkedList遍历时间不稳定。
	 */
	@Test
	public void testErgodic(){
		long start = System.nanoTime();
		Iterator<Object> it = arrayList.iterator();
		while(it.hasNext()){
			it.next();
		}
		long end = System.nanoTime();
		System.out.println("arrayList  遍历耗时："+(end -start));
	    start = System.nanoTime();
		it = linkedList.iterator();
		while(it.hasNext()){
			it.next();
		}
		end = System.nanoTime();
		System.out.println("linkedList 遍历耗时："+(end -start));
	}
}
