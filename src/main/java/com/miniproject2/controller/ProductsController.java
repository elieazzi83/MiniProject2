package com.miniproject2.controller;

import com.miniproject2.ViewNavigator;
import com.miniproject2.model.Product;
import com.miniproject2.store.ProductStore;
import com.miniproject2.util.Validation;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class ProductsController {
    @FXML private TableView<Product> table;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colSku;
    @FXML private TableColumn<Product, Number> colPrice;
    @FXML private TableColumn<Product, Number> colStock;

    @FXML private TextField nameField;
    @FXML private TextField skuField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;

    @FXML private TextField searchField;
    @FXML private Label statusLabel;

    private FilteredList<Product> filtered;

    @FXML
    private void initialize() {
        colName.setCellValueFactory(d -> d.getValue().nameProperty());
        colSku.setCellValueFactory(d -> d.getValue().skuProperty());
        colPrice.setCellValueFactory(d -> d.getValue().priceProperty());
        colStock.setCellValueFactory(d -> d.getValue().stockProperty());

        filtered = new FilteredList<>(ProductStore.all(), s -> true);
        table.setItems(filtered);

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, sel) -> {
            if (sel != null) {
                nameField.setText(sel.getName());
                skuField.setText(sel.getSku());
                priceField.setText(String.valueOf(sel.getPrice()));
                stockField.setText(String.valueOf(sel.getStock()));
            }
        });
    }

    @FXML
    private void onSearch() {
        String q = searchField.getText() == null ? "" : searchField.getText().toLowerCase();
        filtered.setPredicate(p -> p.getName().toLowerCase().contains(q) || p.getSku().toLowerCase().contains(q));
    }

    @FXML
    private void onCreate() {
        try {
            String name = nameField.getText();
            String sku = skuField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            if (!Validation.notBlank(name) || !Validation.notBlank(sku) ||
                !Validation.positiveDouble(price) || !Validation.nonNegativeInt(stock)) {
                statusLabel.setText("Invalid inputs");
                return;
            }
            ProductStore.create(name, sku, price, stock);
            statusLabel.setText("Created");
            clearForm();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Price/Stock must be numeric");
        }
    }

    @FXML
    private void onUpdate() {
        Product sel = table.getSelectionModel().getSelectedItem();
        if (sel == null) { statusLabel.setText("Select a row"); return; }
        try {
            String name = nameField.getText();
            String sku = skuField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            if (!Validation.notBlank(name) || !Validation.notBlank(sku) ||
                !Validation.positiveDouble(price) || !Validation.nonNegativeInt(stock)) {
                statusLabel.setText("Invalid inputs");
                return;
            }
            boolean ok = ProductStore.update(sel.getId(), name, sku, price, stock);
            statusLabel.setText(ok ? "Updated" : "Not found");
            table.refresh();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Price/Stock must be numeric");
        }
    }

    @FXML
    private void onDelete() {
        Product sel = table.getSelectionModel().getSelectedItem();
        if (sel == null) { statusLabel.setText("Select a row"); return; }
        boolean ok = ProductStore.delete(sel.getId());
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
        nameField.clear(); skuField.clear(); priceField.clear(); stockField.clear();
        table.getSelectionModel().clearSelection();
    }
}
