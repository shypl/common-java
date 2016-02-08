package org.shypl.common.util;

import java.util.List;
import java.util.Random;

public final class RandomUtils {
	private static final Random RANDOM = new Random();

	public static int getInt() {
		return RANDOM.nextInt();
	}

	public static int getInt(int bound) {
		return RANDOM.nextInt(bound);
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
			weightsSum += weights[i];
			if (weightsSum >= rnd) {
				return i;
			}
		}

		return i;
	}

	public static long getLong() {
		return RANDOM.nextLong();
	}

	public static boolean getBoolean() {
		return RANDOM.nextBoolean();
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
