package ua.kiev.unicyb.tcct.ui.desktop.infrastructure;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ua.kiev.unicyb.tcct.ui.desktop.controller.Controller;

/**
 * @Author Denys Storozhenko.
 */
public class SpringFxmlLoader {
	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");

	public static Controller load(String url) {
		InputStream fxmlStream = null;
		try {
			fxmlStream = SpringFxmlLoader.class.getResourceAsStream(url);
			FXMLLoader loader = new FXMLLoader();
			loader.setControllerFactory(applicationContext::getBean);

			Node view = loader.load(fxmlStream);
			Controller controller = loader.getController();
			controller.setView(view);

			return controller;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (fxmlStream != null) {
				try {
					fxmlStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
