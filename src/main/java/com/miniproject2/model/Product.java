package com.miniproject2.model;

import javafx.beans.property.*;

public class Product extends BaseEntity {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty sku = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty stock = new SimpleIntegerProperty();

    public Product(String id, String name, String sku, double price, int stock) {
        this.id = id;
        setName(name);
        setSku(sku);
        setPrice(price);
        setStock(stock);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty skuProperty() { return sku; }
    public DoubleProperty priceProperty() { return price; }
    public IntegerProperty stockProperty() { return stock; }

    public String getName() { return name.get(); }
    public void setName(String v) { name.set(v); }

    public String getSku() { return sku.get(); }
    public void setSku(String v) { sku.set(v); }

    public double getPrice() { return price.get(); }
    public void setPrice(double v) { price.set(v); }

    public int getStock() { return stock.get(); }
    public void setStock(int v) { stock.set(v); }

    @Override
    public String toString() { return getName(); }
}
