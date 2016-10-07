package ua.kiev.unicyb.tcct.domain.table;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class Table implements Serializable {
	private static final Long serialVersionUID = 1263315813434182612L;
	private String tableName;
	private Set<Column> columns = new HashSet<>();
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
