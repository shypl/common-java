package org.shypl.common.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class TimeUtils {
	public static long currentSeconds() {
		return System.currentTimeMillis() / 1000;
	}

	public static long currentSecondsPlus(final long seconds) {
		return currentSeconds() + seconds;
	}

	public static Timestamp currentTimestampPlus(final long seconds) {
		return new Timestamp(System.currentTimeMillis() + seconds * 1000);
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp defineTimestamp(final long seconds) {
		return new Timestamp(seconds * 1000);
	}

	public static long currentMidnightSeconds() {
		final Calendar date = new GregorianCalendar();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date.getTimeInMillis() / 1000;
	}
}
