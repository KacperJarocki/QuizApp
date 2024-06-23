package org.studia.javaproadmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("Main.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Admin Panel");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}