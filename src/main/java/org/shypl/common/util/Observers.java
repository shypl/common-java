package org.shypl.common.util;

import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class Observers<T> {

	private final Lock                              lock         = new ReentrantLock();
	private final LinkedHashMap<T, ObserveRepealer> observersMap = new LinkedHashMap<>(1);
	private final Consumer<T> informer;
	private       Object[]    observersCache;

	public Observers(Consumer<T> informer) {
		this.informer = informer;
	}

	public Observers() {
		this(null);
	}

	public Cancelable add(T observer) {
		lock.lock();
		try {
			ObserveRepealer holder = observersMap.get(observer);
			if (holder == null) {
				observersCache = null;
				holder = new ObserveRepealer(observer);
			}
			return holder;
		}
		finally {
			lock.unlock();
		}
	}

	public void remove(T observer) {
		lock.lock();
		try {
			if (null != observersMap.remove(observer)) {
				observersCache = null;
			}
		}
		finally {
			lock.unlock();
		}
	}

	public void removeAll() {
		lock.lock();
		try {
			observersMap.clear();
			observersCache = null;
		}
		finally {
			lock.unlock();
		}
	}

	public void inform() {
		inform(informer);
	}

	public void inform(Consumer<T> informer) {
		Objects.requireNonNull(informer);

		Object[] observers;

		lock.lock();
		try {
			if (observersCache == null) {
				observersCache = observersMap.keySet().toArray();
			}
			observers = observersCache;
		}
		finally {
			lock.unlock();
		}

		inform0(informer, observers);
	}

	@SuppressWarnings("unchecked")
	private void inform0(Consumer<T> informer, Object[] observers) {
		for (Object observer : observers) {
			try {
				informer.accept((T)observer);
			}
			catch (Throwable e) {
				LoggerFactory.getLogger(Observers.class).error("Error in inform observer", e);
			}
		}
	}

	private class ObserveRepealer implements Cancelable {
		private final T observer;

		public ObserveRepealer(T observer) {
			this.observer = observer;
		}

		@Override
		public void cancel() {
			remove(observer);
		}
	}
}
