package org.shypl.common.slf4j;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class PrefixedLoggerProxy implements Logger {

	private final Logger logger;
	private final String prefix;

	public PrefixedLoggerProxy(Logger logger, String Prefix) {
		this.logger = logger;
		prefix = Prefix;
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String msg) {
		logger.trace(prepareMessage(msg));
	}

	@Override
	public void trace(String format, Object arg) {
		logger.trace(prepareMessage(format), arg);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		logger.trace(prepareMessage(format), arg1, arg2);
	}

	@Override
	public void trace(String format, Object... arguments) {
		logger.trace(prepareMessage(format), arguments);
	}

	@Override
	public void trace(String msg, Throwable t) {
		logger.trace(prepareMessage(msg), t);
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return logger.isTraceEnabled(marker);
	}

	@Override
	public void trace(Marker marker, String msg) {
		logger.trace(marker, prepareMessage(msg));
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		logger.trace(marker, prepareMessage(format), arg);
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		logger.trace(marker, prepareMessage(format), arg1, arg2);
	}

	@Override
	public void trace(Marker marker, String format, Object... arguments) {
		logger.trace(marker, prepareMessage(format), arguments);
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		logger.trace(marker, prepareMessage(msg), t);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		logger.debug(prepareMessage(msg));
	}

	@Override
	public void debug(String format, Object arg) {
		logger.debug(prepareMessage(format), arg);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		logger.debug(prepareMessage(format), arg1, arg2);
	}

	@Override
	public void debug(String format, Object... arguments) {
		logger.debug(prepareMessage(format), arguments);
	}

	@Override
	public void debug(String msg, Throwable t) {
		logger.debug(prepareMessage(msg), t);
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return logger.isDebugEnabled(marker);
	}

	@Override
	public void debug(Marker marker, String msg) {
		logger.debug(marker, prepareMessage(msg));
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		logger.debug(marker, prepareMessage(format), arg);
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		logger.debug(marker, prepareMessage(format), arg1, arg2);
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		logger.debug(marker, prepareMessage(format), arguments);
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		logger.debug(marker, prepareMessage(msg), t);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		logger.info(prepareMessage(msg));
	}

	@Override
	public void info(String format, Object arg) {
		logger.info(prepareMessage(format), arg);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		logger.info(prepareMessage(format), arg1, arg2);
	}

	@Override
	public void info(String format, Object... arguments) {
		logger.info(prepareMessage(format), arguments);
	}

	@Override
	public void info(String msg, Throwable t) {
		logger.info(prepareMessage(msg), t);
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return logger.isInfoEnabled(marker);
	}

	@Override
	public void info(Marker marker, String msg) {
		logger.info(marker, prepareMessage(msg));
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		logger.info(marker, prepareMessage(format), arg);
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		logger.info(marker, prepareMessage(format), arg1, arg2);
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		logger.info(marker, prepareMessage(format), arguments);
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		logger.info(marker, prepareMessage(msg), t);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String msg) {
		logger.warn(prepareMessage(msg));
	}

	@Override
	public void warn(String format, Object arg) {
		logger.warn(prepareMessage(format), arg);
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		logger.warn(prepareMessage(format), arg1, arg2);
	}

	@Override
	public void warn(String format, Object... arguments) {
		logger.warn(prepareMessage(format), arguments);
	}

	@Override
	public void warn(String msg, Throwable t) {
		logger.warn(prepareMessage(msg), t);
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return logger.isWarnEnabled(marker);
	}

	@Override
	public void warn(Marker marker, String msg) {
		logger.warn(marker, prepareMessage(msg));
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		logger.warn(marker, prepareMessage(format), arg);
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		logger.warn(marker, prepareMessage(format), arg1, arg2);
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		logger.warn(marker, prepareMessage(format), arguments);
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		logger.warn(marker, prepareMessage(msg), t);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void error(String msg) {
		logger.error(prepareMessage(msg));
	}

	@Override
	public void error(String format, Object arg) {
		logger.error(prepareMessage(format), arg);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		logger.error(prepareMessage(format), arg1, arg2);
	}

	@Override
	public void error(String format, Object... arguments) {
		logger.error(prepareMessage(format), arguments);
	}

	@Override
	public void error(String msg, Throwable t) {
		logger.error(prepareMessage(msg), t);
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return logger.isErrorEnabled(marker);
	}

	@Override
	public void error(Marker marker, String msg) {
		logger.error(marker, prepareMessage(msg));
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		logger.error(marker, prepareMessage(format), arg);
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		logger.error(marker, prepareMessage(format), arg1, arg2);
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		logger.error(marker, prepareMessage(format), arguments);
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		logger.error(marker, prepareMessage(msg), t);
	}

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public boolean equals(Object o) {
		return logger.equals(o);
	}

	@Override
	public int hashCode() {
		return logger.hashCode();
	}

	@Override
	public String toString() {
		return logger.toString();
	}

	private String prepareMessage(String message) {
		return prefix + message;
	}
}
