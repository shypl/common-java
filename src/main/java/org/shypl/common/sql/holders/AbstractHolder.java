package org.shypl.common.sql.holders;

import org.shypl.common.sql.AddablePreparedStatement;
import org.shypl.common.sql.RecordValueHolder;

import java.sql.SQLException;

public abstract class AbstractHolder implements RecordValueHolder {
	private boolean changed;

	@Override
	public final boolean isChanged() {
		return changed;
	}

	@Override
	public final void save(AddablePreparedStatement st) throws SQLException {
		write(st);
		changed = false;
	}

	protected abstract void write(AddablePreparedStatement st) throws SQLException;

	protected final void markAsChanged() {
		changed = true;
	}
}
