package com.phei.netty.aio;

public class TimerClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8082;
		 new Thread(new AsyncTimeClientHandler("localhost",port),"AIO-AsyncTimeClientHandler-001").start();
	}

}
