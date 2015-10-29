package org.framework.cache.config;

import org.apache.log4j.Logger;


import org.common.util.NumberUtil;
import org.common.util.StringUtil;
import org.common.util.file.ConfigProperties;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 读取系统配置文件，生成相应的配置对象
 * @author pj
 *
 */
public class RedisConfigFactory {

	public static final Logger log = Logger.getLogger(RedisConfigFactory.class);
	/**
	 * 最大连接数
	 */
	public static String MAX_TOTAL = ".maxTotal";
	/**
	 * 最大空闲连接数
	 */
	public static String  MAX_IDLE = ".maxIdle";
	/**
	 * 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
	 */
	public static String MAX_WAIT_MILLIS = ".maxWaitMillis";
	
	/**
	 * 生成reidisPoolConfig
	 * @return
	 */
	public static JedisPoolConfig createConfig(String prefix){
		JedisPoolConfig config = null;
		config = new JedisPoolConfig();
		String value = ConfigProperties.getProperty(prefix+MAX_TOTAL);
		if(StringUtil.isNotBlank(value)){
			config.setMaxTotal(NumberUtil.toInt(value));
		}
		value = ConfigProperties.getProperty(prefix+MAX_IDLE);
		if(StringUtil.isNotBlank(value)){
			config.setMaxIdle(NumberUtil.toInt(value));
		}
		value = ConfigProperties.getProperty(prefix+MAX_WAIT_MILLIS);
		if(StringUtil.isNotBlank(value)){
			config.setMaxWaitMillis(NumberUtil.toInt(value));
		}
		return config;
	}
}
