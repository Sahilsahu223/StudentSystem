# 🎓 Student Management System

A full-stack web application for managing student data, built using **Spring Boot (REST API)**, **React (Frontend)**, and **MySQL (Database)**. It supports complete CRUD operations like adding, updating, listing, and deleting student records.

---

## 🚀 Features

- Add new students with details
- Update existing student information
- Delete student records
- View all student data in a user-friendly interface
- Clean API architecture with proper validations and error handling

---

## 🛠 Tech Stack

### Backend (Spring Boot)
- Java 17+
- Spring Boot
- Spring Web & Spring Data JPA
- MySQL
- Maven

### Frontend (React)
- React JS
- Axios (for HTTP requests)
- Bootstrap or TailwindCSS (for styling)

---
## 🧪 Testing

This project includes **comprehensive testing** using:

- ✅ **JUnit 5** for unit & integration testing  
- ✅ **MockMvc** for simulating HTTP requests  
- ✅ **Mockito** for mocking repository behavior  
- ✅ **JaCoCo** for measuring code coverage  

### 🧩 Unit Tests
Focused on:
- Controller behavior (`StudentController`)
- Edge cases and error paths (e.g., 404 not found, validation)

### 🔗 Integration Tests
Simulate actual HTTP calls to endpoints with:
- `@SpringBootTest`
- `@AutoConfigureMockMvc`

### 📈 Code Coverage
Generated via **JaCoCo**, viewable at:
target/site/jacoco/index.html


