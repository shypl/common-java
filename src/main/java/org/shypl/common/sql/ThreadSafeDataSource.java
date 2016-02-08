package org.shypl.common.sql;

import org.shypl.common.util.CollectionUtils;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ThreadSafeDataSource implements DataSource, AutoCloseable {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ThreadSafeDataSource.class);

	private final DataSource source;
	private final Map<Long, ThreadSafeConnection> connections = new ConcurrentHashMap<>(Runtime.getRuntime().availableProcessors());

	public ThreadSafeDataSource(DataSource source) {
		this.source = source;
	}

	@Override
	public Connection getConnection() throws SQLException {
		final long id = Thread.currentThread().getId();
		ThreadSafeConnection connection = connections.get(id);
		if (connection == null) {
			connection = CollectionUtils.putIfAbsentAndGet(connections, id, new ThreadSafeConnection(source));
			LOGGER.trace("Allocate new connection (total: {})", connections.size());
		}
		connection.open();
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return source.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		source.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		source.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return source.getLoginTimeout();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return source.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return source.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return source.isWrapperFor(iface);
	}

	@Override
	public void close() throws Exception {
		LOGGER.trace("Close source");
		if (source instanceof AutoCloseable) {
			((AutoCloseable)source).close();
		}
	}
}
