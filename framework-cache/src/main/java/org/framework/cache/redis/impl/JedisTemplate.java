package org.framework.cache.redis.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;






import org.framework.cache.pool.JedisPools;
import org.framework.cache.redis.operator.HgetAllOperator;
import org.framework.cache.redis.operator.JedisOperator;
import org.framework.cache.redis.operator.PipelineOperator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * redis operation template
 * @author pj
 * */
public class JedisTemplate {
	
	private JedisPools pools;
	
	public JedisTemplate(JedisPools pools){
		this.pools = pools;
	}
	
	public static JedisTemplate createPools(JedisPools pools){
		return new JedisTemplate(pools);
	}
	public JedisPool getPool(){
		return this.pools.getJedisPool();
	}
	public String getSet(String key,String value){
		Jedis resource = null;
		String status ="";
		try {
			resource = pools.getJedisPool().getResource();
			status = resource.getSet(key,value);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		}finally{
			if(null != resource) {
				resource.close();
			}
		}
		return status;
	}
	public String set(String key ,String value){
		Jedis resource = null;
		String status ="";
		try {
			resource = pools.getJedisPool().getResource();
			status = resource.set(key,value);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		}finally{
			if(null != resource) {
				resource.close();
			}
		}
		return status;
	}
	/**
	 * 删除指定的keys
	 * @param keys
	 * @return  被删除的keys个数
	 * @throws Exception 
	 * */
	public long del(String ...keys){
		Jedis resource = null;
		long number = 0;
		try {
			resource = pools.getJedisPool().getResource();
			number = resource.del(keys);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return number;
	}
	
	/**
	 * 返回所有匹配的key值。
	 * @param pattern
	 * @return  keys
	 * @throws Exception 
	 * */
	public Set<String> keys(String pattern){
		Jedis resource = null;
		Set<String> value= Collections.emptySet();
		try {
			resource = pools.getJedisPool().getResource();
			value = resource.keys(pattern);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return value;
	}
	/**
	 * 根据[key]获取[key-value]中的[value]
	 * @param key
	 * @return  value
	 * @throws Exception 
	 * */
	public String get(String key){
		Jedis resource = null;
		String value = "";
		try {
			resource = pools.getJedisPool().getResource();
			value = resource.get(key);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return value;
	}
	/**
	 * 将 key 中储存的数字值增一。
	 * @param key
	 * @return value
	 * @throws Exception 
	 * */
	public long incr(String key){
		Jedis resource = null;
		long value = 0L;
		try {
			resource = pools.getJedisPool().getResource();
			value = resource.incr(key);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return value;
	}
	/**
	 * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
	 * 关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
	 * @param key
	 * @param  seconds
	 * @param value
	 * @return 状态
	 * @throws Exception 
	 * */
	public String setEX(String key,int seconds,String value){
		Jedis resource = null;
		String status = "";
		try {
			resource = pools.getJedisPool().getResource();
			status = resource.setex(key, seconds, value);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return status;
	}
	/**
	 * 根据[key]获取[hash-map]中的[value]
	 * @param key
	 * @return  value
	 * @throws Exception 
	 * */
	public String hget(String key,String field){
		Jedis resource = null;
		String value = "";
		try {
			resource = pools.getJedisPool().getResource();
			value = resource.hget(key, field);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return value;
	}	
	
	/**
	 * 在指定的hash-key的值域增加固定数值[value]
	 * @param key
	 * @param value 增加值
	 * @param fields 被指定值域
	 * @throws Exception 
	 * */
	public long hincrBy(String key,long value,String ...fields){
		Jedis resource = null;
		long val = 0;
		try {
			resource = pools.getJedisPool().getResource();
			for(String field:fields){
				val = resource.hincrBy(key, field, value);
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return val;
	}
	/**
	 * hash结构的hgetall操作<br>
	 * 如果异常，返回null
	 * @throws Exception 
	 */
	public Map<String, String> hgetAll(HgetAllOperator hgetter){
		Jedis resource = null;
		Map<String, String> values = Collections.emptyMap();
		try {
			resource = pools.getJedisPool().getResource();
			values = hgetter.hgetAll(resource);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return values;
	}
	
	/**
	 * 返回[set]集合中指定key下的所有元素
	 * @param key
	 * @return values
	 * @throws Exception 
	 * */
	public Set<String> smembers(String key) {
		Jedis resource = null;
		Set<String> val = Collections.emptySet();
		try {
			resource = pools.getJedisPool().getResource();
			val = resource.smembers(key);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return val;
	}
	/**
	 * 获取客户端，不支持返回数据。
	 * @throws Exception 
	 * */
	public Object jedisOperation(JedisOperator jedisOperation) {
		Jedis resource = null;
		Object t;
		try {
			resource = pools.getJedisPool().getResource();
			t = jedisOperation.excute(resource);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return t;
	}
	/**
	 * pipeline处理<br>
	 * @throws Exception 
	 */
	public void pipeline(PipelineOperator pipeline){
		Jedis resource = null;
		try {
			resource = pools.getJedisPool().getResource();
			Pipeline pl = resource.pipelined();
			pipeline.excute(pl);
			pl.sync();
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
	}
	
	public long scard(String key){
		Jedis resource = null;
		long value;
		try {
			resource = pools.getJedisPool().getResource();
			value = resource.scard(key);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return value;
	}
	
	public boolean exists(String key){
		Jedis resource = null;
		boolean value;
		try {
			resource = pools.getJedisPool().getResource();
			value = resource.exists(key);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
		return value;
	}
	
	public void expire(String key,int seconds){
		Jedis resource = null;
		try {
			resource = pools.getJedisPool().getResource();
			resource.expire(key,seconds);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
	}

	public void rename(String oldkey,String newkey){
		Jedis resource = null;
		try {
			resource = pools.getJedisPool().getResource();
			resource.rename(oldkey, newkey);
		} catch (NullPointerException e) {
			throw new NullPointerException("redis pools 不能为null");
		} finally {
			if(null != resource) {
				resource.close();
			}
		}
	}
}
