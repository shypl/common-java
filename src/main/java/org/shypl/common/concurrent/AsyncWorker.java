
package org.shypl.common.concurrent;

import org.jctools.queues.MpscLinkedQueue8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class AsyncWorker {
	public static final Logger LOGGER = LoggerFactory.getLogger(AsyncWorker.class);
	
	private final MpscLinkedQueue8<Consumer<CompletableFuture<Void>>> tasks = new MpscLinkedQueue8<>();
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
	
	public void addTask(Runnable task) {
		addTask(cf -> {
			task.run();
			cf.complete(null);
		});
	}
	
	public void addTask(Consumer<CompletableFuture<Void>> task) {
		tasks.add(task);
		
		if (working.compareAndSet(false, true)) {
			executor.execute(this::runNextTask);
		}
	}
	
	private void runNextTask() {
		Consumer<CompletableFuture<Void>> task = tasks.relaxedPoll();
		
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
	
	private void runTask(Consumer<CompletableFuture<Void>> task) {
		CompletableFuture<Void> cf = new CompletableFuture<>()
			.exceptionally(this::logException)
			.thenRunAsync(this::runNextTask, executor);
		
		try {
			task.accept(cf);
		}
		catch (Throwable e) {
			cf.completeExceptionally(e);
		}
	}
	
	private Void logException(Throwable e) {
		LOGGER.error("Error on run task ", e);
		
		return null;
	}
}
