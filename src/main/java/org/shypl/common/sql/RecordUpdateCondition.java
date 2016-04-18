package org.shypl.common.sql;

import java.sql.SQLException;

public interface RecordUpdateCondition {
	void buildSql(StringBuilder sql);

	void buildStatement(AddablePreparedStatement st) throws SQLException;
}
