package com.phei.netty.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimerClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8082;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader( socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println(" QUERY TIME ORDER");
			System.out.println("saaaaaaaaaaaa");
			String response = in.readLine();
			System.out.println("response:"+response);
		} catch (Exception e) {
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
		}
		try{
			socket.close();
		}catch (Exception e) {
			 e.printStackTrace();
		}
		socket= null;
	}

}
