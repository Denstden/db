package ua.kiev.unicyb.tcct.web.dto;

import java.util.List;

/**
 * @Author Denys Storozhenko.
 */
public class TableDto {
	private String tableName;

	private List<RecordDto> records;

	private List<ColumnDto> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<RecordDto> getRecords() {
		return records;
	}

	public void setRecords(List<RecordDto> records) {
		this.records = records;
	}

	public List<ColumnDto> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnDto> columns) {
		this.columns = columns;
	}
}
