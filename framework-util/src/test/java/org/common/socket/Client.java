package org.common.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import org.common.util.StringUtil;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream input=null;
		try {
			socket = new Socket("localhost",1234);
			input = new DataInputStream(socket.getInputStream());
			
			String word = input.readUTF();
			while(StringUtil.isNotBlank(word)){
				System.out.println(word);
				word = input.readUTF();
			}
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(null!= input) input.close();
				if(null!= socket)socket.close();	
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
	}

}
