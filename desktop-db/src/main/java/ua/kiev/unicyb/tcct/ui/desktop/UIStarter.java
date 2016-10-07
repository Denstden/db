package ua.kiev.unicyb.tcct.ui.desktop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @Author Denys Storozhenko.
 */
public class UIStarter extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		borderPane.setMaxWidth(800);
		Scene scene = new Scene(borderPane, 800, 600);
		Button buttonFindAll = new Button("Find all");

		primaryStage.setTitle("Database");
		primaryStage.setScene(scene);
		primaryStage.show();

		borderPane.setTop(new FlowPane(buttonFindAll));
	}
}
