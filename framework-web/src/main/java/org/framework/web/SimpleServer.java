package org.framework.web;

import java.util.concurrent.Executors;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.framework.web.handlers.HelloHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.thread.ThreadPool;

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
//		SelectChannelConnector connector = new SelectChannelConnector();
		ServerConnector connector = new ServerConnector(server);
		connector.setHost("127.0.0.1");
		connector.setPort(port);
		//定义线程池
		
		
//		server.setConnectors(connectors);
		server.setHandler(new HelloHandler());
		server.start();
		server.join();
	}

}
