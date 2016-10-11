package ua.kiev.unicyb.tcct.service.record;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.EmptyRecordException;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.exception.NullableColumnException;
import ua.kiev.unicyb.tcct.exception.NullableException;
import ua.kiev.unicyb.tcct.exception.RecordExistsException;
import ua.kiev.unicyb.tcct.exception.TypeException;
import ua.kiev.unicyb.tcct.exception.UnknownColumnException;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.util.ColumnFactory;
import ua.kiev.unicyb.tcct.util.DatabaseFactory;
import ua.kiev.unicyb.tcct.util.RecordFactory;
import ua.kiev.unicyb.tcct.util.TableFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author Denys Storozhenko.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecordServiceImplTest {
	@Mock
	private DatabaseService databaseService;

	@InjectMocks
	private RecordServiceImpl recordService;

	private DatabaseFactory databaseFactory = new DatabaseFactory();
	private TableFactory tableFactory = new TableFactory();
	private RecordFactory recordFactory = new RecordFactory();
	private ColumnFactory columnFactory = new ColumnFactory();
	private Database database;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		database = databaseFactory.create("DB");
		when(databaseService.findByName(any(String.class))).thenReturn(database);
	}

	@Test(expected = EmptyRecordException.class)
	public void addRecordShouldRiseEmptyException() {
		Table table = tableFactory.create("Table");
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();
		Record record = recordFactory.create(recordMap);
		recordService.addRecord("DB", "Table", record);
	}

	@Test(expected = IdColumnNotFoundException.class)
	public void addRecordWithoutIdShouldRiseIdNotFoundException() {
		Table table = tableFactory.create("Table");
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();
		Column column = columnFactory.create("Name", SupportedType.STRING);
		Field field = new Field();
		field.setValue("Ivan");
		recordMap.put(column, field);
		Record record = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record);
	}

	@Test(expected = NullableException.class)
	public void addRecordNotNullWithNullValueShouldRiseNullableException() {
		Table table = tableFactory.create("Table");
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();
		Column column = columnFactory.createId("ID", SupportedType.STRING);
		Field field = new Field();
		recordMap.put(column, field);
		Record record = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record);
	}

	@Test(expected = TypeException.class)
	public void addRecordColumnTypeNotAccordingWithValueTypeShouldRiseTypeException() {
		Table table = tableFactory.create("Table");
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();
		Column column = columnFactory.createId(	"Name", SupportedType.STRING);
		Field field = new Field();
		field.setValue(123L);
		recordMap.put(column, field);
		Record record = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record);
	}

	@Test(expected = NullableColumnException.class)
	public void addRecordColumnNotNullableWithoutDefaultValueShouldRiseException() {
		Table table = tableFactory.create("Table");
		Column column = columnFactory.create("Name", false, null, SupportedType.STRING);
		Column id = columnFactory.createId("ID", SupportedType.STRING);
		table.getColumns().add(id);
		table.getColumns().add(column);
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();

		Column column1 = columnFactory.create("Surname", SupportedType.LONG);
		Field field = new Field();
		field.setValue("123");
		recordMap.put(id, field);
		recordMap.put(column1, field);
		Record record = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record);
	}

	@Test(expected = UnknownColumnException.class)
	public void addRecordWithUnknownColumnShouldRiseException() {
		Table table = tableFactory.create("Table");
		Column column = columnFactory.create("Name", true, null, SupportedType.STRING);
		Column id = columnFactory.createId("ID", SupportedType.STRING);
		table.getColumns().add(id);
		table.getColumns().add(column);
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();

		Column column1 = columnFactory.create("Surname", SupportedType.STRING);
		Field field = new Field();
		field.setValue("123");
		recordMap.put(id, field);
		recordMap.put(column1, field);
		Record record = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record);
	}

	@Test(expected = RecordExistsException.class)
	public void addRecordTableHasRecordWithSuchIdShouldRiseException() {
		Table table = tableFactory.create("Table");
		Column id = columnFactory.createId("ID", SupportedType.STRING);
		Map<Column, Field> recordMap1 = new HashMap<>();
		Field fieldId = new Field("recordId");
		recordMap1.put(id, fieldId);
		table.getColumns().add(id);
		Record record = new Record();
		record.setFields(recordMap1);
		table.getRecords().add(record);
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();
		Field field = new Field();
		field.setValue("recordId");
		recordMap.put(id, field);
		Record record1 = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record1);
	}
	@Test
	public void shouldAddRecordAndFillAllValues() {
		Table table = tableFactory.create("Table");
		Column id = columnFactory.createId("ID", SupportedType.STRING);
		Column column1 = columnFactory.create("Surname", false, null, SupportedType.STRING);
		Column column2 = columnFactory.create("Age", true, null, SupportedType.STRING);
		Column column3 = columnFactory.create("Column3", true, 22.555, SupportedType.DOUBLE);
		Column column4 = columnFactory.create("Column4", false, 22.123, SupportedType.DOUBLE);
		table.getColumns().add(id);
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.getColumns().add(column3);
		table.getColumns().add(column4);
		database.getTables().add(table);

		Map<Column, Field> recordMap = new HashMap<>();
		Column column5 = columnFactory.create("Surname", SupportedType.STRING);
		Field field = new Field();
		field.setValue("Ivanov");
		recordMap.put(id, field);
		recordMap.put(column5, field);
		Record record = recordFactory.create(recordMap);

		recordService.addRecord("DB", "Table", record);

//		assertEquals(5, table.getRecords().get(0).getFields().size());
		Map<Column, Field> res = table.getRecords().get(0).getFields();
		assertTrue(res.containsKey(id));
		assertEquals("Ivanov", res.get(id).getValue());
		assertTrue(res.containsKey(column1));
		assertEquals("Ivanov", res.get(column1).getValue());
		assertTrue(res.containsKey(column2));
		assertEquals(null, res.get(column2).getValue());
		assertTrue(res.containsKey(column3));
		assertEquals(22.555, res.get(column3).getValue());
		assertTrue(res.containsKey(column4));
		assertEquals(22.123, res.get(column4).getValue());
	}
}
