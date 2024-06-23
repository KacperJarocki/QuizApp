package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.services.InternetService;

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
		long testId = internetService.startTestRequest(clientAlbumNumber);
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
		testInfoController.setTestID(testId);
	}
	@FXML
	public void buttonShowMarks(ActionEvent actionEvent) {
	}
}
