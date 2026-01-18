# TicketFlow

**A robust Ticket Management System built with Java and Spring Boot.**

This project manages the full lifecycle of support tickets, providing a secure and organized environment for both technicians and end-users.

## 🛠 Technology Stack

- Backend: Java 17, Spring Boot, Spring Security, JPA/Hibernate.

- Frontend: JSP (Legacy), Spring MVC, Bootstrap.

- Database: MySQL / H2.

- Tools: Maven, JUnit 5.

## ✨ Key Features

- Role-Based Access Control (RBAC): Distinct interfaces and permissions for Customers and Technicians.
  
- Ticket Lifecycle Management: Create, assign, prioritize, and resolve support requests.
  
- Security: Secure authentication and authorization powered by Spring Security.

- Data Integrity: Server-side validation and robust database relationships.

## 🚀 Modernization Roadmap (In Progress)

I am currently refactoring this project to transition from a monolithic architecture to a modern, decoupled system.

### Phase 0: Software Engineering & Design 🏗️
- [ ] Gap Analysis: Detailed documentation of current technical debt vs. target architecture.

- [ ] Requirements Engineering: Definition of User Stories and Functional/Non-functional requirements.

- [ ] System Design: Creating C4 Model diagrams (Context and Container levels) to visualize the new architecture.

- [ ] Database Migration Planning: Schema versioning strategy using Flyway/Liquibase.

### Phase 1: Core Refactoring & Hexagonal Architecture 🧩
- [ ] Domain Isolation: Decoupling business logic from Spring/JPA frameworks.

- [ ] Port & Adapters: Implementing hexagonal layers to improve testability.

- [ ] Unit Testing: Achieving >80% coverage in the domain layer using JUnit 5 and Mockito.

### Phase 2: RESTful API & Security Overhaul 🔐
- [ ] API Migration: Replacing JSP Controllers with @RestController and JSON responses.

- [ ] API Documentation: Integrating Swagger/OpenAPI for interactive documentation.

- [ ] Stateless Security: Transitioning from Session-based auth to JWT (JSON Web Tokens).

### Phase 3: Frontend Modernization 💻
- [ ] SPA Development: Building a new reactive interface using React.

- [ ] Styling: Implementing a professional UI with Tailwind CSS.

- [ ] State Management: Efficient data fetching and caching.

### Phase 4: Infrastructure & DevOps 🐳
- [ ] Containerization: Orchestrating Backend, Frontend, and Database using Docker Compose.

- [ ] CI/CD: Setting up GitHub Actions for automated testing and linting.