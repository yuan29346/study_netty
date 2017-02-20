/**
 * 
 */
package com.phei.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 *
 */
public class TimeServer {
	
	public static void main(String[] args) {
		int port = 8082;
		ServerSocket server = null;
		try{
			server = new ServerSocket(port);
			Socket socket = null;
			TimerServerHandlerExecutePool pool = new TimerServerHandlerExecutePool(50, 10000);
		
			while(true){
				socket = server.accept();
				 System.out.println("port="+port);
				pool.execute(new TimerServerHandler(socket));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != server)
				try {
					server.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			server = null;
		}
	}

}
