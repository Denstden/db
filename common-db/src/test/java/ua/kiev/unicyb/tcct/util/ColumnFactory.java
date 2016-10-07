package ua.kiev.unicyb.tcct.util;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;

/**
 * @Author Denys Storozhenko.
 */
public class ColumnFactory{

	public Column create(String name, String type) {
		return new Column(name, type);
	}

	public Column create(String name, boolean nullable, Object defValue, String type) {
		Column column = new Column(name, type);
		column.setNullable(nullable);
		column.setDefaultValue(defValue);
		return column;
	}

	public Column createId(String name, String tClass) {
		ID column = new ID(tClass);
		column.setColumnName(name);
		column.setNullable(false);
		column.setDefaultValue(null);
		return column;
	}
}
