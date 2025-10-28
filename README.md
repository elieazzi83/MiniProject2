# Mini Project 2 — JavaFX MVC CRUD (No DB)

## Requirements
- JDK 21 installed
- Maven installed
- (Optional) Scene Builder (we already provide FXML; you can open/edit them visually)

## Run
```bash
mvn -v              # should show Java 21
mvn javafx:run
```

## Login
Use any of:
- elie / pass123
- maria / pass123
- hadi  / pass123

## Entities
- Clients (name, email, phone)
- Products (name, sku, price, stock)
- Orders (orderNumber, client, product, quantity, total)

Everything is stored in-memory using `ObservableList` in `store/*` classes.
