/**
 * 
 */
package com.hanxuan.bank;

import java.util.Random;

/**
 * @author Administrator
 *
 */
public class Constants {

	public static final int MAX_SERVICE_TIME = 10000;
	public static final int MIN_SERVICE_TIME = 1000;
	
	public static final int COMMON_CUSTOMER_INTERVAL_TIME = 1;
	
	public static final int getRandomTime(){
		return new Random().nextInt(MAX_SERVICE_TIME-MIN_SERVICE_TIME)+1;
	}
}
