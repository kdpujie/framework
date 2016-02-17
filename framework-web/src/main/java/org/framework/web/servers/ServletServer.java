package org.framework.web.servers;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.framework.web.handlers.TestServlet;

/**
 * 简单的内置jetty servlet server
 * <br>Date:2016年2月3日 下午3:09:33
 * @author pujie
 */
public class ServletServer {
	
	public static int port = 8080;

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS); 
		context.setContextPath("/");
		server.setHandler(context);
		//设置connector
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setHost("192.168.6.227");
		connector.setPort(port);
		//定义线程池
		connector.setThreadPool(new QueuedThreadPool(150));
		connector.setAcceptors(5);
		server.addConnector(connector);

		context.addServlet(new ServletHolder(new TestServlet()), "/hello");
		
		
		server.start();
		server.join();
	}

}
