package ua.kiev.unicyb.tcct.service.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EntityType;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.exception.IdColumnExistsException;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.exception.NotFoundException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Service
public class ColumnServiceImpl implements ColumnService {
	@Autowired
	private DatabaseService databaseService;

	@Override
	public void addColumn(String dbName, String tableName, Column column) {
		Database database = databaseService.findByName(dbName);
		database.getTables().stream().filter(table -> table.getTableName().equals(tableName)).forEach(table -> {
			Set<Column> tableColumns = table.getColumns();
			if (tableColumns.isEmpty()) {
				addColumnToEmptyTable(tableName, column, tableColumns);
			} else {
				addColumnToNotEmptyTable(tableName, column, tableColumns);
			}
		});
		databaseService.update(database);
	}

	@Override
	public void updateColumn(String dbName, String tableName, Column column) {
		Database database = databaseService.findByName(dbName);
		boolean wasFinded = false;
		label:
		for (Table table : database.getTables()) {
			if (table.getTableName().equals(tableName)) {
				for (Column col : table.getColumns()) {
					if (col.getColumnName().equals(column.getColumnName())) {
						wasFinded = true;
						col.setDefaultValue(column.getDefaultValue());
						col.setNullable(column.isNullable());
						col.setType(column.getType());
						break label;
					}
				}
			}
		}
		if (wasFinded) {
			databaseService.update(database);
		}
		throw new NotFoundException(EntityType.COLUMN, column.getColumnName());
	}

	@Override
	public Iterable<SupportedType> getAllSupportedTypes() {
		return Arrays.asList(SupportedType.values());
	}

	private void addColumnToEmptyTable(String tableName, Column column, Set<Column> tableColumns) {
		if (column.isId()) {
			tableColumns.add(column);
		} else {
			throw new IdColumnNotFoundException(tableName);
		}
	}

	private void addColumnToNotEmptyTable(String tableName, Column column, Set<Column> tableColumns) {
		if (!column.isId()) {
			if (!tableColumns.contains(column)) {
				tableColumns.add(column);
			} else {
				throw new ExistsException(EntityType.COLUMN, column.getColumnName());
			}
		} else {
			throw new IdColumnExistsException(tableName);
		}
	}
}
