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
				 //ȡ��
				
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
		String windowName = "��"+windowId+"��"+type+"����";
		System.out.println(windowName+"��ʼȡ���񡣡���");
		 if(num == null){
			 System.out.println(windowName+"û��ȡ������,��Ϣһ��");
			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 }else{
			 System.out.println(windowName+"����Ϊ��"+num+"����ͨ�ͻ�����");
			 long beginTime = System.currentTimeMillis();
			 try {
				Thread.sleep(Constants.getRandomTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 long endTime = System.currentTimeMillis();
			 System.out.println(windowName+"Ϊ��"+num+"����ͨ�ͻ��ķ�ʱ�䣺"+(endTime-beginTime)/1000+"��");
		 }
	}
	

	private void expressService(){
		Integer num = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
		String windowName = "��"+windowId+"��"+type+"����";
		System.out.println(windowName+"��ʼȡ���񡣡���");
		 if(num == null){
			 System.out.println(windowName+"û��ȡ��"+type+"����");
			commonService();
		 }else{
			 System.out.println(windowName+"����Ϊ��"+num+"��"+type+"�ͻ�����");
			 long beginTime = System.currentTimeMillis();
			 try {
				Thread.sleep(Constants.getRandomTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 long endTime = System.currentTimeMillis();
			 System.out.println(windowName+"Ϊ��"+num+"��"+type+"�ͻ��ķ�ʱ�䣺"+(endTime-beginTime)/1000+"��");
		 }
	}
	

	private void vipService(){
		Integer num = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
		String windowName = "��"+windowId+"��"+type+"����";
		System.out.println(windowName+"��ʼȡ���񡣡���");
		 if(num == null){
			 System.out.println(windowName+"û��ȡ��"+type+"����");
			commonService();
		 }else{
			 System.out.println(windowName+"����Ϊ��"+num+"��"+type+"�ͻ�����");
			 long beginTime = System.currentTimeMillis();
			 try {
				Thread.sleep(Constants.getRandomTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 long endTime = System.currentTimeMillis();
			 System.out.println(windowName+"Ϊ��"+num+"��"+type+"�ͻ��ķ�ʱ�䣺"+(endTime-beginTime)/1000+"��");
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
