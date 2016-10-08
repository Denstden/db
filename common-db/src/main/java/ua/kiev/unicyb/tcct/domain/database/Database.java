package ua.kiev.unicyb.tcct.domain.database;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class Database implements Serializable, Comparable<Database> {
	private static final long serialVersionUID = 12631934182612L;

	private String databaseName;

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
}
