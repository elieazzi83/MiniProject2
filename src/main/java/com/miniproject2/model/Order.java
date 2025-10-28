package com.miniproject2.model;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class Order extends BaseEntity {
    private final StringProperty orderNumber = new SimpleStringProperty();
    private final ObjectProperty<Client> client = new SimpleObjectProperty<>();
    private final ObjectProperty<Product> product = new SimpleObjectProperty<>();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();

    public Order(String id, String orderNumber, Client client, Product product, int quantity) {
        this.id = id;
        setOrderNumber(orderNumber);
        setClient(client);
        setProduct(product);
        setQuantity(quantity);
        recalcTotal();
        // update total when deps change
        this.product.addListener((obs,o,n) -> recalcTotal());
        this.quantity.addListener((obs,o,n) -> recalcTotal());
    }

    public StringProperty orderNumberProperty() { return orderNumber; }
    public ObjectProperty<Client> clientProperty() { return client; }
    public ObjectProperty<Product> productProperty() { return product; }
    public IntegerProperty quantityProperty() { return quantity; }
    public ReadOnlyDoubleProperty totalProperty() { return total; }

    public String getOrderNumber() { return orderNumber.get(); }
    public void setOrderNumber(String v) { orderNumber.set(v); }

    public Client getClient() { return client.get(); }
    public void setClient(Client v) { client.set(v); }

    public Product getProduct() { return product.get(); }
    public void setProduct(Product v) { product.set(v); }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int v) { quantity.set(v); }

    public double getTotal() { return total.get(); }

    private void recalcTotal() {
        Product p = getProduct();
        int q = getQuantity();
        total.set((p == null ? 0.0 : p.getPrice()) * Math.max(0, q));
    }
}
