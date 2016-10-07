package ua.kiev.unicyb.tcct.util;

import ua.kiev.unicyb.tcct.domain.table.Table;

/**
 * @Author Denys Storozhenko.
 */
public class TableFactory extends AbstractFactory<Table> {
	@Override
	public Table create(String name) {
		Table table = new Table();
		table.setTableName(name);
		return table;
	}
}
