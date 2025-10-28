package com.miniproject2.store;

import com.miniproject2.model.Product;
import com.miniproject2.util.IdGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class ProductStore {
    private static final ObservableList<Product> products = FXCollections.observableArrayList();

    static {
        products.add(new Product(IdGenerator.newId(), "Espresso Beans", "SKU-100", 8.50, 120));
        products.add(new Product(IdGenerator.newId(), "Milk 1L", "SKU-200", 1.30, 50));
        products.add(new Product(IdGenerator.newId(), "Syrup Vanilla", "SKU-300", 3.99, 20));
    }

    public static ObservableList<Product> all() { return products; }

    public static Product create(String name, String sku, double price, int stock) {
        Product p = new Product(IdGenerator.newId(), name, sku, price, stock);
        products.add(p); return p;
    }

    public static boolean update(String id, String name, String sku, double price, int stock) {
        Optional<Product> opt = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (opt.isEmpty()) return false;
        Product p = opt.get();
        p.setName(name); p.setSku(sku); p.setPrice(price); p.setStock(stock);
        return true;
    }

    public static boolean delete(String id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
