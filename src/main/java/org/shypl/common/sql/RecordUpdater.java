package org.shypl.common.sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecordUpdater {
	private final Map<String, RecordValueHolder> fields = new LinkedHashMap<>();

	public RecordUpdater add(String field, RecordValueHolder valueHolder) {
		fields.put(field, valueHolder);
		return this;
	}

	public boolean update(DataSource dataSource, String table, RecordUpdateCondition condition) throws SQLException {
		try (Connection con = dataSource.getConnection()) {
			return update(con, table, condition);
		}
	}

	public boolean update(Connection con, String table, RecordUpdateCondition condition) throws SQLException {

		StringBuilder sql = new StringBuilder("UPDATE ")
			.append('`').append(table).append('`')
			.append(" SET ");

		boolean noUpdates = true;
		for (Map.Entry<String, RecordValueHolder> entry : fields.entrySet()) {
			if (entry.getValue().isChanged()) {
				if (noUpdates) {
					noUpdates = false;
				}
				else {
					sql.append(", ");
				}
				sql.append("`").append(entry.getKey()).append("` = ?");
			}
		}

		if (noUpdates) {
			return false;
		}

		sql.append(" WHERE ");
		condition.buildSql(sql);

		AddablePreparedStatement st = AddablePreparedStatement.wrap(con.prepareStatement(sql.toString()));

		for (RecordValueHolder holder : fields.values()) {
			if (holder.isChanged()) {
				holder.save(st);
			}
		}

		condition.buildStatement(st);

		return st.executeUpdate() > 0;
	}
}
