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
		return decimalFormat(false);
	}
	
	
	public static DecimalFormat decimalFormat(boolean showSign) {
		return decimalFormat(true, showSign);
	}
	
	public static DecimalFormat decimalFormat(boolean useGroupingSeparator, boolean showSign) {
		DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		
		if (useGroupingSeparator) {
			symbols.setGroupingSeparator(' ');
		} else {
			formatter.setGroupingUsed(false);
		}
		
		formatter.setDecimalFormatSymbols(symbols);
		
		if (showSign) {
			formatter.setPositivePrefix("+");
		}
		
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
