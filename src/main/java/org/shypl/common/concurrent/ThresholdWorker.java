
package org.shypl.common.concurrent;

import org.jctools.queues.MpscLinkedQueue8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class ThresholdWorker {
	public static final Logger LOGGER                      = LoggerFactory.getLogger(ThresholdWorker.class);
	
	private final MpscLinkedQueue8<Runnable> tasks = new MpscLinkedQueue8<>();
	private final ScheduledExecutorService executor;
	private final AtomicBoolean working = new AtomicBoolean();
	
	private final    int  delayBetweenTasks;
	private volatile long lastTaskStartTime;
	
	public ThresholdWorker(ScheduledExecutorService executor, int delayBetweenTasks) {
		this.executor = executor;
		this.delayBetweenTasks = delayBetweenTasks;
	}
	
	public void addTask(Runnable task) {
		tasks.add(task);
		
		if (working.compareAndSet(false, true)) {
			executor.execute(this::runTasks);
		}
	}
	
	private void runTasks() {
		while (tasks.relaxedPeek() != null) {
			long now = currentTimeMillis();
			long delay = lastTaskStartTime + delayBetweenTasks - now;
			if (delay > 0) {
				executor.schedule(this::runTasks, delay, MILLISECONDS);
				return;
			}
			
			lastTaskStartTime = now;
			runTask(tasks.poll());
		}
		
		working.set(false);
		if (!tasks.isEmpty() && working.compareAndSet(false, true)) {
			executor.execute(this::runTasks);
		}
	}
	
	private void runTask(Runnable task) {
		try {
			task.run();
		}
		catch (Throwable e) {
			LOGGER.error("Error on run task " + task.getClass().getName(), e);
		}
	}
	
}
