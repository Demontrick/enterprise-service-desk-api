# Enterprise Service Desk API

## Project Overview

Enterprise Service Desk API is a Spring Boot backend application that simulates an internal IT support ticket management system used in enterprise environments.

The goal of this project is to demonstrate production-grade backend engineering practices including secure REST API design, role-based access control, containerisation, and CI/CD pipeline automation.

This project serves as a proof-of-concept demonstrating skills relevant for Java backend developer roles in enterprise environments.

---

## Business Use Case

Organizations need systems to manage internal IT support requests such as:

- Access issues
- Software installation
- Infrastructure incidents
- Bug reports
- Service requests

This API simulates how companies manage ticket lifecycle from creation to resolution — with security, role separation, and automated deployment built in.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Docker + Docker Compose
- GitHub Actions (CI/CD)
- Swagger (OpenAPI)
- JUnit 5 + Mockito
- Maven

---

## Architecture

The project follows enterprise backend architecture principles:

Controller → Service → Repository → Database

Project structure:
```
src/main/java/com/company/servicedesk
├── controller      → REST endpoints
├── service         → business logic
├── repository      → data access layer
├── model           → entities and enums
├── dto             → request and response models
├── exception       → global error handling
├── security        → JWT filter, JWT util, security config
└── auth            → login endpoint and auth models
```

---

## Security and RBAC

This API implements role-based access control (RBAC) using Spring Security and JWT tokens.

Three roles are supported:

| Role | Access |
|------|--------|
| ADMIN | Full access to all endpoints |
| TECHNICIAN | Full access to all endpoints |
| VIEWER | Read-only access |

All ticket endpoints require a valid JWT token. Requests without a token return 403 Forbidden.

### Login

POST /api/auth/login

Request body:
```json
{
  "username": "admin",
  "password": "admin123"
}
```

Response:
```json
{
  "token": "eyJhbGci...",
  "role": "ADMIN"
}
```

Test credentials:

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ADMIN |
| technician | tech123 | TECHNICIAN |
| viewer | viewer123 | VIEWER |

---

## Features

Ticket Management:
- Create support tickets
- View all tickets
- Get ticket by ID
- Delete tickets

Workflow Management:
- Update ticket status (OPEN → IN_PROGRESS → RESOLVED → CLOSED)

Security:
- JWT authentication
- Role-based access control (RBAC)
- Stateless session management
- Protected endpoints via Spring Security filter chain

Engineering Practices:
- DTO pattern implementation
- Global exception handling
- Input validation
- REST best practices
- Clean layered architecture
- Unit tested with JUnit 5 + Mockito
- Containerised with Docker
- CI/CD pipeline via GitHub Actions

---

## API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /api/auth/login | Get JWT token | No |
| GET | /api/tickets | Returns all tickets | Yes |
| POST | /api/tickets | Creates a new ticket | Yes |
| PATCH | /api/tickets/{id}/status | Updates ticket status | Yes |
| GET | /api/tickets/{id} | Returns ticket by id | Yes |
| DELETE | /api/tickets/{id} | Deletes ticket | Yes |

---

## Example Request

POST /api/tickets

Headers:
Authorization: Bearer your-jwt-token

Request body:
```json
{
  "title": "VPN connection issue",
  "description": "Unable to connect to corporate VPN",
  "priority": "HIGH"
}
```

---

## Ticket Model

| Field | Type | Values |
|-------|------|--------|
| id | Long | Auto generated |
| title | String | Required |
| description | String | Optional |
| status | Enum | OPEN, IN_PROGRESS, RESOLVED, CLOSED |
| priority | Enum | LOW, MEDIUM, HIGH |
| createdAt | Timestamp | Auto generated |

---

## Error Handling

Example error response:
```json
{
  "message": "Ticket not found with id: 99",
  "status": 404,
  "timestamp": "2026-04-02"
}
```

Global exception handling ensures consistent API responses across all endpoints.

---

## How To Run Project

### Option 1 — Local (requires Java 17 and PostgreSQL)

Clone repository:

git clone https://github.com/Demontrick/enterprise-service-desk-api.git

Navigate to project:

cd enterprise-service-desk-api

Create database:

psql -U postgres -c "CREATE DATABASE servicedesk;"

Run application:

mvn spring-boot:run

### Option 2 — Docker (recommended)

docker-compose up --build

App will be available at http://localhost:8080

---

## API Documentation

Swagger UI available at:

http://localhost:8080/swagger-ui.html

Use the Authorize button in Swagger to add your JWT token and test all protected endpoints.

---

## CI/CD Pipeline

This project uses GitHub Actions for continuous integration. On every push to main:

1. PostgreSQL service is spun up
2. Project is built with Maven
3. All tests are executed (JUnit 5 + Mockito)
4. Docker image is built

Pipeline status: ![CI](https://github.com/Demontrick/enterprise-service-desk-api/actions/workflows/ci.yml/badge.svg)

---

## Design Decisions

DTO Pattern — separates persistence layer from API contracts.

Global Exception Handler — provides consistent API error responses.

Status Workflow Endpoint — simulates real enterprise ticket lifecycle management.

Layered Architecture — improves maintainability and scalability.

Stateless JWT Auth — no server-side session storage, scales horizontally.

Docker and Compose — environment-agnostic deployment, OpenShift compatible.

---

## Skills Demonstrated

- REST API Development
- Spring Boot Backend Development
- Spring Security + JWT Authentication
- Role-Based Access Control (RBAC)
- PostgreSQL Integration
- Docker Containerisation
- CI/CD Pipeline (GitHub Actions)
- Unit Testing (JUnit 5 + Mockito)
- Exception Handling
- Backend Architecture Design
- Enterprise Coding Practices

---

## Author

Backend Developer Portfolio Project demonstrating production-grade Java Spring Boot engineering capabilities.