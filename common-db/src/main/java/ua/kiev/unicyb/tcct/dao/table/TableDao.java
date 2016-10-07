package ua.kiev.unicyb.tcct.dao.table;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
public interface TableDao {
	void create(Table table);
	Table read(String tableName);
	void update(Table table);
	void changeName(String oldName, String newName);
	void delete(String tableName);
}
