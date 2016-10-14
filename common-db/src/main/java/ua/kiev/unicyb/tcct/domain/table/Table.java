package ua.kiev.unicyb.tcct.domain.table;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"tableName",
		"columns"
})
@XmlRootElement(name = "table")
public class Table implements Serializable {
	private static final Long serialVersionUID = 1263315813434182612L;

	@XmlElement(required = true)
	private String tableName;
	@XmlElementWrapper(name = "columns", namespace = "ua.kiev.unicyb.tcct", required = true)
	@XmlElement(name = "column", namespace = "ua.kiev.unicyb.tcct")
	private Set<Column> columns = new HashSet<>();
	@XmlTransient
	private List<Record> records = new ArrayList<>();

	public Table() {
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Table table = (Table) o;

		return tableName != null ? tableName.equals(table.tableName) : table.tableName == null;

	}

	@Override
	public int hashCode() {
		return tableName != null ? tableName.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Table{" +
				"tableName='" + tableName + '\'' +
				", columns=" + columns +
				", records=" + records +
				'}';
	}
}
