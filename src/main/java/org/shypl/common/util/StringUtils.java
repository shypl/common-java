package org.shypl.common.util;

import java.util.Collection;
import java.util.Map;

public final class StringUtils {

	private static final String STRING_NULL                = "<null>";
	private static final String STRING_TRUE                = "<true>";
	private static final String STRING_FALSE               = "<false>";
	private static final String STRING_SEQUENCE_SEPARATOR  = ", ";
	private static final String STRING_KEY_VALUE_SEPARATOR = ": ";
	private static final String STRING_EMPTY_LIST          = "[]";
	private static final String STRING_EMPTY_MAP           = "{}";

	private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String toString(boolean v) {
		return v ? STRING_TRUE : STRING_FALSE;
	}

	public static String toString(byte v) {
		int i = v & 0xFF;
		return new String(new char[]{DIGITS[i >>> 4], DIGITS[i & 0x0F]});
	}

	public static String toString(char v) {
		return String.valueOf(v);
	}

	public static String toString(short v) {
		return String.valueOf(v);
	}

	public static String toString(int v) {
		return String.valueOf(v);
	}

	public static String toString(long v) {
		return String.valueOf(v);
	}

	public static String toString(float v) {
		return String.valueOf(v);
	}

	public static String toString(double v) {
		return String.valueOf(v);
	}

	public static String toString(Object v) {
		if (v == null) {
			return STRING_NULL;
		}

		if (v instanceof Boolean) {
			return toString((boolean)v);
		}
		if (v instanceof Byte) {
			return toString((byte)v);
		}
		if (v instanceof Character) {
			return toString((char)v);
		}
		if (v instanceof Short) {
			return toString((short)v);
		}
		if (v instanceof Integer) {
			return toString((int)v);
		}
		if (v instanceof Long) {
			return toString((long)v);
		}
		if (v instanceof Float) {
			return toString((float)v);
		}
		if (v instanceof Double) {
			return toString((double)v);
		}

		if (v instanceof Collection) {
			return toString((Collection)v);
		}

		if (v instanceof Map) {
			return toString((Map)v);
		}

		if (v instanceof boolean[]) {
			return toString((boolean[])v);
		}
		if (v instanceof byte[]) {
			return toString((byte[])v);
		}
		if (v instanceof char[]) {
			return toString((char[])v);
		}
		if (v instanceof short[]) {
			return toString((short[])v);
		}
		if (v instanceof int[]) {
			return toString((int[])v);
		}
		if (v instanceof long[]) {
			return toString((long[])v);
		}
		if (v instanceof float[]) {
			return toString((float[])v);
		}
		if (v instanceof double[]) {
			return toString((double[])v);
		}

		if (v.getClass().isArray()) {
			return toString((Object[])v);
		}

		return v.toString();
	}

	public static String toString(boolean[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder(9 * a.length);
		boolean sep = false;
		string.append('[');
		for (boolean v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(byte[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder(4 * a.length);
		boolean sep = false;
		string.append('[');
		for (byte v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			int i = v & 0xFF;
			string.append(DIGITS[i >>> 4]).append(DIGITS[i & 0x0F]);
		}
		return string.append(']').toString();
	}

	public static String toString(char[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (char v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(short[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (short v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(int[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (int v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(long[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (long v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(float[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (float v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(double[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (double v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(Object[] a) {
		if (a == null) {
			return STRING_NULL;
		}
		if (a.length == 0) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (Object v : a) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(Collection<?> c) {
		if (c == null) {
			return STRING_NULL;
		}
		if (c.isEmpty()) {
			return STRING_EMPTY_LIST;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('[');
		for (Object v : c) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(v));
		}
		return string.append(']').toString();
	}

	public static String toString(Map<?, ?> m) {
		if (m == null) {
			return STRING_NULL;
		}
		if (m.isEmpty()) {
			return STRING_EMPTY_MAP;
		}

		StringBuilder string = new StringBuilder();
		boolean sep = false;
		string.append('{');

		for (Map.Entry<?, ?> e : m.entrySet()) {
			if (sep) {
				string.append(STRING_SEQUENCE_SEPARATOR);
			}
			else {
				sep = true;
			}
			string.append(toString(e.getKey()))
				.append(STRING_KEY_VALUE_SEPARATOR)
				.append(toString(e.getValue()));
		}
		return string.append('}').toString();
	}
}
