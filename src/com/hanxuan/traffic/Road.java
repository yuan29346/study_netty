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
		//����·�ϵ�����
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	
}
