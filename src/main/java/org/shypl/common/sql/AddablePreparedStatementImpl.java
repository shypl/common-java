package org.shypl.common.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

class AddablePreparedStatementImpl implements AddablePreparedStatement {

	private final PreparedStatement source;
	private       int               index;

	public AddablePreparedStatementImpl(PreparedStatement source) {
		this.source = source;
	}

	@Override
	public void addNull(int sqlType) throws SQLException {
		source.setNull(++index, sqlType);
	}

	@Override
	public void addBoolean(boolean x) throws SQLException {
		source.setBoolean(++index, x);
	}

	@Override
	public void addByte(byte x) throws SQLException {
		source.setByte(++index, x);
	}

	@Override
	public void addShort(short x) throws SQLException {
		source.setShort(++index, x);
	}

	@Override
	public void addInt(int x) throws SQLException {
		source.setInt(++index, x);
	}

	@Override
	public void addLong(long x) throws SQLException {
		source.setLong(++index, x);
	}

	@Override
	public void addFloat(float x) throws SQLException {
		source.setFloat(++index, x);
	}

	@Override
	public void addDouble(double x) throws SQLException {
		source.setDouble(++index, x);
	}

	@Override
	public void addBigDecimal(BigDecimal x) throws SQLException {
		source.setBigDecimal(++index, x);
	}

	@Override
	public void addString(String x) throws SQLException {
		source.setString(++index, x);
	}

	@Override
	public void addBytes(byte[] x) throws SQLException {
		source.setBytes(++index, x);
	}

	@Override
	public void addDate(Date x) throws SQLException {
		source.setDate(++index, x);
	}

	@Override
	public void addTime(Time x) throws SQLException {
		source.setTime(++index, x);
	}

	@Override
	public void addTimestamp(Timestamp x) throws SQLException {
		source.setTimestamp(++index, x);
	}

	@Override
	public void addAsciiStream(InputStream x, int length) throws SQLException {
		source.setAsciiStream(++index, x, length);
	}

	@Override
	public void addBinaryStream(InputStream x, int length) throws SQLException {
		source.setBinaryStream(++index, x, length);
	}

	@Override
	public void addObject(Object x, int targetSqlType) throws SQLException {
		source.setObject(++index, x, targetSqlType);
	}

	@Override
	public void addObject(Object x) throws SQLException {
		source.setObject(++index, x);
	}

	@Override
	public void addCharacterStream(Reader reader, int length) throws SQLException {
		source.setCharacterStream(++index, reader, length);
	}

	@Override
	public void addRef(Ref x) throws SQLException {
		source.setRef(++index, x);
	}

	@Override
	public void addBlob(Blob x) throws SQLException {
		source.setBlob(++index, x);
	}

	@Override
	public void addClob(Clob x) throws SQLException {
		source.setClob(++index, x);
	}

	@Override
	public void addArray(Array x) throws SQLException {
		source.setArray(++index, x);
	}

	@Override
	public void addDate(Date x, Calendar cal) throws SQLException {
		source.setDate(++index, x, cal);
	}

	@Override
	public void addTime(Time x, Calendar cal) throws SQLException {
		source.setTime(++index, x, cal);
	}

	@Override
	public void addTimestamp(Timestamp x, Calendar cal) throws SQLException {
		source.setTimestamp(++index, x, cal);
	}

	@Override
	public void addNull(int sqlType, String typeName) throws SQLException {
		source.setNull(++index, sqlType, typeName);
	}

	@Override
	public void addURL(URL x) throws SQLException {
		source.setURL(++index, x);
	}

	@Override
	public void addRowId(RowId x) throws SQLException {
		source.setRowId(++index, x);
	}

	@Override
	public void addNString(String value) throws SQLException {
		source.setNString(++index, value);
	}

	@Override
	public void addNCharacterStream(Reader value, long length) throws SQLException {
		source.setNCharacterStream(++index, value, length);
	}

	@Override
	public void addNClob(NClob value) throws SQLException {
		source.setNClob(++index, value);
	}

	@Override
	public void addClob(Reader reader, long length) throws SQLException {
		source.setClob(++index, reader, length);
	}

	@Override
	public void addBlob(InputStream inputStream, long length) throws SQLException {
		source.setBlob(++index, inputStream, length);
	}

	@Override
	public void addNClob(Reader reader, long length) throws SQLException {
		source.setNClob(++index, reader, length);
	}

	@Override
	public void addSQLXML(SQLXML xmlObject) throws SQLException {
		source.setSQLXML(++index, xmlObject);
	}

	@Override
	public void addObject(Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		source.setObject(++index, x, targetSqlType, scaleOrLength);
	}

	@Override
	public void addAsciiStream(InputStream x, long length) throws SQLException {
		source.setAsciiStream(++index, x, length);
	}

	@Override
	public void addBinaryStream(InputStream x, long length) throws SQLException {
		source.setBinaryStream(++index, x, length);
	}

	@Override
	public void addCharacterStream(Reader reader, long length) throws SQLException {
		source.setCharacterStream(++index, reader, length);
	}

	@Override
	public void addAsciiStream(InputStream x) throws SQLException {
		source.setAsciiStream(++index, x);
	}

	@Override
	public void addBinaryStream(InputStream x) throws SQLException {
		source.setBinaryStream(++index, x);
	}

	@Override
	public void addCharacterStream(Reader reader) throws SQLException {
		source.setCharacterStream(++index, reader);
	}

	@Override
	public void addNCharacterStream(Reader value) throws SQLException {
		source.setNCharacterStream(++index, value);
	}

	@Override
	public void addClob(Reader reader) throws SQLException {
		source.setClob(++index, reader);
	}

	@Override
	public void addBlob(InputStream inputStream) throws SQLException {
		source.setBlob(++index, inputStream);
	}

	@Override
	public void addNClob(Reader reader) throws SQLException {
		source.setNClob(++index, reader);
	}

	@Override
	public void addObject(Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
		source.setObject(++index, x, targetSqlType, scaleOrLength);
	}

	@Override
	public void addObject(Object x, SQLType targetSqlType) throws SQLException {
		source.setObject(++index, x, targetSqlType);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		source.setArray(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		source.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		source.setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		source.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		source.setBlob(parameterIndex, x);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		source.setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		source.setByte(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		source.setBytes(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		source.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		source.setClob(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		source.setDate(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		source.setDate(parameterIndex, x, cal);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		source.setDouble(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		source.setFloat(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		source.setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		source.setLong(parameterIndex, x);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return source.getMetaData();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		source.setNull(parameterIndex, sqlType);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		source.setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		source.setObject(parameterIndex, x);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		source.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
		source.setObject(parameterIndex, x, targetSqlType, scale);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		source.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		source.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		source.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		source.setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		source.setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		source.setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		source.setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		source.setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		source.setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		source.setNClob(parameterIndex, reader);
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return source.getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		source.setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		source.setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		source.setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		source.setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		source.setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		source.setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		source.setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		source.setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		source.setRef(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		source.setShort(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		source.setString(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		source.setTime(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		source.setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		source.setTimestamp(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		source.setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		source.setURL(parameterIndex, x);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		source.setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void addBatch() throws SQLException {
		source.addBatch();
	}

	@Override
	public void clearParameters() throws SQLException {
		index = 0;
		source.clearParameters();
	}

	@Override
	public boolean execute() throws SQLException {
		return source.execute();
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return source.executeQuery();
	}

	@Override
	public int executeUpdate() throws SQLException {
		return source.executeUpdate();
	}

	@Override
	public String toString() {
		return source.toString();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return source.getConnection();
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		source.setCursorName(name);
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		source.setEscapeProcessing(enable);
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		source.setFetchDirection(direction);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return source.getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		source.setFetchSize(rows);
	}

	@Override
	public int getFetchSize() throws SQLException {
		return source.getFetchSize();
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return source.getGeneratedKeys();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		source.setMaxFieldSize(max);
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return source.getMaxFieldSize();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		source.setMaxRows(max);
	}

	@Override
	public int getMaxRows() throws SQLException {
		return source.getMaxRows();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return source.getMoreResults();
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return source.getMoreResults(current);
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		source.setQueryTimeout(seconds);
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return source.getQueryTimeout();
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return source.getResultSet();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return source.getResultSetConcurrency();
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return source.getResultSetHoldability();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return source.isClosed();
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		source.setPoolable(poolable);
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return source.isPoolable();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		source.closeOnCompletion();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return source.isCloseOnCompletion();
	}

	@Override
	public int getResultSetType() throws SQLException {
		return source.getResultSetType();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return source.getUpdateCount();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return source.getWarnings();
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		source.addBatch(sql);
	}

	@Override
	public void cancel() throws SQLException {
		source.cancel();
	}

	@Override
	public void clearBatch() throws SQLException {
		source.clearBatch();
	}

	@Override
	public void clearWarnings() throws SQLException {
		source.clearWarnings();
	}

	@Override
	public void close() throws SQLException {
		source.close();
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return source.execute(sql, autoGeneratedKeys);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return source.execute(sql, columnIndexes);
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return source.execute(sql, columnNames);
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		return source.execute(sql);
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return source.executeBatch();
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return source.executeQuery(sql);
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return source.executeUpdate(sql, autoGeneratedKeys);
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return source.executeUpdate(sql, columnIndexes);
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return source.executeUpdate(sql, columnNames);
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return source.executeUpdate(sql);
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return source.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return source.isWrapperFor(iface);
	}
}
