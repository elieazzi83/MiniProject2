package com.miniproject2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = ViewNavigator.loadScene("login.fxml", 420, 300);
        stage.setTitle("Mini Project 2");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
