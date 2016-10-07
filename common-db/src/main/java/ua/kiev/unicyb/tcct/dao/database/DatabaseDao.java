package ua.kiev.unicyb.tcct.dao.database;

import ua.kiev.unicyb.tcct.domain.database.Database;

/**
 * @Author Denys Storozhenko.
 */
public interface DatabaseDao {
	void create(Database database);

	Database read(String databaseName);

	Database update(Database database);

	Database changeName(String oldName, String newName);

	void delete(String databaseName);

	boolean exists(Database database);

	Iterable<Database> findAll();

	void toFile(String fileName);

	void fromFile(String fileName);
}
