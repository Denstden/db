package ua.kiev.unicyb.tcct.ui.desktop.controller.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.field.Field;
import ua.kiev.unicyb.tcct.domain.record.Record;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.record.RecordService;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;

import static ua.kiev.unicyb.tcct.ui.desktop.infrastructure.Constants.getProperty;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class CreateRecordController extends AbstractController {
	@Autowired
	private TableService tableService;

	@Autowired
	private RecordService recordService;

	@FXML
	private GridPane pane;

	private String databaseName;

	private String tableName;

	public void fillValues() {
		Table table = tableService.findTableByName(databaseName, tableName);
		int row = 1;
		for (Column column : table.getColumns()) {
			pane.add(new Text(column.getColumnName()), 0, row);
			pane.add(new TextField(column.getDefaultValue() == null ? null : column.getDefaultValue().toString()), 1,
					row);
			row++;
		}
		if (row > 1) {
			Button button = new Button(getProperty("create_record"));
			button.setOnAction(event -> addRecordToDatabase(table.getColumns()));
			pane.add(button, 1, ++row);
		}
	}

	private void addRecordToDatabase(Set<Column> columns) {
		try {
			Record record = new Record();
			Map<Column, Field> fields = new HashMap<>();
			int row = 1;
			for (Column column : columns) {
				TextField textField = (TextField) getNodeByRowColumnIndex(row++, 1);
				fields.put(column, new Field(textField.getText()));
			}
			record.setFields(fields);
			recordService.addRecord(databaseName, tableName, record);
			close();
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}

	private Node getNodeByRowColumnIndex(final int row, final int column) {
		Node result = null;
		ObservableList<Node> children = pane.getChildren();

		for (Node node : children) {
			if (pane.getRowIndex(node) == row && pane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
