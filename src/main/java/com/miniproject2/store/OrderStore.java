package com.miniproject2.store;

import com.miniproject2.model.*;
import com.miniproject2.util.IdGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class OrderStore {
    private static final ObservableList<Order> orders = FXCollections.observableArrayList();

    static {
        // Seed one example order
        if (!ClientStore.all().isEmpty() && !ProductStore.all().isEmpty()) {
            orders.add(new Order(IdGenerator.newId(), "ORD-1", ClientStore.all().get(0), ProductStore.all().get(0), 2));
        }
    }

    public static ObservableList<Order> all() { return orders; }

    public static Order create(String orderNumber, Client client, Product product, int quantity) {
        Order o = new Order(IdGenerator.newId(), orderNumber, client, product, quantity);
        orders.add(o);
        return o;
    }

    public static boolean update(String id, String orderNumber, Client client, Product product, int quantity) {
        Optional<Order> opt = orders.stream().filter(o -> o.getId().equals(id)).findFirst();
        if (opt.isEmpty()) return false;
        Order o = opt.get();
        o.setOrderNumber(orderNumber);
        o.setClient(client);
        o.setProduct(product);
        o.setQuantity(quantity);
        return true;
    }

    public static boolean delete(String id) {
        return orders.removeIf(o -> o.getId().equals(id));
    }
}
