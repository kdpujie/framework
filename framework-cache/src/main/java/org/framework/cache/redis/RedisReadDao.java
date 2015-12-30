package org.framework.cache.redis;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPool;

/**
 * redis只读操作
 * @author pj
 */
public interface RedisReadDao {
	
	public abstract JedisPool getPool();
	/**
	 * [key-value] 查询
	 * @param key
	 * @return	value
	 * @throws Exception
	 */
	public abstract String get(String key) throws Exception;
	/**
	 * 返回所有匹配的key值。
	 * @param pattern
	 * @return  keys
	 * @throws Exception 
	 * */
	public abstract Set<String> keys(String pattern) throws Exception;
	
	/**
	 * 根据[key]获取[hash-map]中的[value]
	 * @param key
	 * @return  value
	 * @throws Exception 
	 * */
	public abstract String hget(String key,String field) throws Exception;
	
	/**
	 * 查出指定keys下的所有域，及其对应的值。<br>
	 * 以Map<field,value>的形式返回，<br>
	 * @param keys 待查key集合
	 * @return map<String,String>
	 * @throws Exception 
	 * */
	public abstract Map<String, String> hgetAll(final String... keys) throws Exception;
	
	/**
	 * 返回集合key中的所有成员。
	 * @param key
	 * @return [all-value]
	 * @throws Exception 
	 * **/
	public abstract Set<String> smembers(String key) throws Exception;
	
	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。<br>
	 * 当 key 不存在时，返回 -2 <br>
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。<br>
     * 否则，以秒为单位，返回 key 的剩余生存时间。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long ttl( String key) throws Exception;
}