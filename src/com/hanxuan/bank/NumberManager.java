/**
 * 
 */
package com.hanxuan.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class NumberManager {

	private int lastNumber = 1;
	
	private List<Integer> queueList = new ArrayList<Integer>();

	public synchronized int generateNewNumber() {
		queueList.add(lastNumber);
		return lastNumber++;
	}
	
	public synchronized Integer fetchServiceNumber(){
		if(queueList.size()>0)
		return queueList.remove(0);
		else
			return null;
	}
}
