package ua.kiev.unicyb.tcct.util;

import ua.kiev.unicyb.tcct.domain.database.Database;

/**
 * @Author Denys Storozhenko.
 */
public class DatabaseFactory extends AbstractFactory<Database> {
	@Override
	public Database create(String name) {
		Database database = new Database();
		database.setDatabaseName(name);
		return database;
	}
}
