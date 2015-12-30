package org.framework.cache.pool.impl;

import java.util.Collections;
import java.util.List;



import org.framework.cache.pool.JedisPools;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis pool管理类 
 * @pj
 * */
public class WriteJedisPools implements JedisPools{

	private  JedisPool jedisPool = null;
	
    public WriteJedisPools(final JedisPoolConfig poolConfig,final String host,final int port){
    	try{
			jedisPool = new JedisPool(poolConfig,host,port);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    /**
	 * 获得整个jedisPool
	 * */
    public JedisPool getJedisPool(){
    	return this.jedisPool;
    }
	@Override
	public List<JedisPool> getNodes() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}




}
