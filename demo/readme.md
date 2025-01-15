# 📚 Quiz App – Spring Framework (Java)

**Quiz App** is a dynamic and interactive web application built using the **Spring Framework** (Spring Boot) to manage and conduct quizzes efficiently. The app allows users to take quizzes, view their scores, and manage questions and answers through a user-friendly interface.

The project is built with **Spring Boot** for backend development, offering a scalable, secure, and maintainable solution for quiz-based applications. It integrates modern Java development practices with robust database management to provide a seamless experience for both students and instructors.

## 🛠️ Features

- User authentication and authorization (INSTRUCTOR/STUDENT roles)
- Create, update, and delete quizzes and questions
- Randomized quiz generation
- Real-time scoring and feedback
- Secure REST API endpoints
- Database integration using **JPA/Hibernate**
- Customizable quiz categories and difficulty levels

## 🚀 Tech Stack

- **Java** (JDK 17+)
- **Spring Boot** (Spring Framework)
- **Spring Security** – for authentication and role-based access control
- **Hibernate/JPA** – for database interaction
- **MySQL** – for persistence
- **Thymeleaf** (optional) – for front-end views
- **Maven** – for project management
- **Hibernate Validator** – for validation

## 🗂️ Project Structure

```
src
├── main
│   ├── java/com/example/demo
│   │   ├── controller
│   │   ├── config
│   │   ├── model
│   │   ├── repository
│   │   ├── security
│   │   ├── service
│   │   └── dto
│   └── resources
│       ├── public/
│       └── application.properties
└── test
```

## 🧑‍💻 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/quiz-app-spring.git
   cd quiz-app-spring
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## 📄 License

This project is licensed under the **MIT License**.
