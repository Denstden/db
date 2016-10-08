package ua.kiev.unicyb.tcct.ui.desktop.controller;

import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractController implements Controller {
	private Node view;

	@Override
	public Node getView() {
		return view;
	}

	@Override
	public void setView(Node view) {
		this.view = view;
	}

	public void close() {
		Stage stage = (Stage) view.getScene().getWindow();
		stage.close();
	}
}