package com.phei.netty.nio;

public class TimeServer {

	public static void main(String[] args) {
	 
		int port = 8082;
		MultiplexerTimerServer server = new MultiplexerTimerServer(port);
		new Thread(server,"NIO-MultiplexerTimerServer-001").start();
	}

}
