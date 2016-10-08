package ua.kiev.unicyb.tcct.service.table;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
public interface TableService {
	Table findTableByName(String databaseName, String tableName);

	void addTable(String databaseName, Table table);

	void removeTable(String databaseName, String tableName);

	Iterable<Table> findAllTables(String databaseName);
}
