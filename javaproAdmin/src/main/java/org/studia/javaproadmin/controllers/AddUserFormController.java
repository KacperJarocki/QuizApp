package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.studia.javaproadmin.entities.Client;
import org.studia.javaproadmin.entities.Roles;
import org.studia.javaproadmin.services.InternetService;


public class AddUserFormController {
	MainController mainController;
	InternetService internetService;
	public void initialize() {
		Role.setItems(Roles.getRoles());
	}
	@FXML
	private ComboBox<Roles> Role;

	@FXML
	private TextField albumNumber;

	@FXML
	private TextField email;

	@FXML
	private Label info;

	@FXML
	private TextField name;

	@FXML
	private TextField password;

	@FXML
	private TextField surname;

	@FXML
	void addUserButton(ActionEvent event) {
		Client client = new Client(
				name.getText(),
				surname.getText(),
				Role.getValue(),
				email.getText(),
				password.getText(),
				albumNumber.getText()
		);
		internetService.sendAddUserRequest(client);
		info.setText("User added");
	}
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
}
