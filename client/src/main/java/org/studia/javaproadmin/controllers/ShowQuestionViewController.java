package org.studia.javaproadmin.controllers;

import org.studia.javaproadmin.services.InternetService;

public class ShowQuestionViewController {
	MainController mainController;
	InternetService internetService;
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
}
