package org.shypl.common.sql;

import java.sql.SQLException;

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

	@Override
	public final void save(AddablePreparedStatement st) throws SQLException {
		doSave(st);
		changed = false;
	}

	protected abstract void doSave(AddablePreparedStatement st) throws SQLException;

	protected void markAsChanged() {
		changed = true;
	}
}
