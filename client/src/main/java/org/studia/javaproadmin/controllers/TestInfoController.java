package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.services.InternetService;

import java.io.IOException;

public class TestInfoController {
	MainController mainController;
	InternetService internetService;
	long testID;
	Pane pane = null;
	FXMLLoader loader;
	String clientAlbumNumber;
	void setClientAlbumNumber(String clientAlbumNumber) {
		this.clientAlbumNumber = clientAlbumNumber;
	}
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	void setTestID(long test) {
		this.testID = test;
	}
	//TODO:implement timer
	@FXML
	public void startTimer(ActionEvent actionEvent) {
		Test test = null;
		try {
			test = internetService.startTest(testID);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (int i = 0; i < test.getQuestions().size(); i++) {
			test.getQuestions().get(i).printQuestionInfo();
		}
		// start timer
		loader = new FXMLLoader(this.getClass().getResource("test.fxml"));
		try {
			pane = loader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
		TestController testController = loader.getController();
		testController.setMainController(mainController);
		testController.setInternetService(internetService);
		testController.setClientAlbumNumber(clientAlbumNumber);
		testController.setTest(test);
	}
}
