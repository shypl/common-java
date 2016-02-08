package org.shypl.common.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class EmptyIterator<E> implements Iterator<E> {

	private static final EmptyIterator<Object> INSTANCE = new EmptyIterator<>();

	@SuppressWarnings("unchecked")
	public static <T> Iterator<T> getInstance() {
		return (Iterator<T>)INSTANCE;
	}

	@Override
	public boolean hasNext() { return false; }

	@Override
	public E next() { throw new NoSuchElementException(); }

	@Override
	public void remove() { throw new IllegalStateException(); }

	@Override
	public void forEachRemaining(Consumer<? super E> action) {
		Objects.requireNonNull(action);
	}
}
