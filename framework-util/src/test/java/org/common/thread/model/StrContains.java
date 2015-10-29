package org.common.thread.model;

import org.common.util.NumberUtil;

/**
 * 测试string的contans方法的速度
 * <br>Date:2015年8月28日 下午3:09:11
 * @author pujie
 */
public class StrContains implements Runnable {

	private String str;
	private String element;
	private int size;
	private int times;
	
	public StrContains(String element,int size,int times){
		this.size = size;
		this.element = element;
		this.times = times;
		for(int i=0;i<size;i++){
			str +=element+i+",";
		}
	}
	@Override
	public void run() {
		
		try {
			long start = System.currentTimeMillis();
			String e = element + 100;
			for(int i=0;i<times;i++){
//				e = element+NumberUtil.random(size);
				str.contains(e);
			}
			long end = System.currentTimeMillis();
			System.out.println("最后一个元素"+e);
			System.out.println("字符串的contains方法耗时："+(end - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
