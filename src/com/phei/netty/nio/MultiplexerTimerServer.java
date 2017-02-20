package com.phei.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimerServer implements Runnable{
	
	private Selector selector;
	
	private ServerSocketChannel servChannel;
	
	private volatile boolean stop;
	
	
	public MultiplexerTimerServer(int port) {
		 try {
			selector = Selector.open();
			
			servChannel = ServerSocketChannel.open();
			//非阻塞方式
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port),1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("the time server is start in port:"+port);
			
			
		} catch (IOException e) {
		 
			e.printStackTrace();
		}
		 
	}

	@Override
	public void run() {
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					
					try{
						handleInput(key);
					}catch(Exception e){
						if(key != null){
							key.cancel();
							if(key.channel() != null){
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(selector != null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException{
		if(key.isValid()){
			System.out.println("Acceptable="+key.isAcceptable()+"  Readable="+key.isReadable());
			if(key.isAcceptable()){
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
				
			}else if(key.isReadable()){
				System.out.println("isReadable@@@@@@@@@@ ");
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				//读取服务器发送的数据
				int readBytes = sc.read(readBuffer);
				System.out.println("readBytes="+readBytes);
				if(readBytes>0){
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"utf-8");
					System.out.println("the time server receive order :"+body);
					String currentTime = "Query time order".equalsIgnoreCase(body)?new Date().toLocaleString():"BAD ORDER";
					doWrite(sc,currentTime);
				}else if(readBytes<0){
					key.cancel();
					sc.close();
				}
				
			}
		}
	}
	
	
	private void doWrite(SocketChannel sc,String response) throws IOException{
		if(null != response && response.trim().length()>0){
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			sc.write(writeBuffer);
		}
	}

	public void stop(){
		this.stop = true;
	}
}
