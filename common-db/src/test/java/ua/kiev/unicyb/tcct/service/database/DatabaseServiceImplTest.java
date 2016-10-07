package ua.kiev.unicyb.tcct.service.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.kiev.unicyb.tcct.dao.database.DatabaseDao;
import ua.kiev.unicyb.tcct.domain.database.Database;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * @Author Denys Storozhenko.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseServiceImplTest {
	@Mock
	private DatabaseDao databaseDao;

	@InjectMocks
	private DatabaseServiceImpl databaseService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCreate() {
		Database database = new Database();
		database.setDatabaseName("Name");
		databaseService.create(database);
		verify(databaseDao, only()).create(any(Database.class));
	}

	@Test
	public void shouldFindByName() {
		databaseService.findByName("Name");
		verify(databaseDao, only()).read("Name");
	}
}
