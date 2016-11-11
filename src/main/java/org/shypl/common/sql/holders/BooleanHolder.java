package org.shypl.common.sql.holders;

import org.shypl.common.sql.AddablePreparedStatement;

import java.sql.SQLException;

public class BooleanHolder extends AbstractHolder {
	private boolean value;
	
	public BooleanHolder() {
		this(false);
	}
	
	public BooleanHolder(String value) {
		this(Boolean.parseBoolean(value));
	}
	
	public BooleanHolder(boolean value) {
		this.value = value;
	}
	
	public boolean get() {
		return value;
	}
	
	public void set(boolean v) {
		if (value != v) {
			value = v;
			markAsChanged();
		}
	}
	
	public void negative() {
		value = !value;
		markAsChanged();
	}
	
	@Override
	protected void write(AddablePreparedStatement st) throws SQLException {
		st.addBoolean(value);
	}
}
