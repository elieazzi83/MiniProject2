package com.miniproject2.store;

import com.miniproject2.model.Client;
import com.miniproject2.util.IdGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class ClientStore {
    private static final ObservableList<Client> clients = FXCollections.observableArrayList();

    static {
        clients.add(new Client(IdGenerator.newId(), "Acme Corp", "info@acme.com", "03000000"));
        clients.add(new Client(IdGenerator.newId(), "Beta Ltd", "contact@beta.com", "03888888"));
    }

    public static ObservableList<Client> all() { return clients; }

    public static Client create(String name, String email, String phone) {
        Client c = new Client(IdGenerator.newId(), name, email, phone);
        clients.add(c);
        return c;
    }

    public static boolean update(String id, String name, String email, String phone) {
        Optional<Client> opt = clients.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (opt.isEmpty()) return false;
        Client c = opt.get();
        c.setName(name); c.setEmail(email); c.setPhone(phone);
        return true;
    }

    public static boolean delete(String id) {
        return clients.removeIf(c -> c.getId().equals(id));
    }
}
