package org.common.util;

import org.apache.commons.lang.math.NumberUtils;

public class NumberUtil extends NumberUtils{

	/**32位最大的数*/
	public static final int MAX_32_NUMBER = 0b1000_0000_0000_0000_0000_0000_0000_0000;
	
	/**
	 * 把int型的数值转换成以二进制窜的方式表示
	 * @param number
	 * @return 二进制窜 1 <——> 00000000000000000000000000000001
	 * */
	public static String int2BinaryStr(int number){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<32;i++){
			sb.append((number&MAX_32_NUMBER>>>i)>>>(31-i));
		}
		return sb.toString();
	}
	
	/**
	 * 把byte数组里的四个字节转换成32位的int型数据(高位在后)
	 * @param b byte[]数组
	 * @return int
	 * @author pujie
	 * */
	public static int byteArrayToInt(byte[] b) {  
	       return (int) ((((b[3] & 0xff) << 24)  
			               | ((b[2] & 0xff) << 16)  
			               | ((b[1] & 0xff) << 8) 
			               | ((b[0] & 0xff) << 0)));  
	} 
	/**
	 * 把byte数组里的8个字节转换成64位的long型数据(高位在前)
	 * @param b byte[]数组
	 * @return int
	 * @author pujie
	 * */
	public static long byteArrayToLong(byte[] b){
		return (long) ((((b[0] & 0xff) << 56)  
	               | ((b[1] & 0xff) << 48)  
	               | ((b[2] & 0xff) << 40) 
	               | ((b[3] & 0xff) << 32)
	               | ((b[4] & 0xff) << 24)
	               | ((b[5] & 0xff) << 16)
	               | ((b[6] & 0xff) << 8)
	               | (b[7] & 0xff)
				));  
	}
	/**
	 * 把二进制数组转换成 十六进制串
	 * @param bytes 
	 * @return hex string
	 * */
	public static String binary2HexStr(byte[] bytes){
		StringBuilder hexValue = new StringBuilder();
		for (byte b:bytes) {
			int val = ((int) b) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	/**
	 * 返回[0-upperLimit)之间的伪随机数<br>
	 * 注：这种方式比java的Random.netInt(n)性能方面要好些
	 * @param upperLimit 随机数的上限(不包含)
	 * @return value
	 * */
	public static int random(int upperLimit){
		if(upperLimit <= 0){
			return 0;
		}
		return (int)(System.currentTimeMillis()%upperLimit);
	}
	/**
	 * 在有序数组(顺序)times中,start-end范围内，查找num。并返回num所在位置占范围(start-end)的百分比。
	 * <br>Date:2015年9月22日 下午4:23:30
	 * @author pujie
	 * @param times 顺序排列数组
	 * @param start 范围开始数值
	 * @param end	范围结束数值
	 * @param num	被查找的数值
	 */
	public static float indexAndRate(int[] times,int start,int end,int num){
		int i_start=-1,i_end=-1,i_num=-1;
		for(int i=0;i<times.length;i++){
			if(i_start ==-1 && start<=times[i] && times[i]<=end){
				i_start = i;
			}
			if(i_start >-1 && times[i] == num){
				i_num = i;
			}
			if(times[i] > end){
				i_end = i-1;break;
			}else if(i ==times.length -1){
				i_end = i;
			}
		}
		if(i_start == -1 || i_end == -1 || i_num == -1)return -1f;
		return (i_num - i_start +1)/(float)(i_end-i_start+1);
	}
	
	
}
