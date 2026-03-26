# Enterprise Service Desk API

## Project Overview

Enterprise Service Desk API is a Spring Boot backend application that simulates an internal IT support ticket management system used in enterprise environments.

The goal of this project is to demonstrate backend engineering practices such as REST API design, layered architecture, DTO usage, validation, and exception handling.

This project serves as a proof-of-concept demonstrating skills relevant for Java backend developer roles.

---

## Business Use Case

Organizations need systems to manage internal IT support requests such as:

• Access issues
• Software installation
• Infrastructure incidents
• Bug reports
• Service requests

This API simulates how companies manage ticket lifecycle from creation to resolution.

---

## Tech Stack

Java 17
Spring Boot
Spring Data JPA
Hibernate
H2 Database
Swagger (OpenAPI)
Maven

---

## Architecture

The project follows enterprise backend architecture principles:

Controller → Service → Repository → Database

Project structure:

src/main/java/com/company/servicedesk

controller → REST endpoints
service → business logic
repository → data access layer
model → entities and enums
dto → request and response models
exception → global error handling

---

## Features

Ticket Management:

• Create support tickets
• View all tickets
• Get ticket by ID
• Delete tickets

Workflow Management:

• Update ticket status
(OPEN → IN_PROGRESS → RESOLVED → CLOSED)

Engineering Practices:

• DTO pattern implementation
• Global exception handling
• Input validation
• REST best practices
• Clean layered architecture

---

## API Endpoints

GET /api/tickets
Returns all tickets

POST /api/tickets
Creates a new ticket

PATCH /api/tickets/{id}/status
Updates ticket workflow status

GET /api/tickets/{id}
Returns ticket by id

DELETE /api/tickets/{id}
Deletes ticket

---

## Example Request

POST /api/tickets

Request body:

{
"title":"VPN connection issue",
"description":"Unable to connect to corporate VPN",
"priority":"HIGH"
}

---

## Ticket Model

Ticket fields:

id → Long
title → String
description → String
status → OPEN | IN_PROGRESS | RESOLVED | CLOSED
priority → LOW | MEDIUM | HIGH
createdAt → Timestamp

---

## Error Handling

Example error response:

{
"message":"Ticket not found with id: 99",
"status":404,
"timestamp":"2026-03-26"
}

Global exception handling ensures consistent API responses.

---

## How To Run Project

Clone repository:

git clone <your-repo-url>

Navigate to project:

cd enterprise-service-desk-api

Run application:

mvn spring-boot:run

---

## API Documentation

Swagger UI available at:

http://localhost:8080/swagger-ui.html

Use Swagger to test all endpoints.

---

## Design Decisions

DTO Pattern:
Used to separate persistence layer from API contracts.

Global Exception Handler:
Provides consistent API error responses.

Status Workflow Endpoint:
Simulates real enterprise ticket lifecycle management.

Layered Architecture:
Improves maintainability and scalability.

---

## Possible Improvements

JWT Authentication
Role based authorization
PostgreSQL integration
Docker deployment
Unit testing

---

## Skills Demonstrated

REST API Development
Spring Boot Backend Development
Database Integration
Exception Handling
Backend Architecture Design
Enterprise Coding Practices

---

## Author

Backend Developer Portfolio Project demonstrating Java Spring Boot engineering capabilities.
