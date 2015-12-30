package org.framework.cache.redis.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.framework.cache.redis.operator.HgetAllOperator;
import org.framework.cache.redis.operator.JedisOperator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public abstract class DefaultReadRedisDao{

	protected abstract JedisTemplate getJedisTemplate();
	
	public String get(String key) throws Exception {
		return getJedisTemplate().get(key);
	}
	
	public String hget(String key, String field) throws Exception {
		return getJedisTemplate().hget(key, field);
	}
	
	public Map<String, String> hgetAll(final String... keys) throws Exception {
		if(null == keys)return Collections.emptyMap();
		return getJedisTemplate().hgetAll(new HgetAllOperator(){
			@Override
			public Map<String, String> hgetAll(Jedis client) {
				Map<String,String> values = client.hgetAll(keys[0]);
				for(int i=1;i<keys.length;i++){
					values.putAll(client.hgetAll(keys[i]));
				}
				return values;
			}
		});
	}


	
	public Set<String> keys(String pattern) throws Exception {
		return getJedisTemplate().keys(pattern);
	}

	
	public Set<String> smembers(String key) throws Exception {
		return getJedisTemplate().smembers(key);
	}
	public Long ttl(final String key) throws Exception{
		
		return (Long)getJedisTemplate().jedisOperation(new JedisOperator(){
			
			@Override
			public Object excute(Jedis jedis) {
				return jedis.ttl(key);
			}
		});
	}
	public JedisPool getPool() {
		return getJedisTemplate().getPool();
	}
}
