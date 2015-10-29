package org.framework.cache.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.common.util.NumberUtil;
import org.common.util.file.ConfigProperties;
import org.framework.cache.config.RedisConfigFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;


public class JedisClusterFactory {

	public static final Logger log = Logger.getLogger(JedisClusterFactory.class);
//	public static final String DEFAULT_CONFIG_NAME = "redis-cluster.properties";
	public static String REDIS_CLUSTER_PREFIX = "redis.cluster";
	public static String ADDR = ".address";
	public static String TIME_OUT = ".timeout";
	private static Map<String,JedisCluster> clusters = new HashMap<String,JedisCluster>();
	
	static {
		
		init();
	}
	/**
	 * 加载系统配置，初始化jedis cluster
	 * @param configName
	 */
	public static void  init(){
		for(Entry<Object, Object> line: ConfigProperties.entrySet()){
			if(line.getKey().toString().startsWith(REDIS_CLUSTER_PREFIX) //找出集群名称
					&& line.getKey().toString().trim().endsWith(line.getValue().toString().trim())){
				clusters.put(line.getValue().toString(),createCluster(line.getKey().toString().trim()));
			}
		}
	}
	/**
	 * 连接redis集群
	 * @param clusterPath
	 * @return
	 */
	public static JedisCluster createCluster(String clusterPath){
		JedisPoolConfig jedisPoolConfig = RedisConfigFactory.createConfig(clusterPath);//加载配置文件
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		String[] host;
		String addrs = ConfigProperties.getProperty(clusterPath+ADDR);
		String[] hostAndPorts = addrs.split(",");
		for(String hostAndPort:hostAndPorts){
			host = hostAndPort.split(":");
			if(host.length != 2) continue;
			jedisClusterNode.add(new HostAndPort(host[0], NumberUtil.toInt(host[1])));
		}
		if(jedisClusterNode.size() <1 ){
			throw new NullPointerException("jedisCluster 地址为空或格式不正确");
		}
		int timeout = NumberUtil.toInt(ConfigProperties.getProperty(clusterPath + TIME_OUT), 2000);
		return new JedisCluster(jedisClusterNode, timeout,jedisPoolConfig );
	}
	/**
	 * 获得系统持有的jedis cluster 实例
	 * @return
	 */
	public static JedisCluster getcluster(String name){
		return clusters.get(name);
	}
	public static void main(String[] args) {
		/*Pattern p = Pattern.compile("redis.cluster.[a-z]+=[a-z]+");
		Matcher m = p.matcher("redis.cluster.dm=dmp");
		Matcher m1 = p.matcher("redis.cluster.dmp.address=10.71.48.144:30000,10.71.48.145:30001");
		System.out.println(m.find());
		System.out.println(m1.find());*/
		JedisClusterFactory.init();
	}
}
