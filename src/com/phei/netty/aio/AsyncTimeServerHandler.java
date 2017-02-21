/**
 * 
 */
package com.phei.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 *
 */
public class AsyncTimeServerHandler implements Runnable {

	private int port;
	CountDownLatch countDownLatch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;
	
	
	public AsyncTimeServerHandler(int port) {
		this.port = port;
		try {
			this.asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
			System.out.println("this server port:"+port);
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	 this.countDownLatch = new CountDownLatch(1);
	 this.asynchronousServerSocketChannel.accept(this,new AcceptCompletionHandle());
	 try{
		 countDownLatch.await();
	 }catch(Exception e){
		 
	 }
	}

}
