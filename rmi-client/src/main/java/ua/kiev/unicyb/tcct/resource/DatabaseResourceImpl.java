package ua.kiev.unicyb.tcct.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class DatabaseResourceImpl implements DatabaseResource {
	@Autowired
	@Qualifier(value = "remoteDatabaseService")
	private DatabaseService databaseService;

	@Override
	public void createDatabase(String dbName) {
		Database database = new Database();
		database.setDatabaseName(dbName);
		databaseService.create(database);
	}

	@Override
	public Iterable<Database> findAll() {
		return databaseService.findAll();
	}

	@Override
	public String[] dbNames() {
		Iterable<Database> databases = findAll();
		List<String> res = new ArrayList<>();
		for (Database database : databases) {
			res.add(database.getDatabaseName());
		}
		String[] names = new String[res.size()];
		return res.toArray(names);
	}

	public DatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}
}
