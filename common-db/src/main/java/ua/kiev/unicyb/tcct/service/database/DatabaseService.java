package ua.kiev.unicyb.tcct.service.database;

import ua.kiev.unicyb.tcct.domain.database.Database;

/**
 * @Author Denys Storozhenko.
 */
public interface DatabaseService {
	Database create(Database database);

	Database findByName(String databaseName);

	void changeName(String oldName, String newName);

	void update(Database database);

	void drop(String name);

	Iterable<Database> findAll();

	void toFile(String fileName);

	void loadFromFile(String fileName);
}
