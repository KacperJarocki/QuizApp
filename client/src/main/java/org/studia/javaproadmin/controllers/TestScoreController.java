package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.services.InternetService;

public class TestScoreController {
	MainController mainController;
	InternetService internetService;
	String clientAlbumNumber;
	@FXML
	private Label grade;
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}

	@FXML
	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("studentLogged.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
		StudentLoggedController studentLoggedController = loader.getController();
		studentLoggedController.setMainController(mainController);
		studentLoggedController.setInternetService(internetService);
		studentLoggedController.setClientAlbumNumber(clientAlbumNumber);
	}
	public void setScore(int score) {
		grade.setText(calculateGrade(score));
	}

	public void setClientAlbumNumber(String clientAlbumNumber) {
		this.clientAlbumNumber = clientAlbumNumber;
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
}
