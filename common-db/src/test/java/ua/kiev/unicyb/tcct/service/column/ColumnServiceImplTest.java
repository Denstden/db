package ua.kiev.unicyb.tcct.service.column;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.exception.IdColumnExistsException;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.util.ColumnFactory;
import ua.kiev.unicyb.tcct.util.DatabaseFactory;
import ua.kiev.unicyb.tcct.util.TableFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author Denys Storozhenko.
 */
@RunWith(MockitoJUnitRunner.class)
public class ColumnServiceImplTest {
	@Mock
	private DatabaseService databaseService;

	@InjectMocks
	private ColumnServiceImpl columnService;

	private DatabaseFactory databaseFactory = new DatabaseFactory();
	private TableFactory tableFactory = new TableFactory();
	private ColumnFactory columnFactory = new ColumnFactory();
	private Database database;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		database = databaseFactory.create("DB");
		when(databaseService.findByName(any(String.class))).thenReturn(database);
	}

	@Test(expected = IdColumnNotFoundException.class)
	public void addColumnShouldRiseExceptionIfFirstNotId() {
		database.setTables(Collections.singletonList(tableFactory.create("Table")));

		columnService.addColumn("DB", "Table", columnFactory.create("Column", "String"));
	}

	@Test
	public void shouldAddColumnIfFirstIsId() {
		Table table = tableFactory.create("Table");
		database.setTables(Collections.singletonList(table));

		Column column = columnFactory.createId("ID", "String");
		columnService.addColumn("DB", "Table", column);

		assertEquals(column, database.getTables().get(0).getColumns().iterator().next());
	}

	@Test(expected = ExistsException.class)
	public void addColumnShouldRiseExceptionIfExists() {
		Table table = tableFactory.create("Table");
		table.getColumns().add(columnFactory.createId("ID", "String"));
		table.getColumns().add(columnFactory.create("Name", "String"));
		database.setTables(Collections.singletonList(table));

		columnService.addColumn("DB", "Table", columnFactory.create("Name", "String"));
	}

	@Test(expected = IdColumnExistsException.class)
	public void addColumnIdShouldRiseExceptionIfIdExists() {
		Table table = tableFactory.create("Table");
		table.getColumns().add(columnFactory.createId("ID", "String"));
		table.getColumns().add(columnFactory.create("Name", "String"));
		database.setTables(Collections.singletonList(table));

		columnService.addColumn("DB", "Table", columnFactory.createId("ID1", "String"));
	}

	@Test
	public void shouldAddColumnIfNotExists() {
		Table table = tableFactory.create("Table");
		table.getColumns().add(columnFactory.createId("ID", "String"));
		table.getColumns().add(columnFactory.create("Name", "String"));
		database.setTables(Collections.singletonList(table));

		columnService.addColumn("DB", "Table", columnFactory.create("Surname", "String"));

		assertEquals(3, database.getTables().get(0).getColumns().size());
	}
}
