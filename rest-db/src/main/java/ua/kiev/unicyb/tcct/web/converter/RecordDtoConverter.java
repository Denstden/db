package ua.kiev.unicyb.tcct.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EntityType;
import ua.kiev.unicyb.tcct.exception.NotFoundException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.web.dto.RecordDto;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class RecordDtoConverter extends AbstractConverter<RecordDto, Record> {
	@Autowired
	private DatabaseService databaseService;

	@Override
	public RecordDto toDto(Record entity) {
		RecordDto recordDto = new RecordDto();
		Map<String, String> fields = new HashMap<>();
		if (entity.getFields() != null) {
			for (Map.Entry<Column, Field> entry : entity.getFields().entrySet()) {
				fields.put(entry.getKey().getColumnName(), entry.getValue().getValue().toString());
			}
			recordDto.setRecord(fields);
		}
		return recordDto;
	}

	@Override
	public Record toEntity(RecordDto dto) {
		Record record = new Record();
		if (dto.getRecord() != null) {
			Map<Column, Field> recordMap = new HashMap<>();
			for (Map.Entry<String, String> entry : dto.getRecord().entrySet()) {
				String columnName = entry.getKey();
				String fieldValue = entry.getValue();
				Set<Column> tableColumns = getColumns(dto.getDatabaseName(), dto.getTableName());
				for (Column column : tableColumns) {
					if (column.getColumnName().equals(columnName)) {
						recordMap.put(column, convertField(column.getType(), fieldValue));
						break;
					}
				}
			}
			record.setFields(recordMap);
		}
		return record;
	}

	private Set<Column> getColumns(String dbName, String tableName) {
		Database database = databaseService.findByName(dbName);
		for (Table table : database.getTables()) {
			if (table.getTableName().equals(tableName)) {
				return table.getColumns();
			}
		}
		throw new NotFoundException(EntityType.TABLE, tableName);
	}

	private Field convertField(SupportedType type, String value) {
		Field field = new Field();
		if (type == SupportedType.DOUBLE) {
			field.setValue(Double.valueOf(value));
		} else if (type == SupportedType.LONG) {
			field.setValue(Long.valueOf(value));
		} else if (type == SupportedType.INTEGER) {
			field.setValue(Integer.valueOf(value));
		} else {
			field.setValue(String.valueOf(value));
		}
		return field;
	}
}
