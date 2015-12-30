package org.framework.cache.ssdb;

import java.util.List;
import java.util.Map;


public interface SSDBCache
{
	/**
	 * 查询ssdb中的[key-value]键值对<br>
	 * 不存在则返回<a>null<a>
	 * @param key
	 * @return value
	 * @throws Exception 
	 * */
	public String get(String key);

	/**
	 * 遍历ssdb的hash结构，返回key下的所有[field-value]
	 * @param key
	 * @return map<field,value>
	 * @throws Exception 
	 * */
	public Map<String, String> hscanAll(String key);
	
	/**
	 * 遍历ssdb的hash结构，返回key下的指定数量的[field-value]
	 * @param key   索引关键字
	 * @param limit 指定最多返回数[field-value]
	 * @return map<field,value>
	 * @throws Exception 
	 * */	
	public Map<String, String> hscan(String key,int limit);
	
	/**
	 * ssdb的zKeys命令。<br>
	 * @param key 索引关键字
	 * @param zkeyStart 开始的field
	 * @param scoreStart 开始分值
	 * @param scoreEnd 结束分值
	 * @return List<fields> 
	 * */
	public List<String> zkeys(String key,String zkeyStart,Object scoreStart,Object scoreEnd,int limit) ;
	
	/**
	 * ssdb的zKeys命令。按顺序返回记录数，不能超过最大记录数<br>
	 * @param key 索引关键字
	 * @param limit 返回最大记录数
	 * @return List<fields> 
	 * @throws Exception 
	 * */
	public List<String> zkeys(String key,int limit);
	
	/**
	 * ssdb的zscan命令。由于存储为zset结构时，一般要求有顺序，所以返回list结构<br>
	 * 第一个位置为field，第二个位置为value，以此类推
	 * @param key 索引关键字
	 * @param zkeyStart 开始的field
	 * @param scoreStart 开始分值
	 * @param scoreEnd 结束分值
	 * @return List<String>
	 * */
	public List<String> zscan(String key, String zkeyStart, Object scoreStart,Object scoreEnd, int limit);
	
	/**
	 * ssdb的zscan命令。由于存储为zset结构时，一般要求有顺序，所以返回list结构<br>
	 * 第一个位置为field，第二个位置为value，以此类推
	 * @param key 索引关键字
	 * @return List<String>
	 * */
	public List<String> zscanLimit(String key,int limit);
	
	/**
	 * ssdb的zrscan命令。由于存储为zset结构时，一般要求有顺序，所以返回list结构<br>
	 * 第一个位置为field，第二个位置为value，依此类推
	 * @param key 索引关键字
	 * @param zkeyStart 开始的field
	 * @param scoreStart 开始分值
	 * @param scoreEnd 结束分值
	 * @param limit 最大记录数
	 * @return List<String>
	 * */
	public List<String> zrscan(String key, String zkeyStart, Object scoreStart,Object scoreEnd, int limit);
	
	/**
	 * ssdb的zrscan命令。由于存储为zset结构时，一般要求有顺序，所以返回list结构<br>
	 * 第一个位置为field，第二个位置为value，依此类推
	 * @param key 索引关键字
	 * @param limit 最大记录数
	 * @return List<String>
	 * */
	public List<String> zrscanLimit(String key,int limit);

	/**
	 * 设置一个key-val对
	 * @param key
	 * @param val
	 */
	void set(String key, Object val);

	/**
	 * 设置一个key-hkey-hval对
	 * @param key
	 * @param hkey
	 * @param hval
	 */
	void hset(String key, Object hkey, Object hval);
}
