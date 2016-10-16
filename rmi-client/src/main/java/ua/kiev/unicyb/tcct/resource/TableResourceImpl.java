package ua.kiev.unicyb.tcct.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.IntersectionService;
import ua.kiev.unicyb.tcct.service.table.TableService;

/**
 * @Author Denys Storozhenko.
 */
@Component
public class TableResourceImpl implements TableResource {
	@Autowired
	@Qualifier(value = "remoteTableService")
	private TableService tableService;

	@Autowired
	@Qualifier(value = "remoteIntersectionService")
	private IntersectionService intersectionService;

	@Override
	public void createTable(String dbName, String tableName) {
		Table table = new Table();
		table.setTableName(tableName);
		tableService.addTable(dbName, table);
	}

	@Override
	public Iterable<Table> getAllTables(String dbName) {
		return tableService.findAllTables(dbName);
	}

	@Override
	public String[] tableNames(String dbName) {
		Iterable<Table> tables = getAllTables(dbName);
		List<String> res = new ArrayList<>();
		for (Table table : tables) {
			res.add(table.getTableName());
		}
		String[] names = new String[res.size()];
		return res.toArray(names);
	}

	@Override
	public Table intersect(String db1Name, String db2Name, String table1Name, String table2Name) {
		return intersectionService.intersect(db1Name, db2Name, table1Name, table2Name);
	}
}
