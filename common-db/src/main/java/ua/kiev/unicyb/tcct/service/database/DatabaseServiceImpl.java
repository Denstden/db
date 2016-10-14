package ua.kiev.unicyb.tcct.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kiev.unicyb.tcct.dao.database.DatabaseDao;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {
//	private static final Logger logger = LogManager.getLogger(DatabaseServiceImpl.class);

	@Autowired
	private DatabaseDao databaseDao;

	@Override
	public Database create(Database database) {
		databaseDao.create(database);
		return database;
	}

	@Override
	public Database findByName(String databaseName) {
		return databaseDao.read(databaseName);
	}

	@Override
	public void toFile(String fileName) {
		databaseDao.toFile(fileName);
	}

	@Override
	public void loadFromFile(String fileName) {
		databaseDao.fromFile(fileName);
	}

	@Override
	public Iterable<Table> findAllTables(String databaseName) {
		return databaseDao.findAllTables(databaseName);
	}

	@Override
	public Column getColumnByName(String dbName, String tableName, String columnName) {
		return databaseDao.getColumnByName(dbName, tableName, columnName);
	}

	@Override
	public void changeName(String oldName, String newName) {
		databaseDao.changeName(oldName, newName);
	}

	@Override
	public void update(Database database) {
		Database db = findByName(database.getDatabaseName());
		db.setTables(database.getTables());
		databaseDao.update(db);
	}

	@Override
	public void drop(String name) {
		databaseDao.delete(name);
	}

	@Override
	public Iterable<Database> findAll() {
		return databaseDao.findAll();
	}

	public DatabaseDao getDatabaseDao() {
		return databaseDao;
	}

	public void setDatabaseDao(DatabaseDao databaseDao) {
		this.databaseDao = databaseDao;
	}
}
