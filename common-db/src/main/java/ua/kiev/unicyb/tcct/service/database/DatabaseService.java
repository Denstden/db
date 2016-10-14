package ua.kiev.unicyb.tcct.service.database;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
public interface DatabaseService {
	Database create(Database database);

	Database findByName(String databaseName);

	void changeName(String oldName, String newName);

	void update(Database database);

	void drop(String name);

	Iterable<Database> findAll();

	void toFile(String fileName);

	void loadFromFile(String fileName);

	Iterable<Table> findAllTables(String databaseName);

	Column getColumnByName(String dbName, String tableName, String columnName);
}
