package ua.kiev.unicyb.tcct.resource;

import ua.kiev.unicyb.tcct.domain.database.Database;

/**
 * @Author Denys Storozhenko.
 */
public interface DatabaseResource {
	void createDatabase(String name);

	Iterable<Database> findAll();

	String[] dbNames();
}
