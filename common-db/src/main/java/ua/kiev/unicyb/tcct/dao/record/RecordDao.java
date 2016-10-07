package ua.kiev.unicyb.tcct.dao.record;

import java.util.Map;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
public interface RecordDao {
	void create(Map<Column, Field> record);
	Record read(ID id, Object value);
}
