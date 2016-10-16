package ua.kiev.unicyb.tcct.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.service.column.ColumnService;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class ColumnResourceImpl implements ColumnResource {
	@Autowired
	@Qualifier(value = "remoteColumnService")
	private ColumnService columnService;

	@Override
	public void createColumn(String dbName, String tableName, String columnName, Boolean isId, Boolean isNullable,
			SupportedType type) {
		Column column;
		if (isId) {
			column = new ID();
		} else {
			column = new Column();
		}
		column.setNullable(isNullable);
		column.setColumnName(columnName);
		column.setType(type);
		columnService.addColumn(dbName, tableName, column);
	}

	@Override
	public Iterable<Column> getAllColumns(String dbName, String tableName) {
		return columnService.getAllColumns(dbName, tableName);
	}

	@Override
	public String[] columnNames(String dbName, String tableName) {
		Iterable<Column> tables = columnService.getAllColumns(dbName, tableName);
		List<String> res = new ArrayList<>();
		for (Column column : tables) {
			res.add(column.getColumnName());
		}
		String[] names = new String[res.size()];
		return res.toArray(names);
	}
}
