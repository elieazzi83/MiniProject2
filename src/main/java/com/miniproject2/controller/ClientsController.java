package com.miniproject2.controller;

import com.miniproject2.ViewNavigator;
import com.miniproject2.model.Client;
import com.miniproject2.store.ClientStore;
import com.miniproject2.util.Validation;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class ClientsController {

    @FXML private TableView<Client> table;
    @FXML private TableColumn<Client, String> colName;
    @FXML private TableColumn<Client, String> colEmail;
    @FXML private TableColumn<Client, String> colPhone;

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;

    @FXML private TextField searchField;
    @FXML private Label statusLabel;

    private FilteredList<Client> filtered;

    @FXML
    private void initialize() {
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colPhone.setCellValueFactory(data -> data.getValue().phoneProperty());

        filtered = new FilteredList<>(ClientStore.all(), s -> true);
        table.setItems(filtered);

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                nameField.setText(sel.getName());
                emailField.setText(sel.getEmail());
                phoneField.setText(sel.getPhone());
            }
        });
    }

    @FXML
    private void onSearch() {
        String q = searchField.getText() == null ? "" : searchField.getText().toLowerCase();
        filtered.setPredicate(c -> c.getName().toLowerCase().contains(q) || c.getEmail().toLowerCase().contains(q));
    }

    @FXML
    private void onCreate() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        if (!Validation.notBlank(name) || !Validation.emailLike(email) || !Validation.phoneLike(phone)) {
            statusLabel.setText("Invalid inputs");
            return;
        }
        ClientStore.create(name, email, phone);
        statusLabel.setText("Created");
        clearForm();
    }

    @FXML
    private void onUpdate() {
        Client sel = table.getSelectionModel().getSelectedItem();
        if (sel == null) { statusLabel.setText("Select a row"); return; }
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        if (!Validation.notBlank(name) || !Validation.emailLike(email) || !Validation.phoneLike(phone)) {
            statusLabel.setText("Invalid inputs");
            return;
        }
        boolean ok = ClientStore.update(sel.getId(), name, email, phone);
        statusLabel.setText(ok ? "Updated" : "Not found");
        table.refresh();
    }

    @FXML
    private void onDelete() {
        Client sel = table.getSelectionModel().getSelectedItem();
        if (sel == null) { statusLabel.setText("Select a row"); return; }
        boolean ok = ClientStore.delete(sel.getId());
        statusLabel.setText(ok ? "Deleted" : "Not found");
        clearForm();
    }

    @FXML
    private void backToHome() {
        Scene scene = ViewNavigator.loadScene("home.fxml", 720, 420);
        javafx.stage.Stage stage = (javafx.stage.Stage) table.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    private void clearForm() {
        nameField.clear(); emailField.clear(); phoneField.clear();
        table.getSelectionModel().clearSelection();
    }
}
