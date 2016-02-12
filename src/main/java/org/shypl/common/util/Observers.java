package org.shypl.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Observers<T> implements Iterable<T> {
	private final Map<T, ObserverHolder> observers = new ConcurrentHashMap<>();

	public ObserverHolder add(T observer) {
		ObserverHolder holder = observers.get(observer);
		if (holder == null) {
			holder = observers.computeIfAbsent(observer, t -> () -> remove(t));
		}
		return holder;
	}

	public void remove(T observer) {
		observers.remove(observer);
	}

	@Override
	public Iterator<T> iterator() {
		return observers.keySet().iterator();
	}
}
