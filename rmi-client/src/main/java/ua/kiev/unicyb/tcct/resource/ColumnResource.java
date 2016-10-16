package ua.kiev.unicyb.tcct.resource;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;

/**
 * @Author Denys Storozhenko.
 */
public interface ColumnResource {
	void createColumn(String dbName, String tableName, String columnName, Boolean isId, Boolean isNullable,
			SupportedType type);

	Iterable<Column> getAllColumns(String dbName, String tableName);

	String[] columnNames(String dbName, String tableName);
}
