package org.studia.javaproadmin.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.services.InternetService;

public class LoggedController {
	MainController mainController;
	FXMLLoader fmxmlLoader;
	Pane pane;
	InternetService internetService;

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
		ShowQuestionViewController showQuestionViewController = fmxmlLoader.getController();
		showQuestionViewController.setMainController(mainController);
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
		AddQuestionFormController addQuestionFormController = fmxmlLoader.getController();
		addQuestionFormController.setMainController(mainController);
		addQuestionFormController.setInternetService(internetService);
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
		ShowMarksViewController showMarksViewController = fmxmlLoader.getController();
		showMarksViewController.setMainController(mainController);
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
		AddUserFormController addUserFormController = fmxmlLoader.getController();
		addUserFormController.setMainController(mainController);
	}

	public void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
}
