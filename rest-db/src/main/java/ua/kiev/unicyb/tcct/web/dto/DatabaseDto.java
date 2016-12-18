package ua.kiev.unicyb.tcct.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author Denys Storozhenko.
 */
public class DatabaseDto {
	@JsonProperty("databaseName")
	private String databaseName;
	@JsonProperty("tables")
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
