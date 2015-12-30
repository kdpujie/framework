package org.framework.cache.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis pool管理类 
 * @pj
 * */
public class JedisPoolWriper{

	private  JedisPool jedisPool = null;
	
    public JedisPoolWriper(final JedisPoolConfig poolConfig,final String host,final int port){
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
    /**
	 * 从连接池中获取client<br>
	 * 用完客户端，调用调用{@link releaseJedis}把资源放回池中
	 * */
	public Jedis getJedis(){
		return jedisPool.getResource();
	}
	/**
	 * 把client释放回pool
	 * */
	public void releaseJedis(Jedis jedis){
		if(null != jedis){
			jedisPool.returnResource(jedis);
		}
		jedis = null;
	}
	/**
	 * 把被损坏的Jedis释放回pool
	 * */
	public void releaseBrokenJedis(Jedis jedis) {
		if(null != jedis){
			jedisPool.returnBrokenResource(jedis);
		}
		jedis = null;
	}
	public void destroy(){
		this.jedisPool.destroy();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
