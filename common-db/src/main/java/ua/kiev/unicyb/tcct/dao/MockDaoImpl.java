package ua.kiev.unicyb.tcct.dao;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class MockDaoImpl implements MockDao, Serializable {
	@Override
	public List<Database> mockDb() {
		List<Database> res = new ArrayList<>();

		Table table1 = createTable("TABLE1", createColumnsTable1(), createRecordsTable1());
		Table table2 = createTable("TABLE2", createColumnsTable2(), createRecordsTable2());

		Database database = createDatabase("DB1", new ArrayList<Table>(){{
			add(table1);
			add(table2);
		}});
		res.add(database);
		return res;
	}

	private Database createDatabase(String dbName, List<Table> tables) {
		Database database = new Database();
		database.setDatabaseName(dbName);
		database.setTables(tables);
		return database;
	}

	private Table createTable(String tableName, Set<Column> columns, List<Record> records) {
		Table table = new Table();
		table.setTableName(tableName);
		table.setRecords(records);
		table.setColumns(columns);
		return table;
	}

	private Set<Column> createColumnsTable1() {
		Set<Column> columns = new HashSet<>();
		columns.add(createColumn1Table1());
		columns.add(createColumn2Table1());
		return columns;
	}

	private Column createColumn1Table1() {
		Column column = new ID();
		column.setNullable(true);
		column.setType(SupportedType.STRING);
		column.setColumnName("ID");
		column.setDefaultValue("123");
		return column;
	}

	private Column createColumn2Table1() {
		Column column = new Column();
		column.setNullable(true);
		column.setType(SupportedType.STRING);
		column.setColumnName("Name");
		column.setDefaultValue("Ivan");
		return column;
	}

	private List<Record> createRecordsTable1() {
		List<Record> records = new ArrayList<>();
		records.add(createRecord1Table1());
		records.add(createRecord2Table1());
		return records;
	}

	private Record createRecord1Table1() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn1Table1(), new Field("1"));
		map.put(createColumn2Table1(), new Field("Petro"));
		record.setFields(map);
		return record;
	}

	private Record createRecord2Table1() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn1Table1(), new Field("2"));
		map.put(createColumn2Table1(), new Field("Vasiliy"));
		record.setFields(map);
		return record;
	}

	private Set<Column> createColumnsTable2() {
		Set<Column> columns = new HashSet<>();
		columns.add(createColumn1Table2());
		columns.add(createColumn2Table2());
		columns.add(createColumn3Table2());
		return columns;
	}

	private Column createColumn1Table2() {
		Column column = new ID();
		column.setType(SupportedType.STRING);
		column.setColumnName("ID");
		return column;
	}

	private Column createColumn2Table2() {
		Column column = new Column();
		column.setNullable(true);
		column.setType(SupportedType.STRING);
		column.setColumnName("Name");
		return column;
	}

	private Column createColumn3Table2() {
		Column column = new Column();
		column.setNullable(true);
		column.setType(SupportedType.STRING);
		column.setColumnName("Surname");
		return column;
	}

	private List<Record> createRecordsTable2() {
		List<Record> records = new ArrayList<>();
		records.add(createRecord1Table2());
		records.add(createRecord2Table2());
		records.add(createRecord3Table2());
		return records;
	}

	private Record createRecord1Table2() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn1Table2(), new Field("1"));
		map.put(createColumn2Table2(), new Field("Petro"));
		map.put(createColumn3Table2(), new Field("Petrov"));
		record.setFields(map);
		return record;
	}

	private Record createRecord2Table2() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn1Table2(), new Field("2"));
		map.put(createColumn2Table2(), new Field("Vasiliy"));
		map.put(createColumn3Table2(), new Field("Pupkin"));
		record.setFields(map);
		return record;
	}

	private Record createRecord3Table2() {
		Record record = new Record();
		Map<Column, Field> map = new HashMap<>();
		map.put(createColumn1Table2(), new Field("3"));
		map.put(createColumn2Table2(), new Field("Nikolay"));
		map.put(createColumn3Table2(), new Field("Sidorov"));
		record.setFields(map);
		return record;
	}
}
