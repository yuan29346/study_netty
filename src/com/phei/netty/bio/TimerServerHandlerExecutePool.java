/**
 * 
 */
package com.phei.netty.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * 
 */
public class TimerServerHandlerExecutePool {

	private ExecutorService executor;

	public TimerServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime()
				.availableProcessors(), maxPoolSize, 12L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(queueSize));
		 
	}
	
	public void execute(Runnable task){
		executor.execute(task);
	}
}
