package org.common.util;


import com.alibaba.fastjson.JSON;


/**
 * JSON与实体bean之间转换
 * 
 * @author liming
 * @version 2013-07-22
 * 
 */
public class JsonUtil {

	/**
	 * bean 2 JSON
	 * 
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object obj)  {
		
		String json =JSON.toJSONString(obj);
		return json;
	}

	/**
	 * JSON 2 BEAN
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
    public static <T> T jsonToBean(String json, Class<T> cls){
		T vo  = JSON.parseObject(json, cls);
		return vo;
	}
    
	public static void main(String[] args) throws Exception {
		
		
//		// 准备数据
//		Lecard lecard = new Lecard() ;
//		lecard.setBatch("batch") ;
//		lecard.setCardNumber("123456") ;
//		// 实体转JSON字符串
//		String json = beanToJson(lecard);
//		System.out.println("bean 2 json:" + json);
//
//		Object limit2 = jsonToBean(json, Lecard.class);
//		System.out.println(limit2.toString());
	}
}
