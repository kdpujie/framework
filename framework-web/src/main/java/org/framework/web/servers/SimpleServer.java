package org.framework.web.servers;

import java.util.concurrent.Executors;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.framework.web.handlers.HelloHandler;

/**
 * 简单的内置jetty server
 * <br>Date:2016年2月3日 下午3:09:33
 * @author pujie
 */
public class SimpleServer {
	
	public static int port = 8080;

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		//设置connector
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setHost("192.168.6.227");
		connector.setPort(port);
/*		ServerConnector connector = new ServerConnector(server);
		connector.setHost("127.0.0.1");
		connector.setPort(port);*/
		//定义线程池
		connector.setThreadPool(new QueuedThreadPool(150));
		connector.setAcceptors(5);
		server.addConnector(connector);
//		server.setConnectors(new Connector[]{connector});
		server.setHandler(new HelloHandler());
		server.start();
		server.join();
	}

}
