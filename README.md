# User Microservice

## Overview
The User Service is a core microservice in an e-commerce backend system, responsible for managing user-related operations. It acts as a central hub for user authentication, registration, and profile management, while integrating securely with other services like order management, product services, and payment systems. Built using Spring Boot, this microservice ensures scalability, security, and efficiency, enabling seamless user experiences across the platform.

## Features
- User Management: CRUD (Create, Read, Update, Delete) operations for managing user profiles.
- Authentication and Authorization: Implements JWT-based authentication and role-based access control (RBAC).
- User Registration: Provides APIs for user sign-up with validation and secure password storage.
- Profile Management: Allows users to update their account information, such as name, email, and preferences.
- Secure Communication: Uses encryption standards (e.g., HTTPS, BCrypt) to safeguard user data and credentials.
- Integration with Other Services: Seamlessly integrates with services like Product, Order, and Payment for a unified backend.
- Scalable Architecture: RESTful API design ensures maintainability and supports a modular, distributed system.

## Key Technologies Used
- Spring Boot 3.4.0: Framework for building production-ready applications.
- Lombok: Reduces boilerplate code by generating getter, setter, and other utility methods.
- Maven: Dependency management and build tool.
- Java 17: The latest LTS version, providing modern language features and performance improvements.
- Spring Security: Provides JWT-based authentication and role-based access control.
- REST APIs: Implements RESTful endpoints for seamless integration with other services.
- H2/Relational Database: Stores user data in a lightweight, embeddable database (H2 for local development).

## Security
- JWT Authentication: Access to most endpoints requires a valid JWT token in the Authorization header.
- Password Encryption: User passwords are securely stored using BCrypt hashing.

## Getting Started
### Prerequisites
1. Java 17
2. Maven
3. Docker (optional, for containerized deployments)

## Steps to Run Locally
Clone the repository:
```
git clone https://github.com/yourusername/user-service.git  
cd user-service  

```
Build the project using Maven:
```
mvn clean install  

```
Run the application:
```
mvn spring-boot:run  

```
Access the service
