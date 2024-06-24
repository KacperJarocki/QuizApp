package org.studia.javaproadmin.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
	@FXML
	StackPane mainPane;
	@FXML
	public void initialize() {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("login.fxml"));
		Pane pane = null;
		try {
			pane = fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		setNewPane(pane);

		LoginController loginController  = fxmlLoader.getController();
		loginController.setMainController(this);
	}

	public void setNewPane(Pane pane) {
		mainPane.getChildren().clear();
		mainPane.getChildren().add(pane);
	}
}
