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
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("logged.fxml"));
        Pane pane = null;
        internetService = new InternetService();
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Client client = new Client();
        client.setAlbumNumber(Login.getText());
        client.setPassword(Password.getText());
        Roles role = internetService.sendLoginRequest(client);
        if (role == Roles.NONE) {
            System.out.println("Invalid login or password");
        } else if(role == Roles.STUDENT){
           System.out.println("You do not have permisions");
        } else {
            System.out.println("Logged in as " + role);
            mainController.setNewPane(pane);
            LoggedController loggedController = fxmlLoader.getController();
            loggedController.setMainController(mainController);
            loggedController.setInternetService(internetService);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}