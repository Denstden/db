package ua.kiev.unicyb.tcct.domain.column;

import org.springframework.stereotype.Component;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @Author Denys Storozhenko.
 */
@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"type", "columnName", "isNullable", "defaultValue"})
@XmlRootElement(name = "column")
public class Column implements Serializable {
	private static final Long serialVersionUID = 1263317914745382612L;

	@XmlElement(required = true)
	private SupportedType type;

	@XmlElement(required = true)
	private String columnName;

	@XmlElement(required = true)
	private Boolean isNullable = false;

	@XmlElement(required = true)
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
