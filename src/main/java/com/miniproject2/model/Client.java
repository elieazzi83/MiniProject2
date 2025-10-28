package com.miniproject2.model;

import javafx.beans.property.*;

public class Client extends BaseEntity {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();

    public Client(String id, String name, String email, String phone) {
        this.id = id;
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty emailProperty() { return email; }
    public StringProperty phoneProperty() { return phone; }

    public String getName() { return name.get(); }
    public void setName(String n) { name.set(n); }

    public String getEmail() { return email.get(); }
    public void setEmail(String e) { email.set(e); }

    public String getPhone() { return phone.get(); }
    public void setPhone(String p) { phone.set(p); }

    @Override
    public String toString() { return getName(); }
}
