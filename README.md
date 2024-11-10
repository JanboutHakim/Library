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

### Explanation:
1. **User Signup**: A registration endpoint for creating new users, which accepts a **POST** request with the user's details.
2. **User Login**: This part describes the **login form**. The user accesses the **login page** at `/login`, enters their **username** and **password**, and then submits the form. Spring Security will manage the authentication session.
   - If authentication is successful, the user will be redirected to the requested page (or the default page, usually `/` or `/home`).
   - If the credentials are invalid, they will be redirected back to the login page.
3. **Postman Example**: Provides a guide to logging in through **Postman** by using form data in a `POST` request.
4. **Session Management**: Describes that **Spring Security** uses **HTTP sessions** to manage login states and provides a **logout** endpoint for users to manually log out.

This section can be directly added to your **README.md** file to guide users through logging in using **basic Spring Security authentication**.



## Setup Instructions

### 1. Clone the Repository


```bash
git clone https://github.com/your-repository-url.git
cd your-repository-directory


