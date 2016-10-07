package ua.kiev.unicyb.tcct.service.table;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.util.DatabaseFactory;
import ua.kiev.unicyb.tcct.util.TableFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @Author Denys Storozhenko.
 */
@RunWith(MockitoJUnitRunner.class)
public class TableServiceImplTest {
	@Mock
	private DatabaseService databaseService;

	@InjectMocks
	private TableServiceImpl tableService;

	private DatabaseFactory databaseFactory = new DatabaseFactory();

	private TableFactory tableFactory = new TableFactory();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddTableToDatabase() {
		Database database = databaseFactory.create("Name");
		when(databaseService.findByName("Name")).thenReturn(database);

		tableService.addTable("Name", tableFactory.create("table"));
		verify(databaseService).update(any(Database.class));
		assertEquals(1, databaseService.findByName("Name").getTables().size());
	}

	@Test(expected = ExistsException.class)
	public void addTableToDatabaseIfExistsSuchShouldRiseException() {
		Database database = databaseFactory.create("Name");
		database.getTables().add(tableFactory.create("table"));
		when(databaseService.findByName("Name")).thenReturn(database);

		tableService.addTable("Name", tableFactory.create("table"));
	}

	@Test
	public void shouldRemoveTable() {
		Database database = databaseFactory.create("Name");
		Table table = tableFactory.create("Table1");
		database.getTables().add(table);
		database.getTables().add(tableFactory.create("Table2"));
		assertEquals(2, database.getTables().size());
		when(databaseService.findByName("Name")).thenReturn(database);

		tableService.removeTable("Name", "Table1");

		assertEquals(1, database.getTables().size());
	}
}
