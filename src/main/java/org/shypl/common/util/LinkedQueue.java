package org.shypl.common.util;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> extends AbstractQueue<E> {

	private int     size;
	private Node<E> first;
	private Node<E> last;

	@Override
	public Iterator<E> iterator() {
		return size == 0 ? EmptyIterator.getInstance() : new Itr<>(first);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("remove");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("removeAll");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("retainAll");
	}

	@Override
	public boolean offer(E e) {
		if (size == Integer.MAX_VALUE) {
			return false;
		}

		Node<E> node = new Node<>(e);

		if (++size == 1) {
			first = node;
		}
		else {
			last.next = node;
		}
		last = node;

		return true;
	}

	@Override
	public E poll() {
		if (size == 0) {
			return null;
		}

		E e = first.element;
		first = first.next;

		if (--size == 0) {
			last = null;
		}

		return e;
	}

	@Override
	public E peek() {
		return first.element;
	}

	private static class Itr<E> implements Iterator<E> {

		private Node<E> current;

		public Itr(Node<E> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			E element = current.element;
			current = current.next;
			return element;
		}
	}

	private static class Node<E> {
		E       element;
		Node<E> next;

		Node(E element) {
			this.element = element;
		}
	}
}
