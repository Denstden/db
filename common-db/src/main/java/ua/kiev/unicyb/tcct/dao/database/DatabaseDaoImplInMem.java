package ua.kiev.unicyb.tcct.dao.database;

import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EntityType;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.exception.NotFoundException;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class DatabaseDaoImplInMem implements DatabaseDao {
	private List<Database> databases = new ArrayList<>();

	@Override
	public void create(Database database) {
		if (!exists(database)) {
			databases.add(database);
		} else {
			throw new ExistsException(EntityType.DATABASE, database.getDatabaseName());
		}
	}

	@Override
	public Database read(String databaseName) {
		mockDb();
		for (Database database : databases) {
			if (database.getDatabaseName() != null && database.getDatabaseName().equals(databaseName)) {
				return database;
			}
		}
		throw new NotFoundException(EntityType.DATABASE, databaseName);
	}

	private void mockDb() {
		Database database = new Database();
		database.setDatabaseName("DB1");
		Table table = new Table();
		table.setTableName("TABLE1");
		database.setTables(Collections.singletonList(table));
		databases.add(database);
		databases.get(0).getTables().get(0).setRecords(createRecords());
		databases.get(0).getTables().get(0).setColumns(createColumns());
	}

	@Override
	public Database update(Database db) {
		if (db.getDatabaseName() != null) {
			Database database = read(db.getDatabaseName());
			database.setTables(db.getTables());
			return database;
		} else {
			throw new NotFoundException(EntityType.DATABASE, null);
		}
	}

	@Override
	public Database changeName(String oldName, String newName) {
		Database database1 = read(oldName);
		database1.setDatabaseName(newName);
		return database1;
	}

	@Override
	public void delete(String databaseName) {
		Database database = read(databaseName);
		databases.remove(database);
	}

	@Override
	public boolean exists(Database database) {
		if (database == null || database.getDatabaseName() == null) {
			return false;
		} else {
			try {
				read(database.getDatabaseName());
				return true;
			} catch (NotFoundException e) {
				return false;
			}
		}
	}

	@Override
	public Iterable<Database> findAll() {
		databases.get(0).getTables().get(0).setRecords(createRecords());
		databases.get(0).getTables().get(0).setColumns(createColumns());
		return databases;
	}

	@Override
	public Iterable<Table> findAllTables(String databaseName) {
		Database database = read(databaseName);
		return database.getTables();
	}

	@Override
	public void toFile(String fileName) {
		try (
				OutputStream file = new FileOutputStream(fileName);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer)
		) {
			output.writeObject(databases);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void fromFile(String fileName) {
		try (
				InputStream file = new FileInputStream(fileName);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);
		) {
			//deserialize the List
			databases = (List<Database>) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Set<Column> createColumns() {
		Set<Column> columns = new HashSet<>();
		columns.add(createColumn());
		columns.add(createColumn1());
		return columns;
	}

	private Column createColumn() {
		Column column = new ID();
		column.setNullable(true);
		column.setType(SupportedType.STRING);
		column.setColumnName("ID");
		column.setDefaultValue("123");
		return column;
	}

	private Column createColumn1() {
		Column column = new Column();
		column.setNullable(true);
		column.setType(SupportedType.STRING);
		column.setColumnName("Name");
		column.setDefaultValue("Ivan");
		return column;
	}

	private List<Record> createRecords() {
		List<Record> records = new ArrayList<>();
		records.add(createRecord());
		return records;
	}

	private Record createRecord() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn(), new Field("123"));
		map.put(createColumn1(), new Field("Petro"));
		record.setFields(map);
		return record;
	}

	private Record createRecord1() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn1(), new Field("Petro"));
		record.setFields(map);
		return record;
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}
}
