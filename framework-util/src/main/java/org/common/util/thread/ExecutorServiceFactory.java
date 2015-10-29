package org.common.util.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.common.util.NumberUtil;
import org.common.util.file.ConfigProperties;

/**
 * 
 * @author pujie
 *
 */
public class ExecutorServiceFactory {

	/**
	 * 核心线程数
	 */
	private static String THREAD_CORE_POOL_SIZE = "thread.pool.executor.corePoolSize";
	/**
	 * 最大线程数
	 */
	private static String THREAD_MAX_POOL_SIZE = "thread.pool.executor.maxPoolSize";
	/**
	 * 线程所允许的空闲时间
	 */
	private static String THREAD_KEEP_ALIVE_SECONDS = "thread.pool.executor.keepAliveSeconds";
	/**
	 * 工作队列最大长度
	 */
	private static String THREAD_QUEUE_CAPACITY = "thread.pool.executor.queueCapacity";
	
	private static int corePoolSize = Runtime.getRuntime().availableProcessors();
	private static int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
	private static int keepAliveSeconds = 60;
	private static int queueCapacity = 10000;
	static ThreadPoolExecutor executor;
	/**
	 * 初始化ThreadPoolExecutor
	 * @return
	 * @throws Exception 
	 */
	public static void initialize() throws Exception{
		try{
			corePoolSize = NumberUtil.toInt(ConfigProperties.getProperty(THREAD_CORE_POOL_SIZE), corePoolSize);
			maxPoolSize = NumberUtil.toInt(ConfigProperties.getProperty(THREAD_MAX_POOL_SIZE), maxPoolSize);
			keepAliveSeconds = NumberUtil.toInt(ConfigProperties.getProperty(THREAD_KEEP_ALIVE_SECONDS), keepAliveSeconds);
			queueCapacity = NumberUtil.toInt(ConfigProperties.getProperty(THREAD_QUEUE_CAPACITY), queueCapacity);
			executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueCapacity));
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("工作线程池初始化失败：",e);
		}
	}
	/**
	 * 获得系统线程池对象。
	 * @return executor
	 */
	public static ThreadPoolExecutor getExecutor(){
		return executor;
	}
	/**
	 * 打印线程池配置信息
	 */
	public static void printExecutorInfo(){
		if(null != executor){
			System.out.println("线程池初始化成功。");
			System.out.println("-----------corePoolSize = " + executor.getCorePoolSize());
			System.out.println("-----------maxPoolSize = " + executor.getMaximumPoolSize());
			System.out.println("-----------keepAliveSeconds = " + executor.getKeepAliveTime(TimeUnit.SECONDS));
			System.out.println("-----------queueSize = " + executor.getQueue().size());
			System.out.println("-----------taskCount = " + executor.getTaskCount());
		}else{
			System.out.println("线程池初始化失败！");
		}
	}
}
