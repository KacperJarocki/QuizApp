package org.studia.javaproadmin.controllers;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.entities.Client;
import org.studia.javaproadmin.entities.Roles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginController{
    URL url = new URL("http://localhost:8080/login");
    MainController mainController;
    @FXML
    private Button LogIn;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Password;

	public LoginController() throws MalformedURLException {
	}

	@FXML
    void logIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("logged.fxml"));
        Pane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Client client = new Client();
        client.setAlbumNumber(Login.getText());
        client.setPassword(Password.getText());
        Roles role = sendLoginRequest(client);
        if (role == Roles.NONE) {
            System.out.println("Invalid login or password");
        } else if(role == Roles.STUDENT){
           System.out.println("You do not have permisions");
        } else {
            System.out.println("Logged in as " + role);
            mainController.setNewPane(pane);
            LoggedController loggedController = fxmlLoader.getController();
            loggedController.setMainController(mainController);
        }
    }
    public Roles sendLoginRequest(Client client) throws IOException {
        Gson gson = new Gson();
        String jsonInputString = gson.toJson(client);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode(); // To ensure the request was successful
        System.out.println("Response Code : " + code);
        if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            System.out.println("Authentication is required.");
            return Roles.NONE;
        }
        Roles role = null;
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Response Body : " + response.toString());

            role = gson.fromJson(response.toString(), Roles.class);
        }

        return role;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}