package org.shypl.common.sql;

import java.sql.SQLException;

public interface RecordValueHolder {
	boolean isChanged();

	void save(AddablePreparedStatement st) throws SQLException;
}
