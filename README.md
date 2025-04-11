# Trello API Testing (CRUD)

## 🚀 Problem Statement

Perform **end-to-end API testing** on Trello's Board endpoints using **Postman** and **Rest Assured (Java)**.

🔗 [Trello API](https://trello.com/)

---

## 🎯 Objective

The goal is to perform comprehensive testing of Trello’s Board functionalities using both **manual** (Postman) and **automated** (Rest Assured) approaches. This includes validating responses, error handling, and ensuring the reliability of core Board operations.

---

## 📋 Requirements

### ✅ Functional Coverage:
- **Create** a new Board  
- **Read** the Board details  
- **Update** the Board name  
- **Delete** the Board  

### 🛡️ Validation:
- Status Code
- Response Body
- Schema Validation
- Error Handling (e.g., invalid token, unauthorized access)

---

## 🧰 Tools & Technologies

| Tool           | Purpose                                |
|----------------|----------------------------------------|
| **Postman**    | Manual API Testing                     |
| **Rest Assured (Java)** | Automated API Testing Framework    |
| **Java SDK**   | Development Runtime                    |
| **Maven/Gradle** | Build Tool & Dependency Management    |

---

## 🔐 Authentication

Trello uses OAuth 1.0 or personal API Key and Token.  
You’ll need:
- **API Key**
- **API Token**

Pass them as query parameters or headers as required.

---

## 📁 Project Structure (Automated Testing)

```bash
trello-api-testing/
├── src/
│   └── test/
│       └── java/
│           └── trello/
│               └── Config/
│               ├── CreateBoardTest.java
│               ├── ReadBoardTest.java
│               ├── UpdateBoardTest.java
│               └── DeleteBoardTest.java
├── pom.xml 
└── README.md
