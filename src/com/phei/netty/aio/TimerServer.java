/**
 * 
 */
package com.phei.netty.aio;

/**
 * @author Administrator
 *
 */
public class TimerServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		int port = 8082;
		AsyncTimeServerHandler server = new AsyncTimeServerHandler(port);
		new Thread(server,"NIO-AsyncTimeServerHandler-001").start();
	}

}
