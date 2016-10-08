package ua.kiev.unicyb.tcct.service.record;

import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
public interface RecordService {
	void addRecord(String databaseName, String tableName, Record record);

	void updateRecord(String databaseName, String tableName, Record record);

	void uploadImage(String databaseName, String tableName, String columnName, String recordId, byte[] bytes);
}
