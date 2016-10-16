package ua.kiev.unicyb.tcct.dao.database;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

import javax.annotation.PostConstruct;

import ua.kiev.unicyb.tcct.dao.MockDao;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.database.Database;
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

	@Autowired
	private MockDao mockDao;

	@PostConstruct
	private void setUp() {
		databases.addAll(mockDao.mockDb());
	}

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
		for (Database database : databases) {
			if (database.getDatabaseName() != null && database.getDatabaseName().equals(databaseName)) {
				return database;
			}
		}
		throw new NotFoundException(EntityType.DATABASE, databaseName);
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

	@Override
	public Column getColumnByName(String dbName, String tableName, String columnName) {
		Iterable<Table> tables = findAllTables(dbName);
		for (Table table : tables) {
			for (Column column : table.getColumns()) {
				if (column.getColumnName().equals(columnName)) {
					return column;
				}
			}
		}
		return null;
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	public MockDao getMockDao() {
		return mockDao;
	}

	public void setMockDao(MockDao mockDao) {
		this.mockDao = mockDao;
	}
}
