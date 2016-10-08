package ua.kiev.unicyb.tcct.ui.desktop.controller.main;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.kiev.unicyb.tcct.ui.desktop.controller.AbstractController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.column.CreateColumnController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.database.CreateDatabaseController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.table.CreateTableController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.table.GetTableViewController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.table.TableIntersectionController;
import ua.kiev.unicyb.tcct.ui.desktop.controller.util.MockUtil;
import ua.kiev.unicyb.tcct.ui.desktop.infrastructure.SpringFxmlLoader;

import static ua.kiev.unicyb.tcct.ui.desktop.infrastructure.Constants.LOW_HEIGHT;
import static ua.kiev.unicyb.tcct.ui.desktop.infrastructure.Constants.LOW_WIDTH;
import static ua.kiev.unicyb.tcct.ui.desktop.infrastructure.Constants.MIDDLE_HEIGHT;
import static ua.kiev.unicyb.tcct.ui.desktop.infrastructure.Constants.MIDDLE_WIDTH;
import static ua.kiev.unicyb.tcct.ui.desktop.infrastructure.Constants.getProperty;

/**
 * @Author Denys Storozhenko.
 */
@org.springframework.stereotype.Controller
public class MainDatabaseController extends AbstractController {
	@Autowired
	private MockUtil mockUtil;

	public void handleCreateDatabaseAction() {
		CreateDatabaseController createDatabaseController =
				(CreateDatabaseController) SpringFxmlLoader.load("/view/create-db.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) createDatabaseController.getView(), LOW_WIDTH, LOW_HEIGHT);
		stage.setTitle(getProperty("create_database"));
		stage.setScene(scene);
		stage.show();
	}

	public void handleCreateTableAction() {
		CreateTableController createTableController =
				(CreateTableController) SpringFxmlLoader.load("/view/create-table.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) createTableController.getView(), LOW_WIDTH, LOW_HEIGHT);
		stage.setTitle(getProperty("create_table"));
		stage.setScene(scene);
		stage.show();
	}

	public void handleCreateColumnAction() {
		CreateColumnController createColumnController =
				(CreateColumnController) SpringFxmlLoader.load("/view/create-column.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) createColumnController.getView(), MIDDLE_WIDTH, MIDDLE_HEIGHT);
		stage.setTitle(getProperty("create_column"));
		stage.setScene(scene);
		stage.show();
	}

	public void handleViewTableContentAction() {
		GetTableViewController getTableViewController =
				(GetTableViewController) SpringFxmlLoader.load("/view/get-table-view.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) getTableViewController.getView(), LOW_WIDTH, LOW_HEIGHT);
		stage.setTitle(getProperty("get_table_view"));
		stage.setScene(scene);
		getTableViewController.setGetTableViewVisible(true);
		getTableViewController.setCreateRecordViewVisible(false);
		stage.show();
	}

	public void handleCreateRecordAction() {
		GetTableViewController getTableViewController =
				(GetTableViewController) SpringFxmlLoader.load("/view/get-table-view.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) getTableViewController.getView(), LOW_WIDTH, LOW_HEIGHT);
		stage.setTitle(getProperty("create_record"));
		stage.setScene(scene);
		getTableViewController.setGetTableViewVisible(false);
		getTableViewController.setCreateRecordViewVisible(true);
		stage.show();
	}

	public void handleTableIntersectionAction() {
		TableIntersectionController tableIntersectionController =
				(TableIntersectionController) SpringFxmlLoader.load("/view/intersect-table.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) tableIntersectionController.getView(), LOW_WIDTH, LOW_HEIGHT);
		stage.setTitle(getProperty("intersect_tables"));
		stage.setScene(scene);
		stage.show();
	}

	public void mock() {
		mockUtil.mockDatabases();
	}
}
