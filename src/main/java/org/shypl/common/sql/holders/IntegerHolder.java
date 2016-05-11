package org.shypl.common.sql.holders;

import org.shypl.common.sql.AddablePreparedStatement;

import java.sql.SQLException;

public class IntegerHolder extends AbstractHolder {
	private final boolean unsigned;
	private       int     value;

	public IntegerHolder() {
		this(0);
	}

	public IntegerHolder(String value) {
		this(Integer.parseUnsignedInt(value));
	}

	public IntegerHolder(int value) {
		this(value, true);
	}

	public IntegerHolder(int value, boolean unsigned) {
		this.value = value;
		this.unsigned = unsigned;
	}

	public int get() {
		return value;
	}

	public void set(int v) {
		if (value != v) {
			value = v;
			markAsChanged();
		}
	}

	public void add(int v) {
		if (v != 0) {
			value += v;
			markAsChanged();
		}
	}

	@Override
	protected void write(AddablePreparedStatement st) throws SQLException {
		if (unsigned && value < 0) {
			st.addLong(Integer.toUnsignedLong(value));
		}
		else {
			st.addInt(value);
		}
	}
}
