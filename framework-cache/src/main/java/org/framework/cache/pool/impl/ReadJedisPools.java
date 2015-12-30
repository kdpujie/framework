package org.framework.cache.pool.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;




import org.common.util.NumberUtil;
import org.framework.cache.pool.JedisPools;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
/**
 * redis pool管理类 <br>
 * 用于只读多个slave节点的情况，每个slave存储的内容相同
 * @pj
 * */
public class ReadJedisPools implements JedisPools{

	private List<JedisPool> nodes;
	private List<JedisShardInfo> nodesInfos;
	private JedisPoolConfig poolConfig;
	private int nodesSize;
	
	public ReadJedisPools(JedisPoolConfig poolConfig,List<JedisShardInfo> nodes){
		this.poolConfig = poolConfig;
		this.nodesInfos = nodes;
		this.nodesSize = this.nodesInfos.size();
	}
	@PostConstruct
	public void init(){
		nodes = new ArrayList<JedisPool>(this.nodesInfos.size());
		for(JedisShardInfo node:this.nodesInfos){
			nodes.add(new JedisPool(poolConfig,node.getHost(),node.getPort(),node.getConnectionTimeout()));
		}
		System.out.println("只读节点初始化成功，共"+nodesSize+"节点");
	}
	
	public int hash(){
		return  NumberUtil.random(this.nodesSize);
	}
    /* (non-Javadoc)
	 * @see com.vaolan.common.cache.config.JedisPools#getJedisPool()
	 */
    @Override
	public JedisPool getJedisPool(){
    	return this.nodes.get(hash());
    }

	@Override
	public List<JedisPool> getNodes() {
		// TODO Auto-generated method stub
		return this.nodes;
	}
    
}
