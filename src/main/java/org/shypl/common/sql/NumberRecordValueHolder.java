package org.shypl.common.sql;

import java.sql.SQLException;

public class NumberRecordValueHolder<T extends Number> extends AbstractRecordValueHolder<T> {
	public NumberRecordValueHolder(T value) {
		super(value);
	}

	@Override
	public void doSave(AddablePreparedStatement st) throws SQLException {
		T value = get();
		if (value instanceof Integer) {
			st.addInt(value.intValue());
		}
		else if (value instanceof Long) {
			st.addLong(value.longValue());
		}
		else if (value instanceof Byte) {
			st.addByte(value.byteValue());
		}
		else if (value instanceof Short) {
			st.addShort(value.shortValue());
		}
		else if (value instanceof Double) {
			st.addDouble(value.doubleValue());
		}
		else if (value instanceof Float) {
			st.addFloat(value.floatValue());
		}
	}
}
