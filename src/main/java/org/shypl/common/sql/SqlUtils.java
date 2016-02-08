package org.shypl.common.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public final class SqlUtils {
	public static String buildInClauseQuery(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}

		size = size * 2 - 1;
		char[] chars = new char[size];

		chars[0] = '?';
		int i = 1;
		while (i < size) {
			chars[i++] = ',';
			chars[i++] = '?';
		}

		return new String(chars);
	}

	public static int setValues(PreparedStatement statement, int index, boolean[] values) throws SQLException {
		for (boolean value : values) {
			statement.setBoolean(index++, value);
		}
		return index;
	}

	public static int setValues(PreparedStatement statement, int index, byte[] values) throws SQLException {
		for (byte value : values) {
			statement.setByte(index++, value);
		}
		return index;
	}

	public static int setValues(PreparedStatement statement, int index, short[] values) throws SQLException {
		for (short value : values) {
			statement.setShort(index++, value);
		}
		return index;
	}

	public static int setValues(PreparedStatement statement, int index, int[] values) throws SQLException {
		for (int value : values) {
			statement.setInt(index++, value);
		}
		return index;
	}

	public static int setValues(PreparedStatement statement, int index, long[] values) throws SQLException {
		for (long value : values) {
			statement.setLong(index++, value);
		}
		return index;
	}

	public static int setValues(PreparedStatement statement, int index, float[] values) throws SQLException {
		for (float value : values) {
			statement.setFloat(index++, value);
		}
		return index;
	}

	public static int setValues(PreparedStatement statement, int index, double[] values) throws SQLException {
		for (double value : values) {
			statement.setDouble(index++, value);
		}
		return index;
	}

	public static <V> int setValues(PreparedStatement statement, int index, V[] values, PreparedStatementSetter<V> setter) throws SQLException {
		for (V value : values) {
			setter.set(statement, index++, value);
		}
		return index;
	}

	public static <V> int setValues(PreparedStatement statement, int index, Collection<V> values, PreparedStatementSetter<V> setter) throws SQLException {
		for (V value : values) {
			setter.set(statement, index++, value);
		}
		return index;
	}
}
