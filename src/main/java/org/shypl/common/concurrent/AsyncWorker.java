
package org.shypl.common.concurrent;

import org.jctools.queues.MpscLinkedQueue8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

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
	
	public class Task extends CompletableFuture<Void> {
		private final Supplier<CompletableFuture<Void>> workSupplier;
		
		public Task(Supplier<CompletableFuture<Void>> workSupplier) {
			this.workSupplier = workSupplier;
		}
		
		public Supplier<CompletableFuture<Void>> getWorkSupplier() {
			return workSupplier;
		}
	}
	
	public Task addTask(Runnable runnable) {
		return addTask(() -> {
			runnable.run();
			return CompletableFuture.completedFuture(null);
		});
	}
	
	public Task addTask(Supplier<CompletableFuture<Void>> workSupplier) {
		Task task = new Task(workSupplier);
		
		tasks.add(task);
		
		if (working.compareAndSet(false, true)) {
			executor.execute(this::runNextTask);
		}
		
		return task;
	}
	
	private void runNextTask() {
		Task task = tasks.relaxedPeek();
		
		if (task != null) {
			long now = currentTimeMillis();
			long delay = delayBetweenTasks == 0 ? 0 : lastTaskStartTime + delayBetweenTasks - now;
			if (delay > 0) {
				executor.schedule(this::runNextTask, delay, MILLISECONDS);
				return;
			}
			
			lastTaskStartTime = now;
			runTask(tasks.poll());
			return;
		}
		
		working.set(false);
		if (!tasks.isEmpty() && working.compareAndSet(false, true)) {
			executor.execute(this::runNextTask);
		}
	}
	
	private synchronized void runTask(Task task) {
		try {
			CompletableFuture<Void> workFuture = task.getWorkSupplier().get();
			workFuture.whenCompleteAsync((aVoid, throwable) -> runNextTask(), executor);
			workFuture.handle((result, exception) -> exception == null ? task.complete(result) : task.completeExceptionally(exception));
		}
		catch (Throwable e) {
			task.completeExceptionally(e);
			executor.execute(this::runNextTask);
		}
	}
	
	private Void logException(Throwable e) {
		LOGGER.error("Error on run task ", e);
		
		return null;
	}
}
