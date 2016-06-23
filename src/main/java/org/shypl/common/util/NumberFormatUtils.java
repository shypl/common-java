package org.shypl.common.util;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public final class NumberFormatUtils {

	private NumberFormatUtils() {

	}

	public static DecimalFormat decimalFormat() {

		DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		symbols.setGroupingSeparator(' ');
		formatter.setDecimalFormatSymbols(symbols);
		return formatter;
	}

	public static DecimalFormat percentFormat() {
		DecimalFormat format = decimalFormat();
		format.setPositiveSuffix("%");
		format.setNegativeSuffix("%");
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		return format;
	}
}
