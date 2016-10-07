package ua.kiev.unicyb.tcct.dao.table;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EntityType;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.exception.NotFoundException;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class TableDaoImplInMem implements TableDao {
	private List<Table> tables = new ArrayList<>();

	@Override
	public void create(Table table) {
		if (!tables.contains(table)) {
			tables.add(table);
		} else {
			throw new ExistsException(EntityType.TABLE, table.getTableName());
		}
	}

	@Override
	public Table read(String tableName) {
		for (Table table : tables) {
			if (table.getTableName() != null && table.getTableName().equals(tableName)) {
				return table;
			}
		}
		throw new NotFoundException(EntityType.TABLE, tableName);
	}

	@Override
	public void update(Table table) {
		if (table.getTableName() != null) {
			Table table1 = read(table.getTableName());
			table1.setColumns(table.getColumns());
			table1.setRecords(table.getRecords());
		} else {
			throw new NotFoundException(EntityType.TABLE, null);
		}
	}

	@Override
	public void changeName(String oldName, String newName) {
		Table table = read(oldName);
		table.setTableName(newName);
	}

	@Override
	public void delete(String tableName) {
		Table table = read(tableName);
		tables.remove(table);
	}
}
