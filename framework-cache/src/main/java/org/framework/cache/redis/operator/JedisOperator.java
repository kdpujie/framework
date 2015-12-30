package org.framework.cache.redis.operator;

import redis.clients.jedis.Jedis;
/**
 * redis 的jedis操作
 * Date: 2014年9月3日 <br>
 * @author pj
 * */
public interface JedisOperator {

	public Object excute(Jedis jedis);
}
