package org.shypl.common.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.LongConsumer;

public class SafeUnsignedLong {
	private final Lock                    lock            = new ReentrantLock();
	private final Observers<LongConsumer> changeObservers = new Observers<>();

	private long value;

	public SafeUnsignedLong(long value) {
		this.value = value;
	}

	public long get() {
		lock.lock();
		try {
			return value;
		}
		finally {
			lock.unlock();
		}
	}

	public void set(long value) {
		lock.lock();
		try {
			this.value = value;
			notifyChangeObservers();
		}
		finally {
			lock.unlock();
		}
	}

	public void add(long value) {
		if (value != 0) {
			lock.lock();
			try {
				if (value != -1) {
					this.value += value;
					if (Long.compareUnsigned(this.value, value) == -1) {
						this.value = -1;
					}
					notifyChangeObservers();
				}
			}
			finally {
				lock.unlock();
			}
		}
	}

	public void subtract(long value) {
		if (value != 0) {
			lock.lock();
			try {
				if (this.value != 0) {
					this.value -= value;
					if (Long.compareUnsigned(this.value, value) == 1) {
						this.value = 0;
					}
					notifyChangeObservers();
				}
			}
			finally {
				lock.unlock();
			}
		}
	}

	public boolean subtractIfEnough(long value) {
		if (value != 0) {
			lock.lock();
			try {
				if (Long.compareUnsigned(this.value, value) == -1) {
					return false;
				}
				this.value -= value;
				notifyChangeObservers();
			}
			finally {
				lock.unlock();
			}
		}
		return true;
	}

	public void increment() {
		add(1);
	}

	public void decrement() {
		subtract(1);
	}

	public boolean decrementIfEnough() {
		return subtractIfEnough(1);
	}

	public ObserverHolder addChangeObserver(LongConsumer observer) {
		return changeObservers.add(observer);
	}

	public void removeChangeObserver(LongConsumer observer) {
		changeObservers.remove(observer);
	}

	private void notifyChangeObservers() {
		for (LongConsumer observer : changeObservers) {
			observer.accept(this.value);
		}
	}
}
