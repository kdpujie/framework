package org.framework.cache.pool;

import java.util.List;

import redis.clients.jedis.JedisPool;

public interface JedisPools {

	/**
	 * 获取一个jedisPool
	 * */
	public abstract JedisPool getJedisPool();

	public abstract List<JedisPool> getNodes();
}