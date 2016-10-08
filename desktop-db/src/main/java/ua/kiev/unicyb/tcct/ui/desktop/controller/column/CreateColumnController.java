package ua.kiev.unicyb.tcct.ui.desktop.controller.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ua.kiev.unicyb.tcct.domain.column.Column;
import ua.kiev.unicyb.tcct.domain.column.ID;
import ua.kiev.unicyb.tcct.domain.column.SupportedType;
import ua.kiev.unicyb.tcct.service.column.ColumnService;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.util.UtilController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class CreateColumnController extends AbstractController implements Initializable {
	@Autowired
	private TableService tableService;

	@Autowired
	private ColumnService columnService;

	@Autowired
	private UtilController utilController;

	@FXML
	private ChoiceBox<String> databaseNames;

	@FXML
	private ChoiceBox<String> tableNames;

	@FXML
	private ChoiceBox<String> types;

	@FXML
	private TextField columnName;

	@FXML
	private CheckBox nullable;

	@FXML
	private CheckBox isId;

	@FXML
	private TextField defaultValue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		utilController.initDatabaseNames(databaseNames);
		utilController.loadTableNames(databaseNames.getValue(), tableNames);
		databaseNames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			utilController.loadTableNames(databaseNames.getValue(), tableNames);
		});
		tableNames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			unlockFields();
			loadTypes();
		});
	}

	private void unlockFields() {
		columnName.setDisable(false);
		isId.setDisable(false);
		nullable.setDisable(false);
		defaultValue.setDisable(false);
		types.setDisable(false);
	}

	private void loadTypes() {
		if (types.getItems().size() == 0) {
			columnService.getAllSupportedTypes().forEach(type -> types.getItems().add(type.name()));
		}
	}

	public void createColumn() {
		try {
			Column column = fillColumnFields();
			validate(column);

			String selectedDb = databaseNames.getSelectionModel().getSelectedItem();
			String selectedTable = tableNames.getSelectionModel().getSelectedItem();

			columnService.addColumn(selectedDb, selectedTable, column);
			close();
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}

	private Column fillColumnFields() {
		Column column;
		if (isId.isSelected()) {
			column = new ID();
		} else {
			column = new Column();
		}
		column.setColumnName(columnName.getText());
		column.setNullable(nullable.isSelected());
		column.setType(SupportedType.valueOf(types.getSelectionModel().getSelectedItem()));
		column.setDefaultValue(defaultValue.getText());
		return column;
	}

	private void validate(Column column) {
		if (StringUtils.isEmpty(column.getColumnName()) || column.getType() == null) {
			throw new RuntimeException("Name and type should be not empty.");
		}
	}
}
