package ua.kiev.unicyb.tcct.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author Denys Storozhenko.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"databaseName",
		"tableName",
		"columnName"
})
@XmlRootElement(name = "getColumnByNameDtoRequest")
public class GetColumnByNameDtoRequest {
	@XmlElement(required = true)
	private String databaseName;
	@XmlElement(required = true)
	private String tableName;
	@XmlElement(required = true)
	private String columnName;

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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
