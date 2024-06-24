package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import org.studia.javaproadmin.services.InternetService;

public class ShowMarksViewController {
	MainController mainController;
	InternetService internetService;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	private String calculateGrade(int score) {
		score = score / 20 * 100;
		if (score >= 95) {
			return "twoja ocena 5.0";
		} else if (score >= 90) {
			return "twoja ocena 4.5";
		} else if (score >= 80) {
			return "twoja ocena 4.0";
		} else if (score >= 70) {
			return "twoja ocena 3.5";
		} else if (score >= 50) {
			return "twoja ocena 3.0";
		} else {
			return "twoja ocena 2.0";
		}
	}

	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("adminLogged.fxml"));
		try {
			mainController.setNewPane(loader.load());
			AdminLoggedController adminLoggedController = loader.getController();
			adminLoggedController.setMainController(mainController);
			adminLoggedController.setInternetService(internetService);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
