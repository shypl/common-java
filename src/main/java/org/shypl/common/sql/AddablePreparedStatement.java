package org.shypl.common.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public interface AddablePreparedStatement extends PreparedStatement {
	static AddablePreparedStatement wrap(PreparedStatement statement) {
		return new AddablePreparedStatementImpl(statement);
	}

	/**
	 * @see PreparedStatement#setNull
	 */
	void addNull(int sqlType) throws SQLException;

	/**
	 * @see PreparedStatement#setBoolean
	 */
	void addBoolean(boolean x) throws SQLException;

	/**
	 * @see PreparedStatement#setByte
	 */
	void addByte(byte x) throws SQLException;

	/**
	 * @see PreparedStatement#setShort
	 */
	void addShort(short x) throws SQLException;

	/**
	 * @see PreparedStatement#setInt
	 */
	void addInt(int x) throws SQLException;

	/**
	 * @see PreparedStatement#setLong
	 */
	void addLong(long x) throws SQLException;

	/**
	 * @see PreparedStatement#setFloat
	 */
	void addFloat(float x) throws SQLException;

	/**
	 * @see PreparedStatement#setDouble
	 */
	void addDouble(double x) throws SQLException;

	/**
	 * @see PreparedStatement#setBigDecimal
	 */
	void addBigDecimal(BigDecimal x) throws SQLException;

	/**
	 * @see PreparedStatement#setString
	 */
	void addString(String x) throws SQLException;

	/**
	 * @see PreparedStatement#setBytes
	 */
	void addBytes(byte[] x) throws SQLException;

	/**
	 * @see PreparedStatement#setDate
	 */
	void addDate(Date x) throws SQLException;

	/**
	 * @see PreparedStatement#setTime
	 */
	void addTime(Time x) throws SQLException;

	/**
	 * @see PreparedStatement#setTimestamp
	 */
	void addTimestamp(Timestamp x) throws SQLException;

	/**
	 * @see PreparedStatement#setAsciiStream
	 */
	void addAsciiStream(InputStream x, int length) throws SQLException;

	/**
	 * @see PreparedStatement#setBinaryStream
	 */
	void addBinaryStream(InputStream x, int length) throws SQLException;

	/**
	 * @see PreparedStatement#setObject
	 */
	void addObject(Object x, int targetSqlType) throws SQLException;

	/**
	 * @see PreparedStatement#setObject
	 */
	void addObject(Object x) throws SQLException;

	/**
	 * @see PreparedStatement#setCharacterStream
	 */
	void addCharacterStream(Reader reader, int length) throws SQLException;

	/**
	 * @see PreparedStatement#setRef
	 */
	void addRef(Ref x) throws SQLException;

	/**
	 * @see PreparedStatement#setBlob
	 */
	void addBlob(Blob x) throws SQLException;

	/**
	 * @see PreparedStatement#setClob
	 */
	void addClob(Clob x) throws SQLException;

	/**
	 * @see PreparedStatement#setArray
	 */
	void addArray(Array x) throws SQLException;

	/**
	 * @see PreparedStatement#setDate
	 */
	void addDate(Date x, Calendar cal) throws SQLException;

	/**
	 * @see PreparedStatement#setTime
	 */
	void addTime(Time x, Calendar cal) throws SQLException;

	/**
	 * @see PreparedStatement#setTimestamp
	 */
	void addTimestamp(Timestamp x, Calendar cal) throws SQLException;

	/**
	 * @see PreparedStatement#setNull
	 */
	void addNull(int sqlType, String typeName) throws SQLException;

	/**
	 * @see PreparedStatement#setURL
	 */
	void addURL(URL x) throws SQLException;

	/**
	 * @see PreparedStatement#setRowId
	 */
	void addRowId(RowId x) throws SQLException;

	/**
	 * @see PreparedStatement#setNString
	 */
	void addNString(String value) throws SQLException;

	/**
	 * @see PreparedStatement#setNCharacterStream
	 */
	void addNCharacterStream(Reader value, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setNClob
	 */
	void addNClob(NClob value) throws SQLException;

	/**
	 * @see PreparedStatement#setClob
	 */
	void addClob(Reader reader, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setBlob
	 */
	void addBlob(InputStream inputStream, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setNClob
	 */
	void addNClob(Reader reader, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setSQLXML
	 */
	void addSQLXML(SQLXML xmlObject) throws SQLException;

	/**
	 * @see PreparedStatement#setObject
	 */
	void addObject(Object x, int targetSqlType, int scaleOrLength) throws SQLException;

	/**
	 * @see PreparedStatement#setAsciiStream
	 */
	void addAsciiStream(InputStream x, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setBinaryStream
	 */
	void addBinaryStream(InputStream x, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setCharacterStream
	 */
	void addCharacterStream(Reader reader, long length) throws SQLException;

	/**
	 * @see PreparedStatement#setAsciiStream
	 */
	void addAsciiStream(InputStream x) throws SQLException;

	/**
	 * @see PreparedStatement#setBinaryStream
	 */
	void addBinaryStream(InputStream x) throws SQLException;

	/**
	 * @see PreparedStatement#setCharacterStream
	 */
	void addCharacterStream(Reader reader) throws SQLException;

	/**
	 * @see PreparedStatement#setNCharacterStream
	 */
	void addNCharacterStream(Reader value) throws SQLException;

	/**
	 * @see PreparedStatement#setClob
	 */
	void addClob(Reader reader) throws SQLException;

	/**
	 * @see PreparedStatement#setBlob
	 */
	void addBlob(InputStream inputStream) throws SQLException;

	/**
	 * @see PreparedStatement#setNClob
	 */
	void addNClob(Reader reader) throws SQLException;

	/**
	 * @see PreparedStatement#setObject
	 */
	void addObject(Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException;

	/**
	 * @see PreparedStatement#setObject
	 */
	void addObject(Object x, SQLType targetSqlType) throws SQLException;
}
