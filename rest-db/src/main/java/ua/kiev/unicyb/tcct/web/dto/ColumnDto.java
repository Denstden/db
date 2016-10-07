package ua.kiev.unicyb.tcct.web.dto;

/**
 * @Author Denys Storozhenko.
 */
public class ColumnDto {
	private String type;

	private String columnName;

	private boolean nullable;

	private String defaultValue;

	private boolean id;

	public ColumnDto() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean getNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean getId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ColumnDto{" +
				"type='" + type + '\'' +
				", columnName='" + columnName + '\'' +
				", isNullable=" + nullable +
				", defaultValue='" + defaultValue + '\'' +
				", isId=" + id +
				'}';
	}
}
