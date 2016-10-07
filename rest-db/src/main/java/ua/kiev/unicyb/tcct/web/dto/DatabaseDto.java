package ua.kiev.unicyb.tcct.web.dto;

import java.util.List;

/**
 * @Author Denys Storozhenko.
 */
public class DatabaseDto {
	private String databaseName;

	private List<TableDto> tables;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public List<TableDto> getTables() {
		return tables;
	}

	public void setTables(List<TableDto> tables) {
		this.tables = tables;
	}
}
