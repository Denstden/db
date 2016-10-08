package ua.kiev.unicyb.tcct.ui.desktop.controller.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.IntersectionService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.util.UtilController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;
import ua.kiev.unicyb.tcct.ui.desktop.infrastructure.SpringFxmlLoader;

import static ua.kiev.unicyb.tcct.ui.desktop.Constants.MAIN_HEIGHT;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.MAIN_WIDTH;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.getProperty;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class TableIntersectionController extends AbstractController implements Initializable {
	@Autowired
	private UtilController utilController;

	@Autowired
	private IntersectionService intersectionService;

	@FXML
	private ChoiceBox<String> databaseNames1;

	@FXML
	private ChoiceBox<String> databaseNames2;

	@FXML
	private ChoiceBox<String> tableNames1;

	@FXML
	private ChoiceBox<String> tableNames2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		utilController.initDatabaseNames(databaseNames1);
		utilController.initDatabaseNames(databaseNames2);
		utilController.loadTableNames(databaseNames1.getValue(), tableNames1);
		utilController.loadTableNames(databaseNames2.getValue(), tableNames2);
		databaseNames1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			utilController.loadTableNames(databaseNames1.getValue(), tableNames1);
		});
		databaseNames2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			utilController.loadTableNames(databaseNames2.getValue(), tableNames2);
		});
	}

	public void intersectTables() {
		try {
			Table table = intersectionService
					.intersect(databaseNames1.getValue(), databaseNames2.getValue(), tableNames1.getValue(),
							tableNames2.getValue());
			TableViewController tableViewController =
					(TableViewController) SpringFxmlLoader.load("/view/table-view.fxml");
			Stage stage = new Stage();
			Scene scene = new Scene((Parent) tableViewController.getView(), MAIN_WIDTH, MAIN_HEIGHT);
			stage.setTitle(getProperty("table_view") + " '" + table.getTableName() + "'");
			tableViewController.setTable(table);
			tableViewController.fillValues();
			stage.setScene(scene);
			stage.show();
			close();
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}
}
