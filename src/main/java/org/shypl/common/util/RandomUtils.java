package org.shypl.common.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtils {
	public static int getInt() {
		return ThreadLocalRandom.current().nextInt();
	}

	public static int getInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public static int getInt(int min, int max) {
		return getInt(max + 1 - min) + min;
	}

	public static int getIndex(int[] weights) {
		return getIndex(weights, ArrayUtils.sum(weights));
	}

	public static int getIndex(int[] weights, int weightsSum) {
		int rnd = getInt(weightsSum);
		int i = 0;
		weightsSum = 0;

		for (; i < weights.length; ++i) {
			if (weights[i] != 0) {
				weightsSum += weights[i];
				if (weightsSum >= rnd) {
					return i;
				}
			}
		}

		return i;
	}

	public static long getLong() {
		return ThreadLocalRandom.current().nextLong();
	}

	public static boolean getBoolean() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	public static boolean getElement(boolean[] array) {
		return array[getInt(array.length)];
	}

	public static byte getElement(byte[] array) {
		return array[getInt(array.length)];
	}

	public static char getElement(char[] array) {
		return array[getInt(array.length)];
	}

	public static short getElement(short[] array) {
		return array[getInt(array.length)];
	}

	public static int getElement(int[] array) {
		return array[getInt(array.length)];
	}

	public static long getElement(long[] array) {
		return array[getInt(array.length)];
	}

	public static float getElement(float[] array) {
		return array[getInt(array.length)];
	}

	public static double getElement(double[] array) {
		return array[getInt(array.length)];
	}

	public static <E> E getElement(E[] array) {
		return array[getInt(array.length)];
	}

	public static <E> E getElement(List<E> list) {
		return list.get(getInt(list.size()));
	}
}
