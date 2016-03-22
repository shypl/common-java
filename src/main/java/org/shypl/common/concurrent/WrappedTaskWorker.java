package org.shypl.common.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;

public class WrappedTaskWorker extends Worker {
	private final Function<Runnable, Runnable> wrapper;

	public WrappedTaskWorker(ScheduledExecutorService executor, Function<Runnable, Runnable> wrapper) {
		super(executor);
		this.wrapper = wrapper;
	}

	@Override
	public void addTask(Runnable task) {
		super.addTask(wrapper.apply(task));
	}
}
