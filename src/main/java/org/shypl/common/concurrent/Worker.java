package org.shypl.common.concurrent;

import org.shypl.common.util.Cancelable;
import org.shypl.common.util.LinkedQueue;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Worker {
	private final Object          lock  = new Object();
	private final Queue<Runnable> queue = new LinkedQueue<>();
	private final Supplier<ScheduledExecutorService> executor;
	private       boolean                  executing;
	
	public Worker(Supplier<ScheduledExecutorService> executor) {
		this.executor = executor;
	}
	
	public Worker(ScheduledExecutorService executor) {
		this.executor = () -> executor;
	}
	
	public void addTask(Runnable task) {
		synchronized (lock) {
			if (executing) {
				queue.add(task);
				return;
			}
			executing = true;
		}
		
		runTask(task);
		processQueue();
	}
	
	public Cancelable scheduleTask(Runnable task, long delay, TimeUnit unit) {
		ScheduledTaskHolder holder = new ScheduledTaskHolder(task);
		holder.setFuture(executor.get().schedule(holder, delay, unit));
		return holder;
	}
	
	public Cancelable scheduleTaskPeriodic(Runnable task, long period, TimeUnit unit) {
		return scheduleTaskPeriodic(task, period, period, unit);
	}
	
	public Cancelable scheduleTaskPeriodic(Runnable task, long initialDelay, long period, TimeUnit unit) {
		ScheduledTaskHolder scheduledTask = new ScheduledTaskHolder(task);
		scheduledTask.setFuture(executor.get().scheduleAtFixedRate(scheduledTask, initialDelay, period, unit));
		return scheduledTask;
	}
	
	private void processQueue() {
		Runnable task;
		
		while (true) {
			synchronized (lock) {
				task = queue.poll();
				if (task == null) {
					executing = false;
					return;
				}
			}
			runTask(task);
		}
	}
	
	private void runTask(Runnable task) {
		try {
			task.run();
		}
		catch (Throwable e) {
			LoggerFactory.getLogger(Worker.class).error("Error on run task " + task.getClass().getName(), e);
		}
	}
	
	private class ScheduledTaskHolder implements Cancelable, Runnable {
		private final Runnable           task;
		private       ScheduledFuture<?> future;
		private volatile boolean actual = true;
		
		public ScheduledTaskHolder(Runnable task) {
			this.task = task;
		}
		
		@Override
		public void run() {
			if (actual) {
				addTask(task);
			}
		}
		
		@Override
		public void cancel() {
			ScheduledFuture<?> f = future;
			if (actual) {
				actual = false;
				f.cancel(false);
				future = null;
			}
		}
		
		public void setFuture(ScheduledFuture<?> future) {
			this.future = future;
		}
	}
}
