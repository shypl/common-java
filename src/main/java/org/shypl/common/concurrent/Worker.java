package org.shypl.common.concurrent;

import org.shypl.common.util.Cancelable;
import org.shypl.common.util.LinkedQueue;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Worker {
	private final Object          lock                 = new Object();
	private final Queue<Runnable> executorTasks        = new LinkedQueue<>();
	private final Queue<Runnable> executingThreadTasks = new LinkedQueue<>();
	private final ScheduledExecutorService executor;
	private       boolean                  executing;
	private       long                     executingThreadId;
	
	public Worker(ScheduledExecutorService executor) {
		this.executor = executor;
	}
	
	public void addTask(Runnable task) {
		long currentThreadId = getCurrentThreadId();
		
		synchronized (lock) {
			if (executing) {
				if (currentThreadId == executingThreadId) {
					executingThreadTasks.add(task);
				}
				else {
					executorTasks.add(task);
				}
				return;
			}
			
			executing = true;
			executingThreadId = currentThreadId;
		}
		
		new TaskRunner(task).run();
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
	
	private long getCurrentThreadId() {
		return Thread.currentThread().getId();
	}
	
	private class TaskRunner implements Runnable {
		private Runnable task;
		
		public TaskRunner(Runnable task) {
			this.task = task;
		}
		
		@Override
		public void run() {
			
			synchronized (lock) {
				executingThreadId = getCurrentThreadId();
			}
			
			runTask();
			
			while (true) {
				synchronized (lock) {
					task = executingThreadTasks.poll();
					if (task == null) {
						executingThreadId = 0;
						break;
					}
				}
				runTask();
			}
			
			synchronized (lock) {
				task = executorTasks.poll();
				if (task == null) {
					executing = false;
					return;
				}
			}
			
			executor.execute(this);
		}
		
		private void runTask() {
			try {
				task.run();
			}
			catch (Throwable e) {
				LoggerFactory.getLogger(TaskRunner.class).error("Error on run task " + task.getClass().getName(), e);
			}
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
