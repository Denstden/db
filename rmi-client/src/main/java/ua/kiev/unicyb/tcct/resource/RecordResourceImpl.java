package ua.kiev.unicyb.tcct.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.service.record.RecordService;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class RecordResourceImpl implements RecordResource {

	@Autowired
	@Qualifier(value = "remoteRecordService")
	private RecordService recordService;

	@Override
	public void addRecord(String dbName, String tableName, Record record) {
		recordService.addRecord(dbName, tableName, record);
	}
}
