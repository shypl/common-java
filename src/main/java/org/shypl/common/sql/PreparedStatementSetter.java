package org.shypl.common.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter<V> {
	void set(PreparedStatement statement, int index, V value) throws SQLException;
}
