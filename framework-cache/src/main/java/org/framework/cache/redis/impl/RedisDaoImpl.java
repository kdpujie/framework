package org.framework.cache.redis.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.framework.cache.pool.impl.WriteJedisPools;
import org.framework.cache.redis.RedisDao;
import org.framework.cache.redis.operator.JedisOperator;
import org.framework.cache.redis.operator.PipelineOperator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;



public class RedisDaoImpl extends DefaultReadRedisDao implements RedisDao {

	private JedisTemplate jedisTemplate;
	
	public RedisDaoImpl(WriteJedisPools writeJedisPool){
		this.jedisTemplate = JedisTemplate.createPools(writeJedisPool);
	}
	
	@Override
	protected JedisTemplate getJedisTemplate() {
		return this.jedisTemplate;
	}
	
	@Override
	public String set(String key ,String value)throws Exception{
		return jedisTemplate.set(key, value);
	}
	@Override
	public String getSet(String key,String value)throws Exception{
		return jedisTemplate.getSet(key, value);
	}
	@Override
	public long incr(String key) throws Exception{
		return jedisTemplate.incr(key);
	}
	@Override
	public String setEX(String key,int seconds,String value) throws Exception{
		return jedisTemplate.setEX(key, seconds, value);
	}
	@Override
	public void hset(final String key, final Map<String, String> fieldInfo) throws Exception {

		jedisTemplate.pipeline(new PipelineOperator() {
			@Override
			public void excute(Pipeline pl) {
				for(Entry<String,String> entry : fieldInfo.entrySet()){
						pl.hset(key,entry.getKey(),entry.getValue());
				}
			}
		});
	}
	
	@Override
	public void hsetAndIncry(final String key,final Map<String, String> fieldInfo,final String... incryField) throws Exception {
		jedisTemplate.pipeline(new PipelineOperator() {
			
			@Override
			public void excute(Pipeline pl) {
				for(Entry<String,String> entry : fieldInfo.entrySet()){
						pl.hset(key,entry.getKey(),entry.getValue());
				}
				if(null != incryField){
					for(String field:incryField){
						pl.hincrBy(key,field, 1);
					}
				}
			}
		});
		
	}
	@Override
	public void hsetAndIncry(final String key,final Map<String, Long> fieldInfo) throws Exception {
		jedisTemplate.pipeline(new PipelineOperator() {
			
			@Override
			public void excute(Pipeline pl) {
				for(Entry<String,Long> entry : fieldInfo.entrySet()){
						pl.hincrBy(key,entry.getKey(),entry.getValue());
				}
			}
		});
		
	}
	@Override
	public void hsetAndIncry(final Map<String,Map<String,Long>> keyMap) throws Exception {
		jedisTemplate.pipeline(new PipelineOperator() {
			
			@Override
			public void excute(Pipeline pl) {
				for(Entry<String,Map<String,Long>> entry : keyMap.entrySet()){
					String key = entry.getKey();
					Map<String,Long> fieldInfo = entry.getValue();
					for(Entry<String,Long> field : fieldInfo.entrySet()){
						pl.hincrBy(key,field.getKey(),field.getValue());
					}
				}
			}
		});
		
	}
	@Override
	public long del(String... keys) throws Exception {
		return jedisTemplate.del(keys);
	}

	@Override
	public long hincrBy(String key, long value, String... fields) throws Exception {
		return jedisTemplate.hincrBy(key, value, fields);
	}

	@Override
	public void saddEX(final String key, final int seconds, final String... values) throws Exception {
		
		if(values.length < 1) return;
		jedisTemplate.jedisOperation(new JedisOperator()  {
			
			@Override
			public Object excute(Jedis jedis) {
				jedis.sadd(key, values);
				if(seconds >0){
					jedis.expire(key, seconds);		
				}
				return null;
			}});
	}
	
	@Override
	public void sadd(final Map<String,String> setMap) throws Exception {
		jedisTemplate.pipeline(new PipelineOperator() {
			@Override
			public void excute(Pipeline pl) {
				for(Entry<String,String> entry : setMap.entrySet()){
					pl.sadd(entry.getKey(),entry.getValue());
				}
			}
		});
		
	}

	@Override
	public Long scard(final String key) throws Exception {
		return jedisTemplate.scard(key);
		
	}
	
	@Override
	public boolean exists(final String key) throws Exception {
		return jedisTemplate.exists(key);
	}

	@Override
	public void rename(String oldKey,String newKey)throws Exception {
		jedisTemplate.rename(oldKey,newKey);
	}
	@Override
	public void expire(String key,int seconds)throws Exception {
		jedisTemplate.expire(key,seconds);
	}
}
