package org.shypl.common.util;

import org.junit.Assert;
import org.junit.Test;

public class ByteUtilsTest {

	@Test
	public void testWriteInt() throws Exception {
		byte[] bytes = new byte[5];
		byte[] actual = {(byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, 0};

		ByteUtils.writeInt(bytes, Integer.MAX_VALUE);

		Assert.assertArrayEquals(bytes, actual);
	}

	@Test
	public void testWriteInt1() throws Exception {
		byte[] bytes = new byte[5];
		byte[] actual = {0, (byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF};

		ByteUtils.writeInt(bytes, Integer.MAX_VALUE, 1);

		Assert.assertArrayEquals(bytes, actual);
	}

	@Test
	public void testWriteLong() throws Exception {
		byte[] bytes = new byte[9];
		byte[] actual = {(byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 0};

		ByteUtils.writeLong(bytes, Long.MAX_VALUE);

		Assert.assertArrayEquals(bytes, actual);
	}

	@Test
	public void testWriteLong1() throws Exception {
		byte[] bytes = new byte[9];
		byte[] actual = {0, (byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};

		ByteUtils.writeLong(bytes, Long.MAX_VALUE, 1);

		Assert.assertArrayEquals(bytes, actual);
	}

	@Test
	public void testReadInt() throws Exception {
		byte[] bytes = {(byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, 0};

		int result = ByteUtils.readInt(bytes);

		Assert.assertEquals(result, Integer.MAX_VALUE);
	}

	@Test
	public void testReadInt1() throws Exception {
		byte[] bytes = {0, (byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF};

		int result = ByteUtils.readInt(bytes, 1);

		Assert.assertEquals(result, Integer.MAX_VALUE);
	}

	@Test
	public void testReadLong() throws Exception {
		byte[] bytes = {(byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, 0};

		long result = ByteUtils.readLong(bytes);

		Assert.assertEquals(result, Long.MAX_VALUE);
	}

	@Test
	public void testReadLong1() throws Exception {
		byte[] bytes = {0, (byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};

		long result = ByteUtils.readLong(bytes, 1);

		Assert.assertEquals(result, Long.MAX_VALUE);
	}

	@Test
	public void testGetIntBytes() throws Exception {
		byte[] actual = {(byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF};

		byte[] result = ByteUtils.getIntBytes(Integer.MAX_VALUE);

		Assert.assertArrayEquals(result, actual);
	}

	@Test
	public void testGetLongBytes() throws Exception {
		byte[] actual = {(byte)0x7F, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};

		byte[] result = ByteUtils.getLongBytes(Long.MAX_VALUE);

		Assert.assertArrayEquals(result, actual);
	}
}