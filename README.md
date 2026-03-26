# Enterprise Service Desk API

## Overview

Enterprise Service Desk API is a backend REST service built with Spring Boot that simulates a real IT support ticket management system used inside companies.

The project demonstrates backend engineering fundamentals such as layered architecture, DTO design, exception handling, validation, and REST workflow management.

This project was built as a proof-of-concept to demonstrate backend development skills relevant to Java enterprise environments.

---

## Business Problem

Companies need internal systems to track IT support issues such as:

• System access problems
• Software installation requests
• Infrastructure incidents
• Bug reports

This API simulates how enterprise service desks manage ticket lifecycle from creation to resolution.

---

## Tech Stack

Java 17
Spring Boot
Spring Data JPA
Hibernate
H2 Database
Swagger / OpenAPI
Maven

---

## Architecture

The project follows standard enterprise backend structure:

Controller → Service → Repository → Database

Structure:

src/main/java/com/company/servicedesk

controller → REST endpoints
service → business logic
repository → database access
model → entities & enums
dto → request/response objects
exception → global error handling

---

## Features

Ticket Management:
• Create support tickets
• View all tickets
• Get ticket by id
• Delete tickets

Workflow Management:
• Update ticket status (OPEN → IN_PROGRESS → RESOLVED → CLOSED)

Engineering Practices:
• DTO pattern
• Global exception handling
• Input validation
• REST best practices
• Layered architecture

---

## API Endpoints

### Get all tickets

GET /api/tickets

Returns all tickets.

---

### Create ticket

POST /api/tickets

Example request:

{
"title":"VPN not working",
"description":"Cannot connect to company VPN",
"priority":"HIGH"
}

---

### Get ticket by ID

GET /api/tickets/{id}

Example:

GET /api/tickets/1

---

### Update ticket status

PATCH /api/tickets/{id}/status?status=IN_PROGRESS

Example:

PATCH /api/tickets/1/status?status=RESOLVED

---

### Delete ticket

DELETE /api/tickets/{id}

Example:

DELETE /api/tickets/1

---

## Data Models

Ticket:

id → Long
title → String
description → String
status → OPEN | IN_PROGRESS | RESOLVED | CLOSED
priority → LOW | MEDIUM | HIGH
createdAt → Timestamp

---

## Validation Rules

Title:
• Required
• Cannot be empty

Priority:
• Must be LOW, MEDIUM, or HIGH

---

## Error Handling

The API uses GlobalExceptionHandler to return consistent error responses.

Example:

{
"message":"Ticket not found with id: 99",
"status":404,
"timestamp":"2026-03-26T14:23"
}

---

## How to Run

Clone project:

git clone <your repo link>

Navigate:

cd enterprise-service-desk-api

Run:

mvn spring-boot:run

---

## API Documentation

Swagger UI:

http://localhost:8080/swagger-ui.html

Use Swagger to test endpoints directly.

---

## Design Decisions

DTO Pattern:
Used to separate API contract from database entities.

Global Exception Handling:
Ensures consistent API error responses.

Status Workflow Endpoint:
Added PATCH endpoint to simulate real service desk lifecycle management.

Layered Architecture:
Improves maintainability and scalability.

---

## Future Improvements (optional)

Authentication (JWT)
Role based access
PostgreSQL integration
Docker containerization
Unit testing

---

## What This Project Demonstrates

Understanding of:

REST API development
Spring Boot architecture
Database persistence
Exception handling
Backend project structure
Enterprise coding practices

---

## Author

Java Backend Developer candidate demonstrating enterprise backend development skills.
