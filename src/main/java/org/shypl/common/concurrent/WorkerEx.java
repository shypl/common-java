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
import java.util.function.Supplier;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WorkerEx {
	public static final Logger LOGGER = LoggerFactory.getLogger(WorkerEx.class);
	
	private final MpscLinkedQueue8<Runnable> tasks   = new MpscLinkedQueue8<>();
	private final AtomicBoolean              working = new AtomicBoolean();
	private final Supplier<ScheduledExecutorService> executor;
	
	private volatile Thread thread;
	
	public WorkerEx(Supplier<ScheduledExecutorService> executor) {
		this.executor = executor;
	}
	
	public WorkerEx(ScheduledExecutorService executor) {
		this.executor = () -> executor;
	}
	
	public void addTask(Runnable task) {
		addTask(task, true);
	}
	
	public void addTask(Runnable task, boolean addToQueue) {
		if (addToQueue) {
			planRunTask(task);
		}
		else {
			tryRunTaskNow(task);
		}
	}
	
	private void tryRunTaskNow(Runnable task) {
		if (isSameThread()) {
			runExceptionSafe(task);
			return;
		}
		
		if (working.compareAndSet(false, true)) {
			this.thread = currentThread();
			
			runExceptionSafe(task);
			
			this.thread = null;
			this.working.set(false);
			planRunTasks();
			
			return;
		}
		
		tasks.add(task);
		planRunTasks();
	}
	
	private void planRunTask(Runnable task) {
		tasks.add(task);
		
		if (working.compareAndSet(false, true)) {
			executor.get().execute(this::runTasks);
		}
	}
	
	private void runTasks() {
		thread = currentThread();
		
		tasks.drain(this::runExceptionSafe);
		
		thread = null;
		working.set(false);
		planRunTasks();
	}
	
	private void runExceptionSafe(Runnable task) {
		try {
			task.run();
		}
		catch (Throwable e) {
			LOGGER.error("Error on run task " + task.getClass().getName(), e);
		}
	}
	
	private void planRunTasks() {
		if (!tasks.isEmpty() && working.compareAndSet(false, true)) {
			executor.get().execute(this::runTasks);
		}
	}
	
	public Cancelable scheduleTask(Runnable task, LocalDateTime date) {
		long delayMills = Duration.between(LocalDateTime.now(), date).toMillis();
		return scheduleTask(task, delayMills, MILLISECONDS);
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
	
	private boolean isSameThread() {
		return thread == currentThread();
	}
}
