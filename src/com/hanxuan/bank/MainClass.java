/**
 * 
 */
package com.hanxuan.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public class MainClass {

	public static void main(String[] args) {
		
		
		for(int i=1;i<5;i++){
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setWindowId(i);
			commonWindow.start();
		}
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setWindowId(1);
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
	
		ServiceWindow vipWindow = new ServiceWindow();
		vipWindow.setWindowId(1);
		vipWindow.setType(CustomerType.VIP);
		vipWindow.start();
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					
					@Override
					public void run() {
						Integer num = NumberMachine.getInstance().getCommManager().generateNewNumber();
						System.out.println(num+"号普通客户等待服务！");
						
					}
				},
				0,
				Constants.COMMON_CUSTOMER_INTERVAL_TIME, 
				TimeUnit.SECONDS);
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					
					@Override
					public void run() {
						Integer num = NumberMachine.getInstance().getExpressManager().generateNewNumber();
						System.out.println(num+"号快速客户等待服务！");
						
					}
				},
				0,
				Constants.COMMON_CUSTOMER_INTERVAL_TIME*2,
				TimeUnit.SECONDS);
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					
					@Override
					public void run() {
						Integer num = NumberMachine.getInstance().getVipManager().generateNewNumber();
						System.out.println(num+"号VIP客户等待服务！");
						
					}
				},
				0,
				Constants.COMMON_CUSTOMER_INTERVAL_TIME*6,
				TimeUnit.SECONDS);
		
	}
}
