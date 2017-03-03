/**
 * 
 */
package com.netty.timer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Administrator
 *
 */
public class TimerServer {

	public void bind(int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1024)
		.childHandler(new TimeServerInitializer());
		try {
			ChannelFuture future = b.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
//	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
//
//		@Override
//		protected void initChannel(SocketChannel arg0) throws Exception {
//			arg0.pipeline().addLast(new TimeServerInitializer());
//		}
//		
//	}
	
	public static void main(String[] args) {
		int port = 8080;
		new TimerServer().bind(port);
	}
	
}
