# Library Management System API

## Overview

This project is a **Library Management System** that provides **RESTful API endpoints** for managing books, patrons, and borrowing/returning operations.

### Technologies
- **Java 22** (JDK)
- **Spring Boot** (Backend framework)
- **Maven** (Dependency management and build tool)
- **Postman** (API testing tool)

---

## Prerequisites

Before running the application, ensure you have the following installed:
- **Java 22** (or a compatible version)
- **Maven** (For building and managing dependencies)
- **Postman** (For API testing)

---

## Authentication

### 1. **User Login**

- **Endpoint**: `POST /login`
- **Description**: Allows users to log in.
- **Request Body**:
  ```json
  {
    "username": "your-username",
    "password": "your-password"
  }
- **Default Credentials**
  ```json
  {
    "username": "admin",
    "password": "password"
  }



## Setup Instructions

### 1. Clone the Repository


```bash
git clone https://github.com/your-repository-url.git
cd your-repository-directory


