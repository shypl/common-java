package org.shypl.common.util;

public final class LangUtils {
	public static void close(AutoCloseable... objects) {
		for (AutoCloseable obj : objects) {
			if (obj != null) {
				try {
					obj.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
