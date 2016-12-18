package ua.kiev.unicyb.tcct.corba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class RemoteDatabaseServiceImpl implements RemoteDatabaseService {
	@Autowired
	private DatabaseService databaseService;

	@Override
	public void createDatabase(String dbName) {
		Database database = new Database();
		database.setDatabaseName(dbName);
		databaseService.create(database);
	}
}
