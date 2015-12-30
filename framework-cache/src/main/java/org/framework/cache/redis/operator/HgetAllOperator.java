package org.framework.cache.redis.operator;

import java.util.Map;

import redis.clients.jedis.Jedis;
/**
 * redis 的hget操作
 * */
public interface HgetAllOperator {

	public Map<String,String> hgetAll(Jedis client);
}
