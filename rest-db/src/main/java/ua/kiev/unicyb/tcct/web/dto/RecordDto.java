package ua.kiev.unicyb.tcct.web.dto;

import java.util.Map;

/**
 * @Author Denys Storozhenko.
 */
public class RecordDto {
	private Map<String, String> record;

	private String databaseName;

	private String tableName;

	public Map<String, String> getRecord() {
		return record;
	}

	public void setRecord(Map<String, String> record) {
		this.record = record;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "RecordDto{" +
				"record=" + record +
				", databaseName='" + databaseName + '\'' +
				", tableName='" + tableName + '\'' +
				'}';
	}
}
