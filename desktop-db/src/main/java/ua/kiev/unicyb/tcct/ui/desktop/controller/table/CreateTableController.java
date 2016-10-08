package ua.kiev.unicyb.tcct.ui.desktop.controller.table;

import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.util.UtilController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;

/**
 * @Author Denys Storozhenko.
 */
@org.springframework.stereotype.Controller
public class CreateTableController extends AbstractController implements Initializable {
	@Autowired
	private TableService tableService;

	@Autowired
	private UtilController utilController;

	@FXML
	private TextField tableName;

	@FXML
	private ChoiceBox<String> databaseNames;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		utilController.initDatabaseNames(databaseNames);
	}

	@FXML
	public void createTable() {
		try {
			Table table = new Table();
			table.setTableName(tableName.getText());
			String selectedDbName = databaseNames.getSelectionModel().getSelectedItem();
			tableService.addTable(selectedDbName, table);
			close();
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}
}
