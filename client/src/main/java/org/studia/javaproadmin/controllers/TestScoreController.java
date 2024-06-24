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
//todo: implement setScore method add a mark acording to test standards
	public void setScore(int score) {
		grade.setText("Your score is: " + score);
	}

	public void setClientAlbumNumber(String clientAlbumNumber) {
		this.clientAlbumNumber = clientAlbumNumber;
	}
}
