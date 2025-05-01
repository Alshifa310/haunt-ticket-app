
# 🎃 Haunt Ticket App

A Spring Boot-based Halloween Haunt Ticket Management System developed as part of a semester project. This application enables users to book, manage, and cancel tickets for Halloween haunt events, and allows administrators to manage ticket availability and event data.

## 📌 Features

- 🎟️ Book and cancel haunt event tickets
- 🔍 View available tickets and event details
- 👤 Basic user and admin interaction
- 🧾 Admin panel for managing events and ticket limits
- 📅 Date and time-based filtering 

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring MVC**
- **Maven**
- **Eclipse IDE**
- **Thymeleaf** 
- **HTML/CSS/JavaScript** 
- **H2** 

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/Alshifa310/haunt-ticket-app.git
   cd haunt-ticket-app
   ```

2. **Import the project into Eclipse**
   - File → Import → Existing Maven Projects → Select this directory

3. **Configure database (optional)**
   - If using  H2, update `src/main/resources/application.properties` with your DB credentials

4. **Run the application**
   - Right-click the main class (with `@SpringBootApplication`) → Run As → Java Application

5. **Access the app**
   - Open `http://localhost:8080` in your browser (or the configured port)

## 📁 Project Structure

```
haunt-ticket-app/
├── src/
│   ├── main/
│   │   ├── java/             # Source code
│   │   └── resources/        # Properties, templates, static files
├── .gitignore
├── README.md
├── pom.xml
```

## 📝 Notes

This project was developed for academic purposes. It demonstrates the use of Spring Boot for creating a complete CRUD-based web application.

## 🙋‍♀️ Author

**Alshifa Belim**  
- [GitHub](https://github.com/Alshifa310)

---
⭐ Feel free to star this repo if you found it helpful or want to reference it!
