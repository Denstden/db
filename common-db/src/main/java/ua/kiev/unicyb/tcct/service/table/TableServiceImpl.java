package ua.kiev.unicyb.tcct.service.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EntityType;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.exception.TableNotFoundException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Service
public class TableServiceImpl implements TableService {
	@Autowired
	private DatabaseService databaseService;

	@Override
	public Table findTableByName(String databaseName, String tableName) {
		Database database = databaseService.findByName(databaseName);
		for (Table table : database.getTables()) {
			if (table.getTableName().equals(tableName)) {
				return table;
			}
		}
		throw new TableNotFoundException(tableName, databaseName);
	}

	@Override
	public void addTable(String databaseName, Table table) {
		Database database = databaseService.findByName(databaseName);
		if (!database.getTables().contains(table)) {
			database.getTables().add(table);
		} else {
			throw new ExistsException(EntityType.TABLE, table.getTableName());
		}
		databaseService.update(database);
	}

	@Override
	public void removeTable(String databaseName, String tableName) {
		Database database = databaseService.findByName(databaseName);
		Iterator<Table> tableIterator = database.getTables().iterator();
		while (tableIterator.hasNext()) {
			Table table = tableIterator.next();
			if (table.getTableName().equals(tableName)) {
				tableIterator.remove();
			}
		}
		databaseService.update(database);
	}

	/*@Override
	public void createTable(String name) {
		Table table = new Table();
		table.setTableName(name);
		tableDao.create(table);
	}

	@Override
	public Table getByName(String name) {
		return tableDao.read(name);
	}

	@Override
	public void addColumn(String tableName, Column column) {
		Table table = getByName(tableName);
		if (!table.getColumns().isEmpty() || column.isId()) {
			if (!hasColumn(table, column) && (table.getColumns().isEmpty() || !column.isId())) {
				table.getColumns().add(column);
				tableDao.update(table);
			} else {
				throw new ExistsException(EntityType.COLUMN, column.getColumnName());
			}
		} else {
			throw new IdColumnNotFoundException(tableName);
		}
	}

	private boolean hasColumn(Table table, Column column) {
		return table.getColumns().contains(column);
	}

	@Override
	public void addRecord(String tableName, Record record) {
		Table table = getByName(tableName);
		if (record != null && record.getFields() != null && record.getFields().size() > 0) {
			checkTypeAndId(tableName, record.getFields());
			checkNotNullColumnsAndFillDefaultValues(table.getColumns(), record.getFields());
			checkIsRecordUnique(table, record);
			table.getRecords().add(record);
			tableDao.update(table);
		} else {
			throw new EmptyRecordException();
		}
	}

	@Override
	public void changeName(String oldName, String newName) {
		tableDao.changeName(oldName, newName);
	}

	@Override
	public void drop(String tableName) {
		tableDao.delete(tableName);
	}

	private void checkTypeAndId(String tableName, Map<Column, Field> record) {
		boolean isId = false;
		for (Map.Entry<Column,Field> field : record.entrySet()) {
			if (field.getKey().isId()) {
				isId = true;
			}
			checkColumnFieldAccordance(field.getKey(), field.getValue());
		}
		if (!isId) {
			throw new IdColumnNotFoundException(tableName);
		}
	}

	private void checkColumnFieldAccordance(Column column, Field field) {
		checkNullable(column, field);
		checkType(column, field);
	}

	private void checkNullable(Column column, Field field) {
		if (notNullable(column) && field.getValue() == null) {
			throw new NullableException(column.getColumnName());
		}
	}

	private void checkType(Column column, Field field) {
		try {
			if (column.getType().equals("String")) {
				if (!Class.forName("java.lang.String").equals(field.getValue().getClass())) {
					throw new TypeException(column.getColumnName(), column.getClass().getSimpleName());
				}
			} else if (!Class.forName(column.getType()).equals(field.getValue().getClass())) {
				throw new TypeException(column.getColumnName(), column.getClass().getSimpleName());
			}
		} catch (ClassNotFoundException e) {
			throw new TypeException(column.getColumnName(), column.getClass().getSimpleName());
		}
	}

	private void checkNotNullColumnsAndFillDefaultValues(Set<Column> columns, Map<Column, Field> fields) {
		for (Column column : columns) {
			if (!containsColumn(column, fields.keySet())) {
				if (notNullable(column)) {
					if (hasDefaultValue(column)) {
						fillDefaultValue(fields, column);
					} else {
						throw new NotFoundException(EntityType.COLUMN, column.getColumnName());
					}
				} else {
					if (hasDefaultValue(column)) {
						fillDefaultValue(fields, column);
					} else {
						fields.put(column, new Field());
					}
				}
			}
		}
		for (Column column : fields.keySet()) {
			if (!columns.contains(column)) {
				throw new UnknownColumnException(column.getColumnName());
			}
		}
	}

	private boolean containsColumn(Column column, Set<Column> columns) {
		return columns.contains(column);
	}

	private boolean notNullable(Column column) {
		return !column.isNullable();
	}

	private boolean hasDefaultValue(Column column) {
		return column.getDefaultValue() != null;
	}

	private void fillDefaultValue(Map<Column, Field> fields, Column column) {
		Field field = new Field();
		field.setValue(column.getDefaultValue());
		fields.put(column, field);
	}

	private void checkIsRecordUnique(Table table, Record record) {
		Object value = findIdValue(table.getTableName(), record.getFields());
		for (Record record1 : table.getRecords()) {
			Object val = findIdValue(table.getTableName(), record1.getFields());
			if (val.equals(value)) {
				throw new RecordExistsException(val);
			}
		}
	}

	private Object findIdValue(String tableName, Map<Column, Field> fields) {
		for (Map.Entry<Column, Field> entry : fields.entrySet()) {
			if (entry.getKey().isId()) {
				return entry.getValue().getValue();
			}
		}
		throw new IdColumnNotFoundException(tableName);
	}

	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}*/
}
