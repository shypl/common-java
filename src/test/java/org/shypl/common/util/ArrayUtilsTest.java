package org.shypl.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

	@Test
	public void testSum() throws Exception {
		int[] array = {1, 2, 3};
		int result = ArrayUtils.sum(array);

		assertEquals(result, 1 + 2 + 3);
	}

	@Test
	public void testSumOverflow() throws Exception {
		int[] array = {Integer.MAX_VALUE, 2, 3};
		int result = ArrayUtils.sum(array);

		//noinspection NumericOverflow
		assertEquals(result, Integer.MAX_VALUE + 2 + 3);
	}

	@Test
	public void testSum1() throws Exception {
		long[] array = {1, 2, 3};
		long result = ArrayUtils.sum(array);

		assertEquals(result, 1 + 2 + 3);
	}

	@Test
	public void testSum1Overflow() throws Exception {
		long[] array = {Long.MAX_VALUE, 2, 3};
		long result = ArrayUtils.sum(array);

		//noinspection NumericOverflow
		assertEquals(result, Long.MAX_VALUE + 2 + 3);
	}

	@Test
	public void testSumLong() throws Exception {
		int[] array = {Integer.MAX_VALUE, 2, 3};
		long result = ArrayUtils.sumLong(array);

		assertEquals(result, ((long)Integer.MAX_VALUE) + 2 + 3);
	}
}