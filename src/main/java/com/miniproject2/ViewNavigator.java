package com.miniproject2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewNavigator {
    public static Scene loadScene(String fxml, double w, double h) {
        try {
            Parent root = FXMLLoader.load(ViewNavigator.class.getResource("/com/miniproject2/" + fxml));
            Scene scene = new Scene(root, w, h);
            scene.getStylesheets().add(ViewNavigator.class.getResource("/com/miniproject2/styles.css").toExternalForm());
            return scene;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load FXML: " + fxml, e);
        }
    }
}
