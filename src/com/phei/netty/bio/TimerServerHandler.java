/**
 * 
 */
package com.phei.netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class TimerServerHandler implements Runnable {

	private Socket socket;
	public TimerServerHandler(Socket socket) {
		 this.socket = socket;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		 BufferedReader in = null;
		 PrintWriter out = null;
		 try{
			 in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			 out = new PrintWriter(this.socket.getOutputStream(),true);
			 String currentTime = null;
			 String body = null;
			
			 while(true){
				 System.out.println("##########"+in.read());
				 body = in.readLine();
		 		 if(null == body)break;
				 System.out.println("time server recive order :"+body);
				 currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date().toLocaleString():"BAD ORDER";
				 out.println(currentTime);
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		}finally{
			try{
				if(null != in)
					in.close();
			}catch (Exception e) {
				 e.printStackTrace();
			}
			try{				
				if(null != out)
					out.close();
			}catch (Exception e) {
				 e.printStackTrace();
			}
			try{
				this.socket.close();
			}catch (Exception e) {
				 e.printStackTrace();
			}
		}

	}

}
