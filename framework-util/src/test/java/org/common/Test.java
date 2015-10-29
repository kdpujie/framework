package org.common;

import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f1();
	}

	public static void f(){
//		new Exception("addd").printStackTrace();
		Map<Thread, StackTraceElement[]> ses = Thread.getAllStackTraces();
		for(Entry<Thread, StackTraceElement[]> entry:ses.entrySet()){
			System.out.println("***************线程名称"+entry.getKey().getName());
			for(StackTraceElement se:entry.getValue()){
				System.out.print(se.getClassName() + ","+se.getMethodName()+",");
			}
			System.out.println();
		}
	}
	public static void f1(){
		f();
	}
}
