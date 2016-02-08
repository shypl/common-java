package org.shypl.common.concurrent;

import org.shypl.common.util.LinkedQueue;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TaskQueue {
	private final Queue<Runnable> queue = new LinkedQueue<>();
	private final ScheduledExecutorService executor;
	private       boolean                  executing;

	public TaskQueue(ScheduledExecutorService executor) {
		this.executor = executor;
	}

	public ScheduledTask schedule(Runnable task, long delay, TimeUnit unit) {
		ScheduledTaskImpl scheduledTask = new ScheduledTaskImpl(task);
		scheduledTask.setFuture(executor.schedule(scheduledTask, delay, unit));
		return scheduledTask;
	}

	public ScheduledTask schedulePeriodic(Runnable task, long initialDelay, long period, TimeUnit unit) {
		ScheduledTaskImpl scheduledTask = new ScheduledTaskImpl(task);
		scheduledTask.setFuture(executor.scheduleAtFixedRate(scheduledTask, initialDelay, period, unit));
		return scheduledTask;
	}

	public void add(Runnable task) {
		synchronized (queue) {
			if (executing) {
				queue.add(task);
				return;
			}
			executing = true;
		}
		executor.execute(new Task(task));
	}

	private class Task implements Runnable {
		private Runnable task;

		public Task(Runnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			try {
				task.run();
			}
			catch (Throwable e) {
				LoggerFactory.getLogger(TaskQueue.class).error("Error on run task " + task.getClass().getName(), e);
			}

			synchronized (queue) {
				task = queue.poll();
				executing = task != null;
			}

			if (executing) {
				executor.execute(this);
			}
		}
	}

	private class ScheduledTaskImpl implements ScheduledTask, Runnable {
		private final Runnable           task;
		private       ScheduledFuture<?> future;
		private volatile boolean actual = true;

		public ScheduledTaskImpl(Runnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			if (actual) {
				add(task);
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
