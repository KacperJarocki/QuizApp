package org.studia.javaproadmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminApplicationController {

    @FXML
    private Button LogIn;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Password;

    @FXML
    void logIn(ActionEvent event) {
        System.out.println("Login: " + Login.getText());
        System.out.println("Password: " + Password.getText());
        System.out.println("send login credentials");
    }

}