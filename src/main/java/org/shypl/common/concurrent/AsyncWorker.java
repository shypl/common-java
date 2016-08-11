
package org.shypl.common.concurrent;

import org.jctools.queues.MpscLinkedQueue8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class AsyncWorker {
	public static final Logger LOGGER = LoggerFactory.getLogger(AsyncWorker.class);
	
	private final MpscLinkedQueue8<Task> tasks = new MpscLinkedQueue8<>();
	private final ScheduledExecutorService executor;
	private final AtomicBoolean working = new AtomicBoolean();
	
	private final    int  delayBetweenTasks;
	private volatile long lastTaskStartTime;
	
	public AsyncWorker(ScheduledExecutorService executor) {
		this(executor, 0);
	}
	
	public AsyncWorker(ScheduledExecutorService executor, int delayBetweenTasks) {
		this.executor = executor;
		this.delayBetweenTasks = delayBetweenTasks;
	}
	
	public CompletableFuture<Void> addTask(Runnable runnable) {
		Task task = new Task(runnable);

		tasks.add(task);
		
		if (working.compareAndSet(false, true)) {
			executor.execute(this::runTasks);
		}
		
		return task.cf;
	}
	
	private void runTasks() {
		while (tasks.relaxedPeek() != null) {
			long now = currentTimeMillis();
			long delay = delayBetweenTasks == 0 ? 0 : lastTaskStartTime + delayBetweenTasks - now;
			if (delay > 0) {
				executor.schedule(this::runTasks, delay, MILLISECONDS);
				return;
			}
			
			lastTaskStartTime = now;
			tasks.poll().run();
		}
		
		working.set(false);
		if (!tasks.isEmpty() && working.compareAndSet(false, true)) {
			executor.execute(this::runTasks);
		}
	}
	
	private class Task {
		private final Runnable internalRunnable;
		private final CompletableFuture<Void> cf = new CompletableFuture<>();
		
		public Task(Runnable internalRunnable) {
			this.internalRunnable = internalRunnable;
		}
		
		public void run() {
			if (cf.isDone()) {
				return;
			}
			
			try {
				internalRunnable.run();
				cf.complete(null);
			}
			catch (Throwable e) {
				LOGGER.error("Error on run task ", e);
				cf.completeExceptionally(e);
			}
		}
	}
	
}
