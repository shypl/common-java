package org.shypl.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static java.lang.Math.max;

public final class DeclinationUtils {

	public static final Declination YEARS  = new DeclinationImpl("год", "года", "лет");
	public static final Declination MONTHS = new DeclinationImpl("месяц", "месяца", "месяцев");
	public static final Declination DAYS   = new DeclinationImpl("день", "дня", "дней");

	private DeclinationUtils() {
	}

	public static <T extends Declination> String defineWordDeclinationRu(int number, T declination) {
		if (number % 100 > 10 && number % 100 < 15) {
			return declination.getWord5();
		}

		int last = Math.abs(number) % 10;

		if (last == 1) {
			return declination.getWord1();
		}

		if (last != 0 && last < 5) {
			return declination.getWord2();
		}

		return declination.getWord5();
	}

	public static String prettyPeriodFrom(LocalDateTime time) {
		Period period = Period.between(time.toLocalDate(), LocalDate.now());

		int years = period.getYears();
		period.minusYears(years);

		int months = period.getMonths();
		period.minusMonths(months);

		int days = max(period.getDays(), 1);

		StringBuilder sb = new StringBuilder();
		if (years > 0) {
			sb.append(years)
				.append(" ")
				.append(defineWordDeclinationRu(years, YEARS))
				.append(" ");
		}

		if (months > 0) {
			sb.append(months)
				.append(" ")
				.append(defineWordDeclinationRu(months, MONTHS))
				.append(" ");
		}

		if (days > 0) {
			sb.append(days)
				.append(" ")
				.append(defineWordDeclinationRu(months, DAYS));
		}

		return sb.toString();
	}

}
