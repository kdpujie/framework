package org.common.util;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

/**
 * 继承自  {@link StringUtils}
 * 并且添加了常用的 String 、 String数组的操作
 *
 */
public class StringUtil extends StringUtils{

	/**
	 * 数组中是否包含target 
	 * @param array
	 * @param target
	 * @author houzhaowei
	 * @return 包含返回true，不包含返回false
	 */
	public static boolean contains(String[] array,String target){
		boolean contains = false;
		for(String one : array){
			if(one.equals(target)){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	/**
	 * 数组中是否有某个元素包含 target 
	 * @param array
	 * @param target
	 * @author houzhaowei
	 * @return 数组中某元素包含target则返回true，否则返回false
	 */
	public static boolean containsItemContains(String[] array,String target){
		boolean contains = false;
		for(String one : array){
			if(target.indexOf(one) != -1){
				contains = true;
			}
		}
		return contains;
	}
	/**
	 * 把Collection<?>里的对象转换成string，用指定分隔符分隔
	 * @param Collection
	 * @param separator
	 * @return string
	 * @author pujie
	 */
	public static String list2String(Collection<?> elements,String separator){
		if(null == elements || elements.size()<1){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = elements.iterator();
		while(it.hasNext()){
			sb.append(it.next()).append(separator);
		}
		sb.delete(sb.lastIndexOf(separator),sb.length());
		return sb.toString();
	}
	
	/**
	 * 按指定编码集，把二进制数组转换成字符串
	 * */
	public static String byte2String(byte[] datas,Charset charSet){
		return new String(datas,charSet);
	}

	/**
	 * 从常规url中提取host
	 * @param url  ex:http://baidu.com/bid
	 * @return host ex：baidu.com
	 * @author pujie
	 * */
   public static String getHostUrl(String url){
	   if(StringUtil.isBlank(url)){
		   return "";
	   }
	   char[] array = url.toCharArray();
	   int start=0,end=0;
	   for(int i=1;i<array.length;i++){
		   if(array[i]=='/' && array[i]==array[i-1]){
			   start = i+1;continue;
		   }else if(array[i]=='/' && array[i-1]!=':'){
			   end=i;break;
		   }
		   if(i==array.length-1 && end<start){
			   end = array.length;
		   }
	   }
	   return new String(array,start,end-start);
   }
	public static void main(String[] args) {
        
	}
}
