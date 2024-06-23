package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.entities.Client;
import org.studia.javaproadmin.entities.Roles;
import org.studia.javaproadmin.services.InternetService;

import java.io.IOException;


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
		try {
			internetService.sendAddUserRequest(client);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		info.setText("User added");
		name.clear();
		surname.clear();
		email.clear();
		password.clear();
		albumNumber.clear();
	}
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	@FXML
	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("adminLogged.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainController.setNewPane(pane);
		AdminLoggedController controller = loader.getController();
		controller.setMainController(mainController);
	}
}
