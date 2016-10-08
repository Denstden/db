package ua.kiev.unicyb.tcct.ui.desktop.controller.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import ua.kiev.unicyb.tcct.domain.table.Table;
import ua.kiev.unicyb.tcct.service.table.TableService;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.record.CreateRecordController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.util.UtilController;
import ua.kiev.unicyb.tcct.ui.desktop.exception.ExceptionHandler;
import ua.kiev.unicyb.tcct.ui.desktop.infrastructure.SpringFxmlLoader;

import static ua.kiev.unicyb.tcct.ui.desktop.Constants.LOW_HEIGHT;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.LOW_WIDTH;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.MAIN_HEIGHT;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.MAIN_WIDTH;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.getProperty;

/**
 * @Author Denys Storozhenko.
 */
@Controller
public class GetTableViewController extends AbstractController implements Initializable {

	@Autowired
	private TableService tableService;

	@Autowired
	private UtilController utilController;

	@FXML
	private ChoiceBox<String> databaseNames;

	@FXML
	private ChoiceBox<String> tableNames;

	@FXML
	private Button getTableView;

	@FXML
	private Button createRecordView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		utilController.initDatabaseNames(databaseNames);
		utilController.loadTableNames(databaseNames.getValue(), tableNames);
		databaseNames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			utilController.loadTableNames(databaseNames.getValue(), tableNames);
		});
		tableNames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			getTableView.setDisable(false);
			createRecordView.setDisable(false);
		});
	}

	public void getTableView() {
		try {
			TableViewController tableViewController =
					(TableViewController) SpringFxmlLoader.load("/view/table-view.fxml");
			Stage stage = new Stage();
			Scene scene = new Scene((Parent) tableViewController.getView(), MAIN_WIDTH, MAIN_HEIGHT);
			stage.setTitle(getProperty("table_view") + " '" + tableNames.getValue() + "'");
			Table table = tableService.findTableByName(databaseNames.getValue(), tableNames.getValue());
			tableViewController.setTable(table);
			tableViewController.fillValues();
			stage.setScene(scene);
			stage.show();
			close();
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		}
	}

	public void createRecord() {
		CreateRecordController createRecordController =
				(CreateRecordController) SpringFxmlLoader.load("/view/create-record.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) createRecordController.getView(), LOW_WIDTH, LOW_HEIGHT);
		stage.setTitle(
				getProperty("create_record") + " " + getProperty("in_table") + " '" + tableNames.getValue() + "'");
		createRecordController.setDatabaseName(databaseNames.getValue());
		createRecordController.setTableName(tableNames.getValue());
		createRecordController.fillValues();
		stage.setScene(scene);
		stage.show();
		close();
	}

	public void setGetTableViewVisible(boolean visible) {
		getTableView.setVisible(visible);
	}

	public void setCreateRecordViewVisible(boolean visible) {
		createRecordView.setVisible(visible);
	}

}
