/**
 * 
 */
package com.phei.netty.aio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

	private AsynchronousSocketChannel channel;
	
	public ReadCompletionHandler(AsynchronousSocketChannel channel) {
		if(this.channel == null)
		this.channel = channel;
	}
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		 attachment.flip();
		 byte[] bytes = new byte[attachment.remaining()];
		 attachment.get(bytes);
		 try {
			String req = new String(bytes, "utf-8");
			System.out.println("the timer server recevie order:" + req);
			String currentTime = "Query time order".equalsIgnoreCase(req)?new Date().toLocaleString():"BAD ORDER";
			doWrite(currentTime);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void doWrite(String currentTime){
		if(currentTime != null && currentTime.length()>0){
			byte[] bytes = currentTime.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer,writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					
					
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					// TODO Auto-generated method stub
					
				}
			
			
			});
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		
	}

}
