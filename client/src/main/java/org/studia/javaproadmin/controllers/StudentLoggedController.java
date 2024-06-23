package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.studia.javaproadmin.services.InternetService;

public class StudentLoggedController {
	MainController mainController;
	InternetService internetService;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	@FXML
	public void buttonStartTest(ActionEvent actionEvent) {
	}
	@FXML
	public void buttonShowMarks(ActionEvent actionEvent) {
	}
}
