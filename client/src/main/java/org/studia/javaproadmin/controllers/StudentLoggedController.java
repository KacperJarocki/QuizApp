package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.services.InternetService;

import java.io.IOException;

public class StudentLoggedController {
	MainController mainController;
	InternetService internetService;
	FXMLLoader fxmlLoader;
	String  clientAlbumNumber;
	Pane pane = null;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	void setClientAlbumNumber(String clientAlbumNumber) {
		this.clientAlbumNumber = clientAlbumNumber;
	}
	@FXML
	public void buttonStartTest(ActionEvent actionEvent) {
		long testId = 0;
		try {
			testId = internetService.startTestRequest(clientAlbumNumber);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		fxmlLoader = new FXMLLoader(this.getClass().getResource("testInfo.fxml"));
		try {
			pane = fxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
		TestInfoController testInfoController = fxmlLoader.getController();
		testInfoController.setMainController(mainController);
		testInfoController.setInternetService(internetService);
		testInfoController.setClientAlbumNumber(clientAlbumNumber);
		testInfoController.setTestID(testId);
	}

	//todo: implement the method that shows the marks
	@FXML
	public void buttonShowMarks(ActionEvent actionEvent) {
	}

	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("login.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
		LoginController loginController = loader.getController();
		loginController.setMainController(mainController);
	}
}
