package org.framework.cache.redis.impl;

import org.framework.cache.pool.impl.ReadJedisPools;
import org.framework.cache.redis.RedisReadDao;

public class ReadRedisDaoImpl extends DefaultReadRedisDao implements RedisReadDao {

	private JedisTemplate jedisTemplate;
	
	public ReadRedisDaoImpl(ReadJedisPools readPool){
		this.jedisTemplate = JedisTemplate.createPools(readPool);
	}
	
	@Override
	protected JedisTemplate getJedisTemplate() {
		return this.jedisTemplate;
	}

}
