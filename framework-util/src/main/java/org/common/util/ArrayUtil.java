package org.common.util;

import java.lang.reflect.Array;

import org.apache.commons.lang.ArrayUtils;

public class ArrayUtil extends ArrayUtils{

	/**
	 * 打印数组
	 * @param object 源字符串
	 */
    public static void printArray(Object o){
    	if(o.getClass().isArray()){
    		int len = Array.getLength(o);
    		for(int i=0;i<len;i++){
    			printArray(Array.get(o,i));
    		}
    		System.out.println();
    	}else {
    		System.out.print(o+",");
    	}
    }
}
