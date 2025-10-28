package com.miniproject2.controller;

import com.miniproject2.ViewNavigator;
import com.miniproject2.model.*;
import com.miniproject2.store.ClientStore;
import com.miniproject2.store.OrderStore;
import com.miniproject2.store.ProductStore;
import com.miniproject2.util.Validation;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class OrdersController {

    @FXML private TableView<Order> table;
    @FXML private TableColumn<Order, String> colOrderNo;
    @FXML private TableColumn<Order, String> colClient;
    @FXML private TableColumn<Order, String> colProduct;
    @FXML private TableColumn<Order, Number> colQty;
    @FXML private TableColumn<Order, Number> colTotal;

    @FXML private TextField orderNumberField;
    @FXML private ComboBox<Client> clientCombo;
    @FXML private ComboBox<Product> productCombo;
    @FXML private TextField quantityField;

    @FXML private TextField searchField;
    @FXML private Label statusLabel;

    private FilteredList<Order> filtered;

    @FXML
    private void initialize() {
        clientCombo.setItems(ClientStore.all());
        productCombo.setItems(ProductStore.all());

        colOrderNo.setCellValueFactory(d -> d.getValue().orderNumberProperty());
        colClient.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().getClient() == null ? "" : d.getValue().getClient().getName()));
        colProduct.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().getProduct() == null ? "" : d.getValue().getProduct().getName()));
        colQty.setCellValueFactory(d -> d.getValue().quantityProperty());
        colTotal.setCellValueFactory(d -> d.getValue().totalProperty());

        filtered = new FilteredList<>(OrderStore.all(), s -> true);
        table.setItems(filtered);

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                orderNumberField.setText(sel.getOrderNumber());
                clientCombo.getSelectionModel().select(sel.getClient());
                productCombo.getSelectionModel().select(sel.getProduct());
                quantityField.setText(String.valueOf(sel.getQuantity()));
            }
        });
    }

    @FXML
    private void onSearch() {
        String q = searchField.getText() == null ? "" : searchField.getText().toLowerCase();
        filtered.setPredicate(o ->
            o.getOrderNumber().toLowerCase().contains(q) ||
            (o.getClient()!=null && o.getClient().getName().toLowerCase().contains(q)) ||
            (o.getProduct()!=null && o.getProduct().getName().toLowerCase().contains(q))
        );
    }

    @FXML
    private void onCreate() {
        try {
            String on = orderNumberField.getText();
            Client c = clientCombo.getSelectionModel().getSelectedItem();
            Product p = productCombo.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(quantityField.getText());
            if (!Validation.notBlank(on) || c == null || p == null || !Validation.positiveInt(qty)) {
                statusLabel.setText("Invalid inputs");
                return;
            }
            OrderStore.create(on, c, p, qty);
            statusLabel.setText("Created");
            clearForm();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Quantity must be integer");
        }
    }

    @FXML
    private void onUpdate() {
        Order sel = table.getSelectionModel().getSelectedItem();
        if (sel == null) { statusLabel.setText("Select a row"); return; }
        try {
            String on = orderNumberField.getText();
            Client c = clientCombo.getSelectionModel().getSelectedItem();
            Product p = productCombo.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(quantityField.getText());
            if (!Validation.notBlank(on) || c == null || p == null || !Validation.positiveInt(qty)) {
                statusLabel.setText("Invalid inputs");
                return;
            }
            boolean ok = OrderStore.update(sel.getId(), on, c, p, qty);
            statusLabel.setText(ok ? "Updated" : "Not found");
            table.refresh();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Quantity must be integer");
        }
    }

    @FXML
    private void onDelete() {
        Order sel = table.getSelectionModel().getSelectedItem();
        if (sel == null) { statusLabel.setText("Select a row"); return; }
        boolean ok = OrderStore.delete(sel.getId());
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
        orderNumberField.clear();
        clientCombo.getSelectionModel().clearSelection();
        productCombo.getSelectionModel().clearSelection();
        quantityField.clear();
        table.getSelectionModel().clearSelection();
    }
}
