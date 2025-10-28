package com.miniproject2.controller;

import com.miniproject2.ViewNavigator;
import com.miniproject2.model.User;
import com.miniproject2.session.Session;
import com.miniproject2.store.UserStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.Optional;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void onLogin(ActionEvent e) {
        String u = usernameField.getText();
        String p = passwordField.getText();
        Optional<User> user = UserStore.login(u, p);
        if (user.isPresent()) {
            Session.setCurrentUser(user.get());
            Scene home = ViewNavigator.loadScene("home.fxml", 720, 420);
            javafx.stage.Stage stage = (javafx.stage.Stage) usernameField.getScene().getWindow();
            stage.setScene(home);
            stage.show();
        } else {
            errorLabel.setText("Invalid credentials");
        }
    }
}
