package org.shypl.common.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtils {
	private CollectionUtils() {}

	public static <K, V> V putIfAbsentAndGet(Map<K, V> map, K key, V value) {
		V oldValue = map.putIfAbsent(key, value);
		if (oldValue == null) {
			return value;
		}
		return oldValue;
	}

	public static boolean[] toPrimitiveBoolean(Collection<Boolean> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_BOOLEAN_ARRAY;
		}
		boolean[] result = new boolean[collection.size()];
		int i = 0;
		for (Boolean v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static byte[] toPrimitiveByte(Collection<Byte> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}
		byte[] result = new byte[collection.size()];
		int i = 0;
		for (Byte v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static char[] toPrimitiveChar(Collection<Character> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_CHAR_ARRAY;
		}
		char[] result = new char[collection.size()];
		int i = 0;
		for (Character v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static short[] toPrimitiveShort(Collection<Short> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_SHORT_ARRAY;
		}
		short[] result = new short[collection.size()];
		int i = 0;
		for (Short v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static int[] toPrimitiveInt(Collection<Integer> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_INT_ARRAY;
		}
		int[] result = new int[collection.size()];
		int i = 0;
		for (Integer v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static long[] toPrimitiveLong(Collection<Long> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_LONG_ARRAY;
		}
		long[] result = new long[collection.size()];
		int i = 0;
		for (Long v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static float[] toPrimitiveFloat(Collection<Float> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_FLOAT_ARRAY;
		}
		float[] result = new float[collection.size()];
		int i = 0;
		for (Float v : collection) {
			result[i++] = v;
		}
		return result;
	}

	public static double[] toPrimitiveDouble(Collection<Double> collection) {
		if (collection == null) {
			return null;
		}
		if (collection.isEmpty()) {
			return ArrayUtils.EMPTY_DOUBLE_ARRAY;
		}
		double[] result = new double[collection.size()];
		int i = 0;
		for (Double v : collection) {
			result[i++] = v;
		}
		return result;
	}
}
