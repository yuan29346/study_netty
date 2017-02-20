/**
 * 
 */
package com.hanxuan.bank;

import java.util.concurrent.Executors;

/**
 * @author Administrator
 *
 */
public class ServiceWindow {
	
	private CustomerType type = CustomerType.COMMON;
	private int windowId=1;
	
	
	public void start(){
		
		Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				 //取号
				
				while(true){
					switch(type){
					case COMMON:{
						
						commonService();
						}break;
					case EXPRESS: {
						
						  expressService();
					}break;
					case VIP: {
						 vipService();	
					};
					}
					 
				}
				
			}
		});
	}

	private void commonService(){
		Integer num = NumberMachine.getInstance().getCommManager().fetchServiceNumber();
		String windowName = "第"+windowId+"号"+type+"窗口";
		System.out.println(windowName+"开始取任务。。。");
		 if(num == null){
			 System.out.println(windowName+"没有取到任务,休息一秒");
			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 }else{
			 System.out.println(windowName+"正在为第"+num+"个普通客户服务");
			 long beginTime = System.currentTimeMillis();
			 try {
				Thread.sleep(Constants.getRandomTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 long endTime = System.currentTimeMillis();
			 System.out.println(windowName+"为第"+num+"个普通客户耗费时间："+(endTime-beginTime)/1000+"秒");
		 }
	}
	

	private void expressService(){
		Integer num = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
		String windowName = "第"+windowId+"号"+type+"窗口";
		System.out.println(windowName+"开始取任务。。。");
		 if(num == null){
			 System.out.println(windowName+"没有取到"+type+"任务");
			commonService();
		 }else{
			 System.out.println(windowName+"正在为第"+num+"个"+type+"客户服务");
			 long beginTime = System.currentTimeMillis();
			 try {
				Thread.sleep(Constants.getRandomTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 long endTime = System.currentTimeMillis();
			 System.out.println(windowName+"为第"+num+"个"+type+"客户耗费时间："+(endTime-beginTime)/1000+"秒");
		 }
	}
	

	private void vipService(){
		Integer num = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
		String windowName = "第"+windowId+"号"+type+"窗口";
		System.out.println(windowName+"开始取任务。。。");
		 if(num == null){
			 System.out.println(windowName+"没有取到"+type+"任务");
			commonService();
		 }else{
			 System.out.println(windowName+"正在为第"+num+"个"+type+"客户服务");
			 long beginTime = System.currentTimeMillis();
			 try {
				Thread.sleep(Constants.getRandomTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 long endTime = System.currentTimeMillis();
			 System.out.println(windowName+"为第"+num+"个"+type+"客户耗费时间："+(endTime-beginTime)/1000+"秒");
		 }
	}
	
	public void setType(CustomerType type) {
		this.type = type;
	}

	public CustomerType getType() {
		return type;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

}
