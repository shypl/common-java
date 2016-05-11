package org.shypl.common.sql.holders;

import org.shypl.common.sql.AddablePreparedStatement;

import java.sql.SQLException;

public class LongHolder extends AbstractHolder {
	private final boolean unsigned;
	private       long    value;

	public LongHolder() {
		this(0);
	}

	public LongHolder(String value) {
		this(Long.parseUnsignedLong(value));
	}

	public LongHolder(long value) {
		this(value, true);
	}

	public LongHolder(long value, boolean unsigned) {
		this.value = value;
		this.unsigned = unsigned;
	}

	public long get() {
		return value;
	}

	public void set(long value) {
		if (this.value != value) {
			this.value = value;
			markAsChanged();
		}
	}

	@Override
	protected void write(AddablePreparedStatement st) throws SQLException {
		if (unsigned && value < 0) {
			st.addString(Long.toUnsignedString(value));
		}
		else {
			st.addLong(value);
		}
	}
}
