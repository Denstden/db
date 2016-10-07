package ua.kiev.unicyb.tcct.dao.record;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.exception.EntityType;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.exception.NotFoundException;
import ua.kiev.unicyb.tcct.exception.NullableException;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class RecordDaoImplInMem implements RecordDao {
	private List<Record> records = new ArrayList<>();

	@Override
	public void create(Map<Column, Field> record) {
		Column id = checkId(record.keySet());
		if (record.get(id).getValue() == null) {
			throw new NullableException(id.getColumnName());
		}
		Record toSave = new Record();
		toSave.setFields(record);
		records.add(toSave);
	}

	private Column checkId(Set<Column> columns) {
		for (Column column : columns) {
			if (column.isId()) {
				return column;
			}
		}
		throw new IdColumnNotFoundException("record");
	}

	@Override
	public Record read(ID id, Object value) {
		for (Record record : records) {
			Map<Column, Field> recordMap = record.getFields();
			if (recordMap.get(id) != null && recordMap.get(id).equals(new Field(value))) {
				return record;
			}
		}
		throw new NotFoundException(EntityType.RECORD, id.getColumnName() + "(" + value + ")");
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
}
