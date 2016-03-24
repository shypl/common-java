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
			return toString(((Boolean)v).booleanValue());
		}
		if (v instanceof Byte) {
			return toString(((Byte)v).byteValue());
		}
		if (v instanceof Character) {
			return toString(((Character)v).charValue());
		}
		if (v instanceof Short) {
			return toString(((Short)v).shortValue());
		}
		if (v instanceof Integer) {
			return toString(((Integer)v).intValue());
		}
		if (v instanceof Long) {
			return toString(((Long)v).longValue());
		}
		if (v instanceof Float) {
			return toString(((Float)v).floatValue());
		}
		if (v instanceof Double) {
			return toString(((Double)v).doubleValue());
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

		StringBuilder string = new StringBuilder(3 * a.length);
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

		StringBuilder string = new StringBuilder(4 * a.length);
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

		StringBuilder string = new StringBuilder(4 * a.length);
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

		StringBuilder string = new StringBuilder(4 * a.length);
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

		StringBuilder string = new StringBuilder(4 * a.length);
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

		StringBuilder string = new StringBuilder(4 * a.length);
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

		StringBuilder string = new StringBuilder(4 * a.length);
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

		StringBuilder string = new StringBuilder(4 * c.size());
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

		StringBuilder string = new StringBuilder(6 * m.size());
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

	public static void toString(StringBuilder builder, boolean v) {
		builder.append(v ? STRING_TRUE : STRING_FALSE);
	}

	public static void toString(StringBuilder builder, byte v) {
		int i = v & 0xFF;
		builder.ensureCapacity(builder.capacity() + 2);
		builder.append(DIGITS[i >>> 4]).append(DIGITS[i & 0x0F]);
	}

	public static void toString(StringBuilder builder, char v) {
		builder.append(v);
	}

	public static void toString(StringBuilder builder, short v) {
		builder.append(v);
	}

	public static void toString(StringBuilder builder, int v) {
		builder.append(v);
	}

	public static void toString(StringBuilder builder, long v) {
		builder.append(v);
	}

	public static void toString(StringBuilder builder, float v) {
		builder.append(v);
	}

	public static void toString(StringBuilder builder, double v) {
		builder.append(v);
	}

	public static void toString(StringBuilder builder, Object v) {
		if (v == null) {
			builder.append(STRING_NULL);
		}
		else if (v instanceof Boolean) {
			toString(builder, ((Boolean)v).booleanValue());
		}
		else if (v instanceof Byte) {
			toString(builder, ((Byte)v).byteValue());
		}
		else if (v instanceof Character) {
			toString(builder, ((Character)v).charValue());
		}
		else if (v instanceof Short) {
			toString(builder, ((Short)v).shortValue());
		}
		else if (v instanceof Integer) {
			toString(builder, ((Integer)v).intValue());
		}
		else if (v instanceof Long) {
			toString(builder, ((Long)v).longValue());
		}
		else if (v instanceof Float) {
			toString(builder, ((Float)v).floatValue());
		}
		else if (v instanceof Double) {
			toString(builder, ((Double)v).doubleValue());
		}

		else if (v instanceof Collection) {
			toString(builder, (Collection)v);
		}

		else if (v instanceof Map) {
			toString(builder, (Map)v);
		}

		else if (v instanceof boolean[]) {
			toString(builder, (boolean[])v);
		}
		else if (v instanceof byte[]) {
			toString(builder, (byte[])v);
		}
		else if (v instanceof char[]) {
			toString(builder, (char[])v);
		}
		else if (v instanceof short[]) {
			toString(builder, (short[])v);
		}
		else if (v instanceof int[]) {
			toString(builder, (int[])v);
		}
		else if (v instanceof long[]) {
			toString(builder, (long[])v);
		}
		else if (v instanceof float[]) {
			toString(builder, (float[])v);
		}
		else if (v instanceof double[]) {
			toString(builder, (double[])v);
		}

		else if (v.getClass().isArray()) {
			toString(builder, (Object[])v);
		}

		else {
			builder.append(v.toString());
		}
	}

	public static void toString(StringBuilder builder, boolean[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 9 * a.length);
			builder.append('[');
			boolean sep = false;
			for (boolean v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				toString(builder, v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, byte[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (byte v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				int i = v & 0xFF;
				builder.append(DIGITS[i >>> 4]).append(DIGITS[i & 0x0F]);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, char[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 3 * a.length);
			builder.append('[');
			boolean sep = false;
			for (char v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				builder.append(v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, short[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (short v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				builder.append(v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, int[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (int v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				builder.append(v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, long[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (long v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				builder.append(v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, float[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (float v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				builder.append(v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, double[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (double v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				builder.append(v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, Object[] a) {
		if (a == null) {
			builder.append(STRING_NULL);
		}
		else if (a.length == 0) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * a.length);
			builder.append('[');
			boolean sep = false;
			for (Object v : a) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				toString(builder, v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, Collection<?> c) {
		if (c == null) {
			builder.append(STRING_NULL);
		}
		else if (c.isEmpty()) {
			builder.append(STRING_EMPTY_LIST);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 4 * c.size());
			builder.append('[');
			boolean sep = false;
			for (Object v : c) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				toString(builder, v);
			}

			builder.append(']');
		}
	}

	public static void toString(StringBuilder builder, Map<?, ?> m) {
		if (m == null) {
			builder.append(STRING_NULL);
		}
		else if (m.isEmpty()) {
			builder.append(STRING_EMPTY_MAP);
		}
		else {
			builder.ensureCapacity(builder.capacity() + 6 * m.size());
			builder.append('{');
			boolean sep = false;
			for (Map.Entry<?, ?> e : m.entrySet()) {
				if (sep) {
					builder.append(STRING_SEQUENCE_SEPARATOR);
				}
				else {
					sep = true;
				}
				toString(builder, e.getKey());
				builder.append(STRING_KEY_VALUE_SEPARATOR);
				toString(builder, e.getValue());
			}
			builder.append('}');
		}
	}
}
