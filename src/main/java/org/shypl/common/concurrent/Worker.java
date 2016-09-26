package org.shypl.common.concurrent;

import org.shypl.common.util.Cancelable;
import org.shypl.common.util.LinkedQueue;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Worker {
	private final Lock            lock  = new ReentrantLock();
	private final Queue<Runnable> tasks = new LinkedQueue<>();
	private final ScheduledExecutorService executor;
	private       boolean                  executing;
	
	public Worker(ScheduledExecutorService executor) {
		this.executor = executor;
	}
	
	public void addTask(Runnable task) {
		lock.lock();
		try {
			if (executing) {
				tasks.add(task);
				return;
			}
			executing = true;
		}
		finally {
			lock.unlock();
		}
		
		executor.execute(new TaskRunner(task));
	}
	
	public Cancelable scheduleTask(Runnable task, long delay, TimeUnit unit) {
		ScheduledTaskHolder holder = new ScheduledTaskHolder(task);
		holder.setFuture(executor.schedule(holder, delay, unit));
		return holder;
	}
	
	public Cancelable scheduleTaskPeriodic(Runnable task, long period, TimeUnit unit) {
		return scheduleTaskPeriodic(task, period, period, unit);
	}
	
	public Cancelable scheduleTaskPeriodic(Runnable task, long initialDelay, long period, TimeUnit unit) {
		ScheduledTaskHolder scheduledTask = new ScheduledTaskHolder(task);
		scheduledTask.setFuture(executor.scheduleAtFixedRate(scheduledTask, initialDelay, period, unit));
		return scheduledTask;
	}
	
	private class TaskRunner implements Runnable {
		private Runnable task;
		
		public TaskRunner(Runnable task) {
			this.task = task;
		}
		
		@Override
		public void run() {
			try {
				task.run();
			}
			catch (Throwable e) {
				LoggerFactory.getLogger(TaskRunner.class).error("Error on run task " + task.getClass().getName(), e);
			}
			
			lock.lock();
			try {
				task = tasks.poll();
				if (task == null) {
					executing = false;
					return;
				}
				executing = true;
			}
			finally {
				lock.unlock();
			}
			
			executor.execute(this);
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
