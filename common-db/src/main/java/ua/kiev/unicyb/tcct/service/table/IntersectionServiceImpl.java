package ua.kiev.unicyb.tcct.service.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.exception.IdColumnNotFoundException;

/**
 * @Author Denys Storozhenko.
 */
@Service
public class IntersectionServiceImpl implements IntersectionService {
	@Autowired
	private TableService tableService;

	@Override
	public Table intersect(String databaseName, String tableName1, String tableName2) {
		Table table1 = tableService.findTableByName(databaseName, tableName1);
		Table table2 = tableService.findTableByName(databaseName, tableName2);

		return intersectByColumn(table1, table2);
	}

	private Table intersectByColumn(Table table1, Table table2) {
		Table table = new Table();
		table.setTableName(table1.getTableName() + "_" + table2.getTableName() + "_intersection");
		Set<Column> commonColumns = findCommonColumns(table1.getColumns(), table2.getColumns());
		List<Record> resultRecords =
				findCommonRecordsByColumns(table1.getRecords(), table2.getRecords(), commonColumns);
		table.setColumns(commonColumns);
		table.setRecords(resultRecords);
		return table;
	}

	private Set<Column> findCommonColumns(Set<Column> columns, Set<Column> columns1) {
		Set<Column> result = new HashSet<>(columns);
		result.retainAll(columns1);
		checkIdColumn(result);
		return result;
	}

	private void checkIdColumn(Set<Column> result) {
		for (Column column : result) {
			if (column.isId())
				return;
		}
		throw new IdColumnNotFoundException("intersection");
	}

	private List<Record> findCommonRecordsByColumns(List<Record> table1, List<Record> table2,
			Set<Column> commonColumns) {
		List<Record> recordsTable1 = filterRecordsByColumns(table1, commonColumns);
		List<Record> recordsTable2 = filterRecordsByColumns(table2, commonColumns);
		List<Record> resultRecords = new ArrayList<>();
		resultRecords.addAll(recordsTable1);
		resultRecords.retainAll(recordsTable2);
		return resultRecords;
	}

	private List<Record> filterRecordsByColumns(List<Record> records, Set<Column> commonColumns) {
		List<Record> result = new ArrayList<>();
		for (Record record : records) {
			Record record1 = new Record();
			record1.setFields(filterRecordByColumns(commonColumns, record));
			result.add(record1);
		}
		return result;
	}

	private Map<Column, Field> filterRecordByColumns(Set<Column> commonColumns, Record record) {
		Map<Column, Field> newFields = new HashMap<>(record.getFields());
		Iterator<Map.Entry<Column, Field>> iterator = newFields.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Column, Field> entry = iterator.next();
			if (!commonColumns.contains(entry.getKey())) {
				iterator.remove();
			}
		}
		return newFields;
	}
}
