# Mini Project 2 — JavaFX MVC CRUD Application

A complete **CRUD (Create, Read, Update, Delete)** desktop application built in **JavaFX** following the **MVC architecture**.  
The app includes a login screen, a home dashboard, and management views for multiple business entities — all stored **in-memory** using `ObservableList` collections (no database required).

---

## 🧰 Tech Stack
- **JDK 25**  
- **JavaFX 21.0.3** (via local modules or Maven plugin)
- **IntelliJ IDEA** for development  
- **FXML + Scene Builder** for UI design  
- **ObservableList** for data storage  
- **Git + GitHub** for version control  

---

## ⚙️ How to Run (IntelliJ)
1. Open the project folder in **IntelliJ IDEA**  
2. Make sure **Project SDK** is set to **Java 25**  
3. Open **Run → Edit Configurations → Application**  
   - **Main class:** `com.miniproject2.App`  
   - **VM options:**  
     ```
     --module-path "C:\javafx-sdk-21.0.3\lib" --add-modules javafx.controls,javafx.fxml
     ```
     *(or use your local JavaFX path)*  
4. Click **Run ▶️**  

> 💡 You can also run with Maven if it’s installed:  
> ```bash
> mvn javafx:run
> ```

---

## 🔐 Login Credentials
Use any of the following user accounts:
| Username | Password | Name       |
|-----------|-----------|------------|
| elie      | pass123   | Elie Azzi  |
| maria     | pass123   | Maria N.   |
| hadi      | pass123   | Hadi S.    |

---

## 🏠 Application Flow
1. **Login View** — verifies credentials from `UserStore`  
2. **Home View** — greets the logged-in user and provides navigation buttons  
3. **Entity Views** — allow CRUD operations using a `TableView` and form controls  
4. **Logout** — returns to the Login View  

---

## 🧱 Entities Implemented
| Entity  | Attributes | Controller | Store | View (FXML) |
|----------|-------------|------------|-------|--------------|
| **Clients**  | name, email, phone | `ClientsController` | `ClientStore` | `clients.fxml` |
| **Products** | name, sku, price, stock | `ProductsController` | `ProductStore` | `products.fxml` |
| **Orders**   | orderNumber, client, product, quantity, total | `OrdersController` | `OrderStore` | `orders.fxml` |

> All entities are stored and managed **in-memory** using `ObservableList` inside `store/*` classes.

---

## 🎨 UI Design
- All layouts are defined in **FXML** and styled with **`styles.css`**  
- Built with **Scene Builder** and manually fine-tuned for consistency  
- Modern **Home page** featuring a centered card with rounded buttons and counters  
- Each entity view includes:
  - A searchable `TableView`
  - Input form with validation
  - Buttons for Create / Update / Delete / Back

---

## 🤝 Collaboration
- Developed collaboratively using **Git + GitHub**  
- Repository name: `MiniProject2`
- Workflow: feature branches → Pull Requests → merged into `main`

---

## 📦 Project Structure

MiniProject2/
├─ pom.xml
├─ src/
│ ├─ main/java/com/miniproject2/
│ │ ├─ App.java
│ │ ├─ ViewNavigator.java
│ │ ├─ controller/
│ │ ├─ model/
│ │ ├─ store/
│ │ └─ util/
│ └─ main/resources/com/miniproject2/
│ ├─ login.fxml
│ ├─ home.fxml
│ ├─ clients.fxml
│ ├─ products.fxml
│ ├─ orders.fxml
│ └─ styles.css

---

## 🧪 Notes
- No external database — **all data is temporary (in-memory)**  
- Ready for extension with a real DB in *Mini Project 3*  
- Works on Windows 10 / 11 with JavaFX 21+  

---

## 📸 Screenshots *(to add)*
1. Login Screen  
2. Home Dashboard  
3. Clients View  
4. Products View  
5. Orders View  

---

**© 2025 Mini Project 2 Team — USEK Computer Science Department**
