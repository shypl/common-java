package org.shypl.common.sql;

public abstract class AbstractRecordValueHolder<T> implements RecordValueHolder {
	private T       value;
	private boolean changed;

	public AbstractRecordValueHolder(T value) {
		this.value = value;
	}

	public T get() {
		return value;
	}

	public void set(T value) {
		this.value = value;
		changed = true;
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	protected void markAsChanged() {
		changed = true;
	}
}
