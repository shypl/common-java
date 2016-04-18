package org.shypl.common.sql;

import java.sql.SQLException;

@FunctionalInterface
public interface AddablePreparedStatementFiller {
	void fill(AddablePreparedStatement st) throws SQLException;
}
