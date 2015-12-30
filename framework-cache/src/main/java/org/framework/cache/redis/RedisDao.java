package org.framework.cache.redis;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPool;

/**
 * 正常的redis dao，支持读写操作
 * @author pj
 */
public interface RedisDao {
	
	public abstract JedisPool getPool();
	/**
	 * 根据key删除记录
	 * @param keys
	 * */
	public abstract long del(String... keys) throws Exception;
	
	/**
	 * 批量更新指定key中的域值<br>
	 * @param key 被指定的key
	 * @param fieldInfo 被指定的域和值
	 * @param incryKey 需要自增的域
	 * @throws Exception 
	 * */
	public abstract void hset(final String key,final Map<String, String> fieldInfo) throws Exception;
	
	/**
	 * [hash]批量更新指定key中的field，并对指定field进行自增操作<br>
	 * @param key 被指定的key
	 * @param fieldInfo 更新的fields
	 * @param incryKey 需要自增的field
	 * @throws Exception 
	 * */
	public abstract void hsetAndIncry(final String key,final Map<String,String> fieldInfo,final String... incryField) throws Exception;
	
	/**
	 * 在指定的hash-key的值域增加固定数值[value]
	 * @param key
	 * @param value 增加值
	 * @param fields 被指定值域
	 * @throws Exception 
	 * */
	public abstract long hincrBy(String key,long value, String ...fields ) throws Exception;
	
	/**
	 * 将一个或多个元素添加进[set]，并给key设置过期时间.
	 * 若果seconds<0,表示没有过期时间
	 * @param key
	 * @param seconds 
	 * @param values
	 * @throws Exception 
	 * */
	public abstract void saddEX(final String key,final int seconds,final String...values) throws Exception;
	
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
	 * 查出指定hkeys下的所有域，及其对应的值。<br>
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
	 * 设置多个field
	 * @param key
	 * @param fieldInfo
	 * @throws Exception
	 */
	public void hsetAndIncry(String key, Map<String, Long> fieldInfo) throws Exception;
	/**
	 * 设置多个key 多个field
	 * @param keyMap
	 * @throws Exception
	 */
	public void hsetAndIncry(Map<String, Map<String, Long>> keyMap) throws Exception;
	/**
	 * 设置多个set
	 * @param setMap
	 * @throws Exception
	 */
	public void sadd(Map<String, String> setMap) throws Exception;
	/**
	 * 获取set 大小
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long scard(String key) throws Exception;
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 * @throws Exception
	 */
	boolean exists(String key) throws Exception;
	/**
	 * 重命名
	 * @param oldKey
	 * @param newKey
	 * @throws Exception
	 */
	void rename(String oldKey, String newKey) throws Exception;
	/**
	 * 设置过期时间
	 * @param key
	 * @param seconds
	 * @throws Exception
	 */
	void expire(String key, int seconds) throws Exception;
	/**
	 *  将 key 中储存的数字值增一。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	long incr(String key) throws Exception;
	/**
	 * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
	 * 关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @throws Exception
	 */
	String setEX(String key, int seconds, String value) throws Exception;
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
	/**
	 * 设置key value
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String set(String key, String value) throws Exception;
	/**
	 * getSet
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getSet(String key, String value) throws Exception;
}