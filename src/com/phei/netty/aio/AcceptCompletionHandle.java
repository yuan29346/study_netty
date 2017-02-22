/**
 * 
 */
package com.phei.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Administrator
 *
 */
public class AcceptCompletionHandle implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {

	@Override
	public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		 result.read(buffer);
		
	}

	@Override
	public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
		 exc.printStackTrace();
		 attachment.countDownLatch.countDown();
		
	}

}
