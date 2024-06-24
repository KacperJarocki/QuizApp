package org.studia.javaproadmin.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.services.InternetService;

public class AdminLoggedController {
	MainController mainController;
	FXMLLoader fmxmlLoader;
	Pane pane;
	InternetService internetService;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
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
	@FXML
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
		showMarksViewController.setInternetService(internetService);
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
		addUserFormController.setInternetService(internetService);
	}

	public void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}

	public void goBack(ActionEvent actionEvent) {
		fmxmlLoader = new FXMLLoader(this.getClass().getResource("login.fxml"));
		pane = null;
		try {
			pane = fmxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
		LoginController loginController = fmxmlLoader.getController();
		loginController.setMainController(mainController);
	}
	//todo: implement the method that shows the marks
}
