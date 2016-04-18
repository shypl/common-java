package org.shypl.common.sql;

import java.sql.SQLException;

public class SimpleRecordUpdateCondition implements RecordUpdateCondition {

	public static RecordUpdateCondition factoryLong(String filed, long value) {
		return new SimpleRecordUpdateCondition(filed, st -> st.addLong(value));
	}

	private final String                         filed;
	private final AddablePreparedStatementFiller filler;

	public SimpleRecordUpdateCondition(String filed, AddablePreparedStatementFiller filler) {
		this.filed = filed;
		this.filler = filler;
	}

	@Override
	public void buildSql(StringBuilder sql) {
		sql.append('`').append(filed).append("` = ?");
	}

	@Override
	public void buildStatement(AddablePreparedStatement st) throws SQLException {
		filler.fill(st);
	}
}
