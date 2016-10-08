package ua.kiev.unicyb.tcct.ui.desktop.controller.database;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kiev.unicyb.tcct.domain.database.Database;
import ua.kiev.unicyb.tcct.service.database.DatabaseService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;

/**
 * @Author Denys Storozhenko.
 */
@org.springframework.stereotype.Controller
public class CreateDatabaseController extends AbstractController {
	@Autowired
	private DatabaseService databaseService;

	@FXML
	private TextField textField;

	@FXML
	private void createDatabase() {
		try {
			Database database = new Database();
			database.setDatabaseName(textField.getText());
			databaseService.create(database);
			Stage stage = (Stage) textField.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}
}
