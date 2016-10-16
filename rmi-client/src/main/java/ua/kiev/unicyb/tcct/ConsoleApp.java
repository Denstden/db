package ua.kiev.unicyb.tcct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.resource.ColumnResource;
import ua.kiev.unicyb.tcct.resource.DatabaseResource;
import ua.kiev.unicyb.tcct.resource.RecordResource;
import ua.kiev.unicyb.tcct.resource.TableResource;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class ConsoleApp {
	private static final Scanner scanner = new Scanner(System.in);

	@Autowired
	private DatabaseResource databaseResource;

	@Autowired
	private TableResource tableResource;

	@Autowired
	private ColumnResource columnResource;

	@Autowired
	private RecordResource recordResource;

	public void run() {
		in:
		while (true) {
			printCommands();
			nextCommand:
			while (scanner.hasNext()) {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1: {
					System.out.println(databaseResource.findAll());
					break nextCommand;
				}
				case 2: {
					createDatabase();
					break nextCommand;
				}
				case 3: {
					findAllTablesInTheDb();
					break nextCommand;
				}
				case 4: {
					createTable();
					break nextCommand;
				}
				case 5: {
					createColumn();
					break nextCommand;
				}
				case 6: {
					createRecord();
					break nextCommand;
				}
				case 7: {
					intersect();
					break nextCommand;
				}
				case 0: {
					break in;
				}
				}
			}
		}
	}

	private void printCommands() {
		System.out.println("=================AVAILABLE COMMANDS==========================");
		System.out.println("Find all databases - 1");
		System.out.println("Create database - 2");
		System.out.println("Find all tables in the database - 3");
		System.out.println("Create table - 4");
		System.out.println("Create column - 5");
		System.out.println("Create record - 6");
		System.out.println("Intersect tables - 7");
		System.out.println("Exit - 0");
		System.out.println("=================AVAILABLE COMMANDS==========================");
	}

	private void createDatabase() {
		System.out.println("Enter database name");
		if (scanner.hasNext()) {
			String dbName = scanner.next();
			try {
				databaseResource.createDatabase(dbName);
				System.out.println("Database with name '" + dbName + "' was created.");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void findAllTablesInTheDb() {
		System.out.println("Enter database name (" + arrayToCommaSeparatedString(databaseResource.dbNames()) + ")");
		if (scanner.hasNext()) {
			String dbName = scanner.next();
			try {
				Iterable<Table> tables = tableResource.getAllTables(dbName);
				System.out.println(tables);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void createTable() {
		System.out.println("Enter database name (" + arrayToCommaSeparatedString(databaseResource.dbNames()) + ")");
		if (scanner.hasNext()) {
			String dbName = scanner.next();
			System.out.println("Enter table name");
			if (scanner.hasNext()) {
				String tableName = scanner.next();
				try {
					tableResource.createTable(dbName, tableName);
					System.out.println(
							"Table with name '" + tableName + "' in the database '" + dbName + "' was created.");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	private void createColumn() {
		System.out.println("Enter database name (" + arrayToCommaSeparatedString(databaseResource.dbNames()) + ")");
		if (scanner.hasNext()) {
			String dbName = scanner.next();
			System.out.println(
					"Enter table name (" + arrayToCommaSeparatedString(tableResource.tableNames(dbName)) + ")");
			if (scanner.hasNext()) {
				String tableName = scanner.next();
				System.out.println("Enter column name");
				if (scanner.hasNext()) {
					String columnName = scanner.next();
					System.out.println("Enter column type (String, Long, Integer, Double, Picture");
					if (scanner.hasNext()) {
						SupportedType type = convertType(scanner.next());
						System.out.println("Column is ID? (Y/N)");
						if (scanner.hasNext()) {
							boolean isId = scanner.next().equalsIgnoreCase("y");
							System.out.println("Column may be null? (Y/N)");
							if (scanner.hasNext()) {
								boolean nullable = scanner.next().equalsIgnoreCase("y");
								try {
									columnResource.createColumn(dbName, tableName, columnName, isId, nullable, type);
									System.out.println("Column with name '" + columnName + "' in the table '" +
											tableName + "' in the database '" + dbName + "' was created.");
								} catch (Exception e) {
									System.err.println(e.getMessage());
								}
							}
						}
					}
				}
			}
		}
	}

	private void createRecord() {
		System.out.println("Enter database name (" + arrayToCommaSeparatedString(databaseResource.dbNames()) + ")");
		if (scanner.hasNext()) {
			String dbName = scanner.next();
			System.out.println(
					"Enter table name (" + arrayToCommaSeparatedString(tableResource.tableNames(dbName)) + ")");
			if (scanner.hasNext()) {
				String tableName = scanner.next();
				String[] columns = columnResource.columnNames(dbName, tableName);
				Record record = constructRecord(dbName, tableName, columns);
				try {
					recordResource.addRecord(dbName, tableName, record);
					System.out.println("Record in the table '" +
							tableName + "' in the database '" + dbName + "' was created.");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	private Record constructRecord(String dbName, String tableName, String... strings) {
		Map<String, String> fields = new HashMap<>();
		List<String> columnNames = new ArrayList<>();
		Collections.addAll(columnNames, strings);
		int columnsCount = strings.length;
		while (columnsCount > 0) {
			String[] str = new String[columnNames.size()];
			System.out.println("Enter column name (" + arrayToCommaSeparatedString(columnNames.toArray(str)) + ")");
			String columnName = scanner.next();
			System.out.println("Enter value: ");
			String value = scanner.next();
			fields.put(columnName, value);
			columnsCount--;
			columnNames.remove(columnName);
			System.out.println("Continue? (Y/N) ");
			if (!scanner.next().equalsIgnoreCase("y")) {
				break;
			}
		}
		Iterable<Column> columns = columnResource.getAllColumns(dbName, tableName);
		Map<Column, Field> record = convertStringMapToRecordMap(fields, columns);
		Record result = new Record();
		result.setFields(record);
		return result;
	}

	private Map<Column, Field> convertStringMapToRecordMap(Map<String, String> fields, Iterable<Column> columns) {
		Map<Column, Field> result = new HashMap<>();
		for (Column column : columns) {
			String columnName = column.getColumnName();
			if (fields.containsKey(columnName)) {
				result.put(column, new Field(fields.get(columnName)));
			}
		}
		return result;
	}

	private void intersect() {
		System.out.println("Enter database1 name (" + arrayToCommaSeparatedString(databaseResource.dbNames()) + ")");
		String db1Name = scanner.next();
		System.out
				.println("Enter table1 name (" + arrayToCommaSeparatedString(tableResource.tableNames(db1Name)) + ")");
		String table1Name = scanner.next();
		System.out.println("Enter database2 name (" + arrayToCommaSeparatedString(databaseResource.dbNames()) + ")");
		String db2Name = scanner.next();
		System.out
				.println("Enter table2 name (" + arrayToCommaSeparatedString(tableResource.tableNames(db2Name)) + ")");
		String table2Name = scanner.next();
		try {
			Table table = tableResource.intersect(db1Name, db2Name, table1Name, table2Name);
			System.out.println("Intersection of tables '" + table1Name + "' and '" + table2Name + "':");
			System.out.println(table);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private String arrayToCommaSeparatedString(String... strings) {
		StringBuilder result = new StringBuilder();
		for (String s : strings) {
			result.append(s).append(", ");
		}
		if (result.length() > 0) {
			return result.substring(0, result.length() - 2);
		} else {
			return "";
		}
	}

	private SupportedType convertType(String columnType) {
		switch (columnType.toLowerCase()) {
		case "string":
			return SupportedType.STRING;
		case "long":
			return SupportedType.LONG;
		case "integer":
			return SupportedType.INTEGER;
		case "double":
			return SupportedType.DOUBLE;
		case "picture":
			return SupportedType.PICTURE;
		default:
			return SupportedType.STRING;
		}
	}
}
