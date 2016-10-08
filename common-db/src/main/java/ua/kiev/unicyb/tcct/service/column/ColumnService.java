package ua.kiev.unicyb.tcct.service.column;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;

/**
 * @Author Denys Storozhenko.
 */
public interface ColumnService {
	void addColumn(String dbName, String tableName, Column column);

	void updateColumn(String dbName, String tableName, Column column);

	Iterable<SupportedType> getAllSupportedTypes();
}