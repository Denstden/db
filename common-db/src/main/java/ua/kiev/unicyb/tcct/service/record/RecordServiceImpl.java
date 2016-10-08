package ua.kiev.unicyb.tcct.service.record;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javafx.util.Pair;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EmptyRecordException;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.exception.NullableColumnException;
import ua.kiev.unicyb.tcct.exception.NullableException;
import ua.kiev.unicyb.tcct.exception.RecordExistsException;
import ua.kiev.unicyb.tcct.exception.RecordNotFoundException;
import ua.kiev.unicyb.tcct.exception.TypeException;
import ua.kiev.unicyb.tcct.exception.UnknownColumnException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Service
public class RecordServiceImpl implements RecordService {
	private static final Logger logger = LogManager.getLogger(RecordServiceImpl.class);

	@Autowired
	private DatabaseService databaseService;

	@Override
	public void addRecord(String databaseName, String tableName, Record record) {
		if (record.getFields().size() == 0) {
			throw new EmptyRecordException();
		}
		Database database = databaseService.findByName(databaseName);
		for (Table table : database.getTables()) {
			if (table.getTableName().equals(tableName)) {
				fillRequiredFields(table.getColumns(), record.getFields());
				for (Record tableRecord : table.getRecords()) {
					if (equalsIdColumns(record, tableRecord)) {
						throw new RecordExistsException(findIdValue(record));
					}
				}
				if (findRecordIdColumn(record.getFields()).getKey() != null) {
					checkType(record.getFields());
					checkColumns(record.getFields().keySet(), table.getColumns());
					table.getRecords().add(record);
				}
			}
		}
		databaseService.update(database);
	}

	private void checkColumns(Set<Column> columns, Set<Column> columns1) {
		for (Column column : columns) {
			if (!columns1.contains(column)) {
				throw new UnknownColumnException(column.getColumnName());
			}
		}
	}

	private void fillRequiredFields(Set<Column> columns, Map<Column, Field> fields) {
		for (Column column : columns) {
			if (fields.containsKey(column)) {
				if (!column.isNullable()) {
					if (fields.get(column) == null || fields.get(column).getValue() == null) {
						if (column.getDefaultValue() != null) {
							fields.replace(column, new Field(column.getDefaultValue()));
						} else {
							throw new NullableColumnException(column.getColumnName());
						}
					} else {
						//do nothing
					}
				} else {
					if (fields.get(column) == null
							|| fields.get(column).getValue() == null && column.getDefaultValue() != null) {
						fields.replace(column, new Field(column.getDefaultValue()));
					} else {
						//do nothing
					}
				}
			} else {
				if (!column.isNullable() && column.getDefaultValue() == null) {
					throw new NullableColumnException(column.getColumnName());
				} else {
					fields.put(column, new Field(column.getDefaultValue()));
				}
			}
		}
	}

	private void checkType(Map<Column, Field> fields) {
		for (Map.Entry<Column, Field> entry : fields.entrySet()) {
			Column column = entry.getKey();

			if (entry.getValue() == null || entry.getValue().getValue() == null) {
				if (!column.isNullable()) {
					checkForNullValue(entry, column);
				}
			} else {
				checkForNotNullValue(entry, column);
			}
		}
	}

	private void checkForNullValue(Map.Entry<Column, Field> entry, Column column) {
		if (column.getDefaultValue() == null) {
			throw new NullableColumnException(column.getColumnName());
		} else {
			entry.setValue(new Field(column.getDefaultValue()));
		}
	}

	private void checkForNotNullValue(Map.Entry<Column, Field> entry, Column column) {
		SupportedType type = column.getType();
		switch (type) {
		case DOUBLE: {
			try {
				Double.valueOf(entry.getValue().getValue().toString());
				break;
			} catch (NumberFormatException e) {
				throw new TypeException(column.getColumnName(), column.getType().name());
			}
		}
		case LONG: {
			try {
				Long.valueOf(entry.getValue().getValue().toString());
				break;
			} catch (NumberFormatException e) {
				throw new TypeException(column.getColumnName(), column.getType().name());
			}
		}
		case INTEGER: {
			try {
				Integer.valueOf(entry.getValue().getValue().toString());
				break;
			} catch (NumberFormatException e) {
				throw new TypeException(column.getColumnName(), column.getType().name());
			}
		}
		default: {
			try {
				entry.getValue().getValue().toString();
				break;
			} catch (NumberFormatException e) {
				throw new TypeException(column.getColumnName(), column.getType().name());
			}
		}
		}
	}

	@Override
	public void updateRecord(String databaseName, String tableName, Record record) {
		Pair<Column, Object> column = findRecordIdColumn(record.getFields());
		logger.info("Update record : ID column name '" + column.getKey().getColumnName() +
				"', ID column value '" + column.getValue() + "'");
		Database database = databaseService.findByName(databaseName);
		database.getTables().stream().filter(table -> table.getTableName().equals(tableName)).forEach(table -> {
			logger.info("Update record for table '" + tableName + "'");
			Record record1 = findRecordByIdColumn(column, table.getRecords());
			table.getRecords().remove(record1);
			record1.setFields(record.getFields());
			table.getRecords().add(record1);
		});
		databaseService.update(database);
	}

	private boolean equalsIdColumns(Record record, Record tableRecord) {
		Pair<Column, Object> record1id = findRecordIdColumn(record.getFields());
		Pair<Column, Object> record2id = findRecordIdColumn(tableRecord.getFields());
		return record1id.getKey().equals(record2id.getKey()) && record1id.getValue().equals(record2id.getValue());
	}

	private Optional<Object> findIdValue(Record record) {
		for (Map.Entry<Column, Field> entry : record.getFields().entrySet()) {
			if (entry.getKey().isId()) {
				return Optional.of(entry.getValue().getValue());
			}
		}
		return Optional.empty();
	}

	private Pair<Column, Object> findRecordIdColumn(Map<Column, Field> fields) {
		for (Map.Entry<Column, Field> entry : fields.entrySet()) {
			if (entry.getKey().isId() && entry.getValue() != null && entry.getValue().getValue() != null) {
				return new Pair<>(entry.getKey(), entry.getValue().getValue());
			} else if (entry.getValue() == null || entry.getValue().getValue() == null) {
				throw new NullableException("id");
			}
		}
		throw new IdColumnNotFoundException("record");
	}

	private Record findRecordByIdColumn(Pair<Column, Object> column, List<Record> records) {
		for (Record record : records) {
			for (Map.Entry<Column, Field> entry : record.getFields().entrySet()) {
				if (entry.getKey().equals(column.getKey()) && entry.getValue() != null &&
						entry.getValue().getValue().equals(column.getValue())) {
					return record;
				}
			}
		}
		throw new RecordNotFoundException(column.getValue().toString());
	}
}
