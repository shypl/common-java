package org.shypl.common.util;

public final class ByteUtils {

	public static void writeInt(byte[] bytes, int value) {
		writeInt(bytes, value, 0);
	}

	public static void writeInt(byte[] bytes, int value, int offset) {
		bytes[offset] = (byte)(value >>> 24);
		bytes[offset + 1] = (byte)(value >>> 16);
		bytes[offset + 2] = (byte)(value >>> 8);
		bytes[offset + 3] = (byte)value;
	}

	public static void writeLong(byte[] bytes, long value) {
		writeLong(bytes, value, 0);
	}

	public static void writeLong(byte[] bytes, long value, int offset) {
		bytes[offset] = (byte)(value >>> 56);
		bytes[offset + 1] = (byte)(value >>> 48);
		bytes[offset + 2] = (byte)(value >>> 40);
		bytes[offset + 3] = (byte)(value >>> 32);
		bytes[offset + 4] = (byte)(value >>> 24);
		bytes[offset + 5] = (byte)(value >>> 16);
		bytes[offset + 6] = (byte)(value >>> 8);
		bytes[offset + 7] = (byte)value;
	}

	public static int readInt(byte[] bytes) {
		return readInt(bytes, 0);
	}

	public static int readInt(byte[] bytes, int offset) {
		return (((bytes[offset] & 0xFF) << 24)
			+ ((bytes[offset + 1] & 0xFF) << 16)
			+ ((bytes[offset + 2] & 0xFF) << 8)
			+ (bytes[offset + 3] & 0xFF));
	}

	public static long readLong(byte[] bytes) {
		return readLong(bytes, 0);
	}

	public static long readLong(byte[] bytes, int offset) {
		return (((long)bytes[offset] << 56) +
			((long)(bytes[offset + 1] & 0xFF) << 48) +
			((long)(bytes[offset + 2] & 0xFF) << 40) +
			((long)(bytes[offset + 3] & 0xFF) << 32) +
			((long)(bytes[offset + 4] & 0xFF) << 24) +
			((bytes[offset + 5] & 0xFF) << 16) +
			((bytes[offset + 6] & 0xFF) << 8) +
			((bytes[offset + 7] & 0xFF)));
	}

	public static byte[] getIntBytes(int value) {
		byte[] bytes = new byte[4];
		writeInt(bytes, value, 0);
		return bytes;
	}

	public static byte[] getLongBytes(long value) {
		byte[] bytes = new byte[8];
		writeLong(bytes, value, 0);
		return bytes;
	}
}
