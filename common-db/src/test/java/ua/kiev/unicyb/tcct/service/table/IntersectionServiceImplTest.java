package ua.kiev.unicyb.tcct.service.table;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;
import ua.kiev.unicyb.tcct.util.ColumnFactory;
import ua.kiev.unicyb.tcct.util.TableFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @Author Denys Storozhenko.
 */
@RunWith(MockitoJUnitRunner.class)
public class IntersectionServiceImplTest {
	@Mock
	private TableService tableService;

	@InjectMocks
	private IntersectionServiceImpl intersectionService = new IntersectionServiceImpl();

	private TableFactory tableFactory = new TableFactory();

	private ColumnFactory columnFactory = new ColumnFactory();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldIntersect() {
		Table table1 = createTable1("Table1");
		Table table2 = createTable2("Table2");
		when(tableService.findTableByName("db1", "Table1")).thenReturn(table1);
		when(tableService.findTableByName("db1", "Table2")).thenReturn(table2);
		Table table = intersectionService.intersect("db1", "Table1", "Table2");

		assertEquals(table.getTableName(), "Table1_Table2_intersection");
		verifyColumns(table.getColumns());
		verifyRecords(table.getRecords());
	}

	@Test(expected = IdColumnNotFoundException.class)
	public void intersectShouldRiseExceptionIdNotFound() {
		Table table1 = createTable1("Table1");
		table1.getColumns().remove(createColumnId());
		when(tableService.findTableByName("db1", "Table1")).thenReturn(table1);
		when(tableService.findTableByName("db1", "Table2")).thenReturn(createTable2("Table2"));

		intersectionService.intersect("db1", "Table1", "Table2");
	}

	private Table createTable1(String table1) {
		Table table = tableFactory.create(table1);
		Set<Column> columns = createTable1Columns();
		List<Record> records = createTable1Records();
		table.setRecords(records);
		table.setColumns(columns);
		return table;
	}

	private Table createTable2(String table2) {
		Table table = tableFactory.create(table2);
		Set<Column> columns = createTable2Columns();
		List<Record> records = createTable2Records();
		table.setRecords(records);
		table.setColumns(columns);
		return table;
	}

	private Set<Column> createTable1Columns() {
		Set<Column> columns = new HashSet<>();
		columns.add(createColumnId());
		columns.add(createColumnName());
		columns.add(createColumnSurname());
		return columns;
	}

	private Set<Column> createTable2Columns() {
		Set<Column> columns = new HashSet<>();
		columns.add(createColumnId());
		columns.add(createColumnName());
		columns.add(createColumnAge());
		return columns;
	}

	private Column createColumnId() {
		return columnFactory.createId("ID", "Long");
	}

	private Column createColumnName() {
		return columnFactory.create("Name", "String");
	}

	private Column createColumnSurname() {
		return columnFactory.create("Surname", "String");
	}

	private Column createColumnAge() {
		return columnFactory.create("Age", "Long");
	}

	private List<Record> createTable1Records() {
		Record record1 = createTable1Record(1L, "Ivan", "Ivanov");
		Record record2 = createTable1Record(2L, "Petro", "Petrov");
		Record record3 = createTable1Record(3L, "Sidor", "Sidorov");
		Record record4 = createTable1Record(4L, "Vasiliy", "Pupkin");
		Record record5 = createTable1Record(5L, "Nikolay", "Nikolaev");
		return createListRecord(record1, record2, record3, record4, record5);
	}

	private List<Record> createTable2Records() {
		Record record1 = createTable2Record(1L, "Ivan", 12L);
		Record record2 = createTable2Record(2L, "Petro", 15L);
		Record record3 = createTable2Record(3L, "Dmytro", 15L);
		Record record4 = createTable2Record(4L, "Oleg", 20L);
		Record record5 = createTable2Record(5L, "Nikolay", 55L);
		return createListRecord(record1, record2, record3, record4, record5);
	}

	private List<Record> createListRecord(Record... records) {
		List<Record> result = new ArrayList<>();
		Collections.addAll(result, records);
		return result;
	}

	private Record createTable1Record(Long id, String name, String surname) {
		Record record = new Record();
		Map<Column, Field> recordMap = new HashMap<>();
		recordMap.put(createColumnId(), new Field(id));
		recordMap.put(createColumnName(), new Field(name));
		recordMap.put(createColumnSurname(), new Field(surname));
		record.setFields(recordMap);
		return record;
	}

	private Record createTable2Record(Long id, String name, Long age) {
		Record record = new Record();
		Map<Column, Field> recordMap = new HashMap<>();
		recordMap.put(createColumnId(), new Field(id));
		recordMap.put(createColumnName(), new Field(name));
		recordMap.put(createColumnAge(), new Field(age));
		record.setFields(recordMap);
		return record;
	}

	private void verifyColumns(Set<Column> columns) {
		assertEquals(2, columns.size());
		assertTrue(columns.contains(createColumnId()));
		assertTrue(columns.contains(createColumnName()));
	}

	public void verifyRecords(List<Record> records) {
		assertEquals(3, records.size());
		assertTrue(records.contains(createResultRecord(1L, "Ivan")));
		assertTrue(records.contains(createResultRecord(2L, "Petro")));
		assertTrue(records.contains(createResultRecord(5L, "Nikolay")));
	}

	private Record createResultRecord(Long id, String name) {
		Record record = new Record();
		Map<Column, Field> recordMap = new HashMap<>();
		recordMap.put(createColumnId(), new Field(id));
		recordMap.put(createColumnName(), new Field(name));
		record.setFields(recordMap);
		return record;
	}
}
