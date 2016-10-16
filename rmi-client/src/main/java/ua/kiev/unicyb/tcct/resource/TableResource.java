package ua.kiev.unicyb.tcct.resource;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
public interface TableResource {
	void createTable(String dbName, String tableName);

	Iterable<Table> getAllTables(String dbName);

	String[] tableNames(String dbName);

	Table intersect(String db1Name, String db2Name, String table1Name, String table2Name);
}
