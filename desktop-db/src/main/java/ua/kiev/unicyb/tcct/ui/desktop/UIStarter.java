package ua.kiev.unicyb.tcct.ui.desktop;

import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.kiev.unicyb.tcct.ui.desktop.controller.main.MainDatabaseController;
import ua.kiev.unicyb.tcct.ui.desktop.infrastructure.SpringFxmlLoader;

import static ua.kiev.unicyb.tcct.ui.desktop.Constants.MAIN_HEIGHT;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.MAIN_WIDTH;
import static ua.kiev.unicyb.tcct.ui.desktop.Constants.getProperty;

/**
 * @Author Denys Storozhenko.
 */
public class UIStarter extends Application {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("uk", "UA"));
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initMainWindow(primaryStage);
	}

	private void initMainWindow(Stage primaryStage) {
		MainDatabaseController mainDatabaseController =
				(MainDatabaseController) SpringFxmlLoader.load("/view/main.fxml");
		Scene scene = new Scene((Parent) mainDatabaseController.getView(), MAIN_WIDTH, MAIN_HEIGHT);
		primaryStage.setTitle(getProperty("project_name"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
