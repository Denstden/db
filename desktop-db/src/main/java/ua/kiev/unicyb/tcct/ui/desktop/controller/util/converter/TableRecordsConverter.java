package ua.kiev.unicyb.tcct.ui.desktop.controller.util.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;

/**
 * @Author Denys Storozhenko.
 */
public final class TableRecordsConverter {
	public static List<String> convert(Record record, TableView tableView) {
		return new ArrayList<>(convertRecord(record, tableView).values());
	}

	private static Map<String, String> convertRecord(Record record, TableView tableView) {
		Map<String, String> result = new HashMap<>();
		ObservableList<TableColumn> tableColumns = tableView.getColumns();
		for (TableColumn tableColumn : tableColumns) {
			String column = tableColumn.getText();
			String value = findValueByColumnName(column, record.getFields());
			result.put(column, value);
		}
		return result;
	}

	private static String findValueByColumnName(String column, Map<Column, Field> fields) {
		for (Map.Entry<Column, Field> entry : fields.entrySet()) {
			if (entry.getKey().getColumnName().equals(column)) {
				if (entry.getValue() != null && entry.getValue().getValue() != null) {
					return entry.getValue().getValue().toString();
				}
			}
		}
		return null;
	}

}
