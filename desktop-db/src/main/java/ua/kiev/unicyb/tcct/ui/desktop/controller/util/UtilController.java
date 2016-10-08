package ua.kiev.unicyb.tcct.ui.desktop.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.scene.control.ChoiceBox;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class UtilController extends AbstractController {
	@Autowired
	private DatabaseService databaseService;

	@Autowired
	private TableService tableService;

	public void initDatabaseNames(ChoiceBox<String> databaseNames) {
		try {
			databaseService.findAll().forEach(db -> databaseNames.getItems().add(db.getDatabaseName()));
			if (databaseNames.getItems().size() > 0) {
				databaseNames.getSelectionModel().select(0);
			}
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}

	public void loadTableNames(String databaseName, ChoiceBox<String> tableNames) {
		try {
			tableNames.getItems().clear();
			tableService.findAllTables(databaseName).forEach(table -> tableNames.getItems().add(table.getTableName()));
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}
}
