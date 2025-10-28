package com.miniproject2.controller;

import com.miniproject2.ViewNavigator;
import com.miniproject2.session.Session;
import com.miniproject2.store.ClientStore;
import com.miniproject2.store.ProductStore;
import com.miniproject2.store.OrderStore;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HomeController {

    @FXML private Label welcomeLabel;
    @FXML private FlowPane buttonsPane;
    @FXML private Label countClients;
    @FXML private Label countProducts;
    @FXML private Label countOrders;

    // Optional: if you want to create buttons dynamically for new entities, call addNavButton(...) in initialize()

    @FXML
    private void initialize() {
        var user = Session.getCurrentUser();
        welcomeLabel.setText(user != null ? "Welcome, " + user.getFullName() : "Welcome");

        // Update counters
        updateCounters();

        // Example of adding a new entity button dynamically (uncomment to use):
        // addNavButton("🏷️ Suppliers", "suppliers.fxml", 900, 560);
    }

    private void updateCounters() {
        if (countClients != null)  countClients.setText("Clients: "  + ClientStore.all().size());
        if (countProducts != null) countProducts.setText("Products: " + ProductStore.all().size());
        if (countOrders != null)   countOrders.setText("Orders: "   + OrderStore.all().size());
    }

    // Convenience method for dynamic buttons (optional)
    private void addNavButton(String label, String fxml, double w, double h) {
        Button btn = new Button(label);
        btn.getStyleClass().add("pill-btn");
        btn.setMinWidth(150);
        btn.setMinHeight(48);
        btn.setOnAction(e -> switchTo(fxml, w, h));
        buttonsPane.getChildren().add(btn);
    }

    // Navigation helpers
    private void switchTo(String fxml, double w, double h) {
        Scene scene = ViewNavigator.loadScene(fxml, w, h);
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML private void onLogout() {
        Session.clear();
        switchTo("login.fxml", 420, 300);
    }

    @FXML private void openClients()  { switchTo("clients.fxml",  900, 560); }
    @FXML private void openProducts() { switchTo("products.fxml", 900, 560); }
    @FXML private void openOrders()   { switchTo("orders.fxml",  1000, 600); }
}
