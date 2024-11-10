# Library Management System API

## Overview

This project is a **Library Management System** that provides **RESTful API endpoints** for managing books, patrons, and borrowing/returning operations. The application also implements **user authentication** using **JWT (JSON Web Token)**, which is required to access protected resources.

### Technologies
- **Java 22** (JDK)
- **Spring Boot** (Backend framework)
- **Maven** (Dependency management and build tool)
- **Postman** (API testing tool)
- **JWT Authentication** (For user login and token-based authentication)

---

## Prerequisites

Before running the application, ensure you have the following installed:
- **Java 22** (or a compatible version)
- **Maven** (For building and managing dependencies)
- **Postman** (For API testing)

---

## Setup Instructions

### 1. Clone the Repository
##User Login
Endpoint: POST /api/user/login
Description: Authenticates the user and returns a JWT token.
Request Body:
json
Copy code
{
  "username": "admin",
  "password": "password"
}

```bash
git clone https://github.com/your-repository-url.git
cd your-repository-directory


