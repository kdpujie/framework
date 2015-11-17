package org.common.util.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * socketserver，每隔100ms像客户端 随机发送字母。
 * <br>Date:2015年10月29日 上午11:41:57
 * @author pujie
 */
public class Server {
	
	public static int port = 1234;
	public static int[] words = new int[26];
	public static Random random = new Random();
	private static ExecutorService executor;
	
	public static void main(String[] args) {
		try {
			executor = Executors.newFixedThreadPool(2);
			ServerSocket server = new ServerSocket(port);
			while(true){
				final Socket socket = server.accept();
				executor.execute(new Runnable() {
					@Override
					public void run() {
						DataOutputStream out = null;
						try {
							out = new DataOutputStream(socket.getOutputStream());
							while(true){
								out.writeUTF(randomWord()+"");
								TimeUnit.MILLISECONDS.sleep(100);
								out.flush();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							try {
								out.close();
								socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static char randomWord(){
		for(int i=65;i<=90;i++){
			words[i-65] = i;
		}
		int a = words[random.nextInt(26)];
		return (char)a;
	}
}


