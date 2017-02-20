/**
 * 
 */
package com.hanxuan.traffic;

import java.util.concurrent.Executors;

/**
 * @author Administrator
 *
 */
public class Road {

	private String name;
	
	public Road(String name){
		this.name = name;
		//创建路上的汽车
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	
}
