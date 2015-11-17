package org.common.string;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.common.util.DateUtil;
import org.common.util.NumberUtil;
import org.common.util.StringUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {
	private List<String> list;
	public static Random random = new Random();

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void test(){
		for(int i=0;i<100;i++){
			for(int j=0;j<10;j++){
				if(j==1){
					break;
				}
			}
		}
	}
	public static char randomWord(){
		int[] words = new int[26];
		for(int i=65;i<=90;i++){
			words[i-65] = i;
		}
		int a = words[random.nextInt(26)];
		return (char)a;
	}
//	@Test
	public void testIndexAndRate() {
		int[] times;
		String str = "0,1,2,3,24,25,26,47,49,50,51,52,53,54,70,71,72,73,85,86";
		String[] timesIntevals = str.split(",");
		times = new int[timesIntevals.length];
		for(int i=0;i<timesIntevals.length;i++){
			times[i] = NumberUtil.toInt(timesIntevals[i]);
		}
		System.out.println("已过时段占总时段百分比："+NumberUtil.indexAndRate(times,72,96,72));
	}
	
	public void f(char[] cs){
		cs[0] = 'c';
	}
	

}
