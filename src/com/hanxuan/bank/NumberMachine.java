/**
 * 
 */
package com.hanxuan.bank;

/**
 * @author Administrator
 * 
 */
public class NumberMachine {
	
	private static NumberMachine instance = new NumberMachine();
	
	private NumberManager commManager = new NumberManager();

	private NumberManager expressManager = new NumberManager();

	private NumberManager vipManager = new NumberManager();
	
	private NumberMachine(){}
	
	public static NumberMachine getInstance(){
		return instance;
	}

	public NumberManager getCommManager() {
		return commManager;
	}

	public NumberManager getExpressManager() {
		return expressManager;
	}

	public NumberManager getVipManager() {
		return vipManager;
	}

}
