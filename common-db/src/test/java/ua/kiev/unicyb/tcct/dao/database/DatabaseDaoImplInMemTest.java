package ua.kiev.unicyb.tcct.dao.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.dao.MockDao;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.ExistsException;
import ua.kiev.unicyb.tcct.exception.NotFoundException;
import ua.kiev.unicyb.tcct.util.DatabaseFactory;
import ua.kiev.unicyb.tcct.util.TableFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @Author Denys Storozhenko.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseDaoImplInMemTest {
	@Mock
	private MockDao mockDao;

	@InjectMocks
	private DatabaseDaoImplInMem databaseDao = new DatabaseDaoImplInMem();

	private DatabaseFactory databaseFactory = new DatabaseFactory();

	private TableFactory tableFactory = new TableFactory();

	@Before
	public void setUp() {
		when(mockDao.mockDb()).thenReturn(new ArrayList<>());
	}

	@Test
	public void shouldCreate() {
		Database database = databaseFactory.create("Name");

		databaseDao.create(database);

		assertTrue(databaseDao.getDatabases().contains(database));
	}

	@Test(expected = ExistsException.class)
	public void createShouldRiseExceptionIfExists() {
		Database database = databaseFactory.create("Name");

		databaseDao.create(database);
		assertTrue(databaseDao.getDatabases().contains(database));

		databaseDao.create(databaseFactory.create("Name"));
	}

	@Test
	public void existsNullShouldBeFalse() {
		assertFalse(databaseDao.exists(null));
	}

	@Test
	public void existsWithoutNameShouldBeFalse() {
		Database database = new Database();

		assertFalse(databaseDao.exists(database));
	}

	@Test
	public void existsShouldBeTrue() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		assertTrue(databaseDao.exists(databaseFactory.create("Name")));
	}

	@Test
	public void shouldRead() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		assertEquals(database, databaseDao.read("Name"));
	}

	@Test(expected = NotFoundException.class)
	public void readShouldRiseException() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		databaseDao.read("Name1");
	}

	@Test
	public void shouldDelete() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		databaseDao.delete("Name");

		assertFalse(databaseDao.getDatabases().contains(database));
	}

	@Test(expected = NotFoundException.class)
	public void deleteShouldRiseException() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		databaseDao.delete("Name1");
	}

	@Test
	public void shouldUpdateAllFields() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		Database database1 = databaseFactory.create("Name");
		List<Table> tables = createTables();
		database1.setTables(tables);

		Database actual = databaseDao.update(database1);

		assertEquals(2, databaseDao.getDatabases().get(0).getTables().size());
		assertEquals(actual, databaseDao.read("Name"));
	}

	@Test(expected = NotFoundException.class)
	public void updateShouldRiseException() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		Database database1 = databaseFactory.create("Name1");
		List<Table> tables = createTables();
		database1.setTables(tables);

		databaseDao.update(database1);
	}

	@Test
	public void shouldChangeName() {
		Database database = databaseFactory.create("Name");
		databaseDao.getDatabases().add(database);

		databaseDao.changeName("Name", "Name1");

		assertEquals("Name1", databaseDao.getDatabases().get(0).getDatabaseName());
	}

	private List<Table> createTables() {
		List<Table> tables = new ArrayList<>();
		tables.add(tableFactory.create("Table1"));
		tables.add(tableFactory.create("Table2"));
		return tables;
	}
}
