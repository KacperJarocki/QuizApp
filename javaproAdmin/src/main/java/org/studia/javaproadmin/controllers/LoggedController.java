package org.studia.javaproadmin.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class LoggedController {
	MainController mainController;
	FXMLLoader fmxmlLoader;
	Pane pane;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void buttonShowQuestions(ActionEvent actionEvent) {
		fmxmlLoader = new FXMLLoader(this.getClass().getResource("showQuestionsView.fxml"));
		pane = null;
		try {
			pane = fmxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
	}

	public void buttonAddQuestion(ActionEvent actionEvent) {
		fmxmlLoader = new FXMLLoader(this.getClass().getResource("AddQuestionForm.fxml"));
		pane = null;
		try {
			pane = fmxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
	}

	public void buttonShowMarks(ActionEvent actionEvent) {
		fmxmlLoader = new FXMLLoader(this.getClass().getResource("ShowMarksView.fxml"));
		pane = null;
		try {
			pane = fmxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
	}

	public void buttonAddClient(ActionEvent actionEvent) {
		fmxmlLoader = new FXMLLoader(this.getClass().getResource("AddUserForm.fxml"));
		pane = null;
		try {
			pane = fmxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
	}
}
