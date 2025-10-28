package com.miniproject2.store;

import com.miniproject2.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class UserStore {
    private static final ObservableList<User> users = FXCollections.observableArrayList();

    static {
        users.addAll(
            new User("elie", "pass123", "Elie Azzi"),
            new User("maria", "pass123", "Maria N."),
            new User("hadi", "pass123", "Hadi S.")
        );
    }

    public static Optional<User> login(String username, String password) {
        return users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findFirst();
    }
}
