package ua.kiev.unicyb.tcct.resource;

import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
public interface RecordResource {
	void addRecord(String dbName, String tableName, Record record);
}
