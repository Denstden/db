package ua.kiev.unicyb.tcct.ui.desktop.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import ua.kiev.unicyb.tcct.service.database.DatabaseService;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class MockUtil {
	@Autowired
	private DatabaseService databaseService;

	private boolean flag = false;

	public void mockDatabases() {
		if (!flag) {
			flag = true;
			mockDatabase1();
		}
		/*mockDatabase2();
		mockDatabase3();*/
	}

	private void mockDatabase1() {
		Database database = new Database();
		database.setDatabaseName("DB1");
		database.setTables(createTablesDb1());
		databaseService.create(database);
	}

	private List<Table> createTablesDb1() {
		List<Table> tables = new ArrayList<>();
		tables.add(createTable1Db1());
		tables.add(createTable2Db1());
		tables.add(createTable3Db1());
		return tables;
	}

	private Table createTable1Db1() {
		Table table = new Table();
		table.setTableName("table1Db1");

		Set<Column> columns = new HashSet<>();

		ID column1 = new ID();
		column1.setType(SupportedType.LONG);
		column1.setColumnName("ID");
		column1.setNullable(false);
		columns.add(column1);

		Column column2 = new Column();
		column2.setType(SupportedType.STRING);
		column2.setColumnName("NAME");
		column2.setNullable(false);
		columns.add(column2);

		Record record1 = new Record();
		Map<Column, Field> fields1 = new HashMap<>();
		fields1.put(column1, new Field(1));
		fields1.put(column2, new Field("Ivan"));
		record1.setFields(fields1);

		Record record2 = new Record();
		Map<Column, Field> fields2 = new HashMap<>();
		fields2.put(column1, new Field(2));
		fields2.put(column2, new Field("Petro"));
		record2.setFields(fields2);

		List<Record> records = new ArrayList<Record>() {{
			add(record1);
			add(record2);
		}};

		table.setColumns(columns);
		table.setRecords(records);
		return table;
	}

	private Table createTable2Db1() {
		Table table = new Table();
		table.setTableName("table2Db1");

		Set<Column> columns = new HashSet<>();

		ID column1 = new ID();
		column1.setType(SupportedType.LONG);
		column1.setColumnName("ID");
		column1.setNullable(false);
		columns.add(column1);

		Column column2 = new Column();
		column2.setType(SupportedType.STRING);
		column2.setColumnName("SURNAME");
		column2.setNullable(true);
		columns.add(column2);

		Record record1 = new Record();
		Map<Column, Field> fields1 = new HashMap<>();
		fields1.put(column1, new Field(1));
		fields1.put(column2, new Field("Ivanov"));
		record1.setFields(fields1);

		Record record2 = new Record();
		Map<Column, Field> fields2 = new HashMap<>();
		fields2.put(column1, new Field(2));
		fields2.put(column2, null);
		record2.setFields(fields2);

		List<Record> records = new ArrayList<Record>() {{
			add(record1);
			add(record2);
		}};

		table.setColumns(columns);
		table.setRecords(records);
		return table;
	}

	private Table createTable3Db1() {
		Table table = new Table();
		table.setTableName("table3Db1");

		Set<Column> columns = new HashSet<>();

		ID column1 = new ID();
		column1.setType(SupportedType.LONG);
		column1.setColumnName("ID");
		column1.setNullable(false);
		columns.add(column1);

		Column column2 = new Column();
		column2.setType(SupportedType.STRING);
		column2.setColumnName("NAME");
		column2.setNullable(false);
		columns.add(column2);

		Column column3 = new Column();
		column3.setType(SupportedType.STRING);
		column3.setColumnName("SURNAME");
		column3.setNullable(true);
		column3.setDefaultValue("DefaultSurname");
		columns.add(column3);

		Record record1 = new Record();
		Map<Column, Field> fields1 = new HashMap<>();
		fields1.put(column1, new Field(1));
		fields1.put(column2, new Field("Ivan"));
		fields1.put(column3, new Field("Ivanov"));
		record1.setFields(fields1);

		Record record2 = new Record();
		Map<Column, Field> fields2 = new HashMap<>();
		fields2.put(column1, new Field(2));
		fields2.put(column2, new Field("Petro"));
		fields2.put(column3, null);
		record2.setFields(fields2);

		Record record3 = new Record();
		Map<Column, Field> fields3 = new HashMap<>();
		fields3.put(column1, new Field(3));
		fields3.put(column2, new Field("Vasia"));
		fields3.put(column3, new Field("Pupkin"));
		record3.setFields(fields3);

		List<Record> records = new ArrayList<Record>() {{
			add(record1);
			add(record2);
			add(record3);
		}};

		table.setColumns(columns);
		table.setRecords(records);
		return table;
	}

/*	private  void mockDatabase2() {
		Database database = new Database();
		database.setDatabaseName("DB2");
		database.setTables(createTablesDb2());
		databaseService.create(database);
	}

	private  List<Table> createTablesDb2() {
		List<Table> tables = new ArrayList<>();
		tables.add(createTable1Db2());
		tables.add(createTable2Db2());
		tables.add(createTable3Db2());
		return tables;
	}

	private  Table createTable1Db2() {
		return null;
	}

	private  Table createTable2Db2() {
		return null;
	}

	private  Table createTable3Db2() {
		return null;
	}

	private  void mockDatabase3() {
		Database database = new Database();
		database.setDatabaseName("DB3");
		database.setTables(createTablesDb3());
		databaseService.create(database);
	}

	private  List<Table> createTablesDb3() {
		List<Table> tables = new ArrayList<>();
		tables.add(createTable1Db3());
		tables.add(createTable2Db3());
		tables.add(createTable3Db3());
		return tables;
	}

	private  Table createTable3Db3() {
		return null;
	}

	private  Table createTable2Db3() {


	}

	private  Table createTable1Db3() {


	}*/
}
