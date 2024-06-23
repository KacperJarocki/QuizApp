package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.entities.Client;
import org.studia.javaproadmin.entities.Roles;
import org.studia.javaproadmin.services.InternetService;

import java.io.IOException;
import java.net.MalformedURLException;

public class LoginController{
    MainController mainController;
    @FXML
    private Button LogIn;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Password;

    private InternetService internetService;

	public LoginController() throws MalformedURLException {
	}

	@FXML
    void logIn(ActionEvent event) throws IOException {
        Pane pane = null;
        internetService = new InternetService();
        Client client = new Client();
        client.setAlbumNumber(Login.getText());
        client.setPassword(Password.getText());
        Roles role = internetService.sendLoginRequest(client);
        if (role == Roles.NONE) {
            System.out.println("Invalid login or password");
        } else if(role == Roles.STUDENT){
            System.out.println("Logged in as " + role);
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("studentLogged.fxml"));
            pane = fxmlLoader.load();
            mainController.setNewPane(pane);
            StudentLoggedController loggedController = fxmlLoader.getController();
            loggedController.setMainController(mainController);
            loggedController.setInternetService(internetService);
        } else {
            System.out.println("Logged in as " + role);
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("adminLogged.fxml"));
            pane = fxmlLoader.load();
            mainController.setNewPane(pane);
            AdminLoggedController loggedController = fxmlLoader.getController();
            loggedController.setMainController(mainController);
            loggedController.setInternetService(internetService);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}