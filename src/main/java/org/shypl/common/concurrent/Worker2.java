package org.shypl.common.concurrent;

import org.jctools.queues.MpscLinkedQueue8;
import org.shypl.common.util.Cancelable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Worker2 {
	public static final Logger LOGGER = LoggerFactory.getLogger(Worker2.class);
	
	private final MpscLinkedQueue8<Runnable> tasks = new MpscLinkedQueue8<>();
	private final ScheduledExecutorService executor;
	private final AtomicBoolean working = new AtomicBoolean();
	
	private final Object mutex = new Object();
	
	public Worker2(ScheduledExecutorService executor) {
		this.executor = executor;
	}
	
	public void addTask(Runnable task) {
		tasks.add(task);
		
		if (working.compareAndSet(false, true)) {
			executor.execute(this::runTasks);
		}
	}
	
	private void runTasks() {
		tasks.drain(task -> {
			try {
				synchronized (mutex) {
					task.run();
				}
			}
			catch (Throwable e) {
				LOGGER.error("Error on run task " + task.getClass().getName(), e);
			}
		});
		
		working.set(false);
		if (!tasks.isEmpty() && working.compareAndSet(false, true)) {
			executor.execute(this::runTasks);
		}
	}
	
	public Cancelable scheduleTask(Runnable task, LocalDateTime date) {
		long delayMills = Duration.between(LocalDateTime.now(), date).toMillis();
		return scheduleTask(task, delayMills, MILLISECONDS);
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
	
	private class ScheduledTaskHolder implements Cancelable, Runnable {
		private final    Runnable           task;
		private volatile ScheduledFuture<?> future;
		private final AtomicBoolean actual = new AtomicBoolean(true);
		
		public ScheduledTaskHolder(Runnable task) {
			this.task = task;
		}
		
		@Override
		public void run() {
			if (actual.get()) {
				addTask(task);
			}
		}
		
		@Override
		public void cancel() {
			if (actual.compareAndSet(true, false)) {
				future.cancel(false);
				future = null;
			}
			
		}
		
		public void setFuture(ScheduledFuture<?> future) {
			this.future = future;
		}
	}
}