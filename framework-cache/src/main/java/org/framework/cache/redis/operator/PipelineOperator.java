package org.framework.cache.redis.operator;

import redis.clients.jedis.Pipeline;
/**
 * redis 的pipeline操作
 * @author pj
 * */
public interface PipelineOperator {

	public void excute(Pipeline pl);
}
