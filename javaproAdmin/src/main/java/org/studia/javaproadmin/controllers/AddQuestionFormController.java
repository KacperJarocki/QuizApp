package org.studia.javaproadmin.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class AddQuestionFormController {
	MainController mainController;
	Pane pane;
	FXMLLoader loader;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void buttonChooseFile(ActionEvent actionEvent) {
	}

	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("logged.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainController.setNewPane(pane);
		LoggedController controller = loader.getController();
		controller.setMainController(mainController);
	}

	public void buttonSaveQuestion(ActionEvent actionEvent) {
	}
}
