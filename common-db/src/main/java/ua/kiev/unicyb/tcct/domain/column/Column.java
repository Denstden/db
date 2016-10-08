package ua.kiev.unicyb.tcct.domain.column;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class Column implements Serializable {
	private static final Long serialVersionUID = 1263317914745382612L;

	private SupportedType type;

	private String columnName;

	private Boolean isNullable = false;

	private Object defaultValue;

	public Column() {
	}

	public Column(SupportedType type) {
		this.type = type;
	}

	public Column(String columnName, SupportedType type) {
		this.columnName = columnName;
		this.type = type;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Boolean isNullable() {
		return isNullable;
	}

	public void setNullable(Boolean nullable) {
		isNullable = nullable;
	}

	public SupportedType getType() {
		return type;
	}

	public void setType(SupportedType type) {
		this.type = type;
	}

	public boolean isId() {
		return getClass().equals(ID.class);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Column column = (Column) o;

		return columnName != null ? columnName.equals(column.columnName) : column.columnName == null;

	}

	@Override
	public int hashCode() {
		return columnName != null ? columnName.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Column{" +
				"type=" + type +
				", columnName='" + columnName + '\'' +
				", isNullable=" + isNullable +
				", defaultValue=" + defaultValue +
				'}';
	}
}
