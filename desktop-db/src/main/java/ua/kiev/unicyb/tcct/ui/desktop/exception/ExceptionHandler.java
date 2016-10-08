package ua.kiev.unicyb.tcct.ui.desktop.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.control.Alert;

/**
 * @Author Denys Storozhenko.
 */
public final class ExceptionHandler {

	public static void handleException(Exception e) {
		Logger logger = LogManager.getLogger(e);
		logger.error(e.getMessage(), e);
		Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
		alert.showAndWait();
	}
}
