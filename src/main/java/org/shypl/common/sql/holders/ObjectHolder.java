package org.shypl.common.sql.holders;

import java.util.Objects;

public abstract class ObjectHolder<T> extends AbstractHolder {
	private T value;

	public ObjectHolder(T value) {
		this.value = value;
	}

	public T get() {
		return value;
	}

	public void set(T value) {
		if (!Objects.equals(this.value, value)) {
			this.value = value;
			markAsChanged();
		}
	}
}
