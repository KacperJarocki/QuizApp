package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.studia.javaproadmin.entities.Client;

public class AdminApplicationController {

    @FXML
    private Button LogIn;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Password;

    @FXML
    void logIn(ActionEvent event) {
        Client client = new Client();
        client.setAlbumNumber(Login.getText());
        client.setPassword(Password.getText());
    }

}