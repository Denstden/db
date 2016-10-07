package ua.kiev.unicyb.tcct.util;

import java.util.Map;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
public class RecordFactory {

	public Record create(Map<Column, Field> map) {
		Record record = new Record();
		record.setFields(map);
		return record;
	}
}
