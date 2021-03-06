package ua.kiev.unicyb.tcct.domain.database;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"databaseName", "tables"})
@XmlRootElement(name = "database")
public class Database implements Serializable, Comparable<Database> {
	private static final long serialVersionUID = 12631934182612L;

	@XmlElement(required = true)
	private String databaseName;

	@XmlElementWrapper(name = "listTables", namespace = "ua.kiev.unicyb.tcct", required = true)
	@XmlElement(name = "table", namespace = "ua.kiev.unicyb.tcct")
	private List<Table> tables = new ArrayList<>();

	public Database() {
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Database database = (Database) o;

		return databaseName != null ?
				databaseName.equals(database.databaseName) :
				database.databaseName == null && (tables != null ?
						tables.equals(database.tables) :
						database.tables == null);

	}

	@Override
	public int hashCode() {
		int result = databaseName != null ? databaseName.hashCode() : 0;
		result = 31 * result + (tables != null ? tables.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(Database o) {
		if (this == o)
			return 1;
		if (o == null || getClass() != o.getClass())
			return -1;
		if (databaseName == null && o.databaseName == null) {
			return 0;
		} else {
			if (databaseName == null) {
				return -1;
			} else {
				return databaseName.compareTo(o.databaseName);
			}
		}
	}

	@Override
	public String toString() {
		return "Database{" +
				"databaseName='" + databaseName + '\'' +
				", tables=" + tables +
				'}';
	}
	/*public String toString() {
		return "Database:{\n" +
				"databaseName='" + databaseName + '\'' +
				"\ntables=[\n" + tablesToString(tables) +
				"]\n}\n";
	}

	private String tablesToString(List<Table> tables) {
		String res = "";
		for (Table table : tables) {
			res += "\tTable{"+"\n";
			res += "\t\ttableName='" + table.getTableName()+"'\n";
			res += "\t\tcolumns=[\n" + columnsToString(table.getColumns())+"\t\t]\n\t}\n";
		}
		return res;
	}

	private String columnsToString(Set<Column> columns) {
		String res = "";
		for (Column column : columns) {
			res += "\t\t\tColumn{"+"\n";
			res += "\t\t\t\ttype='"+column.getType()+"'\n";
			res += "\t\t\t\tcolumnName='"+column.getColumnName()+"'\n";
			res += "\t\t\t\tisNullable='"+column.isNullable()+"'\n";
			res += "\t\t\t\tdefaultValue='"+column.getDefaultValue()+"'\n\t\t\t}\n";
		}
		return res;
	}*/
}
