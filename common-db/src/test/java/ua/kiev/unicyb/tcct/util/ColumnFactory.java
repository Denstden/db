package ua.kiev.unicyb.tcct.util;

import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;

/**
 * @Author Denys Storozhenko.
 */
public class ColumnFactory {

	public Column create(String name, SupportedType type) {
		return new Column(name, type);
	}

	public Column create(String name, boolean nullable, Object defValue, SupportedType type) {
		Column column = new Column(name, type);
		column.setNullable(nullable);
		column.setDefaultValue(defValue);
		return column;
	}

	public Column createId(String name, SupportedType tClass) {
		ID column = new ID(tClass);
		column.setColumnName(name);
		column.setNullable(false);
		column.setDefaultValue(null);
		return column;
	}
}
