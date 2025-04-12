# 📌 Trello API Testing (CRUD)

## 🚀 Problem Statement

Perform **end-to-end API testing** on Trello's Board endpoints using **Postman** and **Rest Assured (Java)**.

🔗 [Trello API](https://trello.com/)

---

## 🎯 Objective

The goal is to comprehensively test Trello’s Board functionalities using both **manual** (Postman) and **automated** (Rest Assured) approaches. This includes validating responses, error handling, and ensuring the reliability of core Board operations.

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

| Tool                 | Purpose                            |
|----------------------|------------------------------------|
| **Postman**          | Manual API Testing                 |
| **Rest Assured**     | Automated API Testing (Java)       |
| **Java SDK**         | Development Runtime                |
| **Maven**            | Build Tool & Dependency Management |

---
###[Postman](https://bytebenders-1213.postman.co/workspace/ByteBenders-Workspace~f8846452-9ff3-48a6-9f3f-3cbfb3e987f5/collection/16991420-557138d8-9ec2-4b4f-bf92-735a2107a115?action=share&creator=16991420&active-environment=16991420-66129619-1394-48cd-a655-e6b14115856f) :-

## 🔐 Authentication
Trello uses an API Key and a Token for authentication.

You’ll need:
- `API_KEY`
- `TOKEN`

Pass them as query parameters:
-?key={{API_KEY}}&token={{TOKEN}}

---

## 🌐 Base URL
-https://api.trello.com/1

---

## 🌍 Environment Variables (Postman)

| Variable     | Description               |
|--------------|---------------------------|
| `API_KEY`    | Your Trello API Key       |
| `TOKEN`      | Your Trello API Token     |
| `BOARD_ID`   | Stores created board ID   |
| `BASE_URL`   | https://api.trello.com/1  |

---

## 🔄 Postman API Workflow

## 📌 POST: Create a New Board
 
{{BASE_URL}}boards/?name=ByteBenders&key={{API_KEY}}&token={{TOKEN}}

**Script (Store ID):**

```
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});


pm.test("Store Board ID", function () {
    let id = pm.response.json().id;
    pm.environment.set("BOARD_ID", id);
    console.log("Board ID stored:", id);
});
```
---
## 📋 GET: Read All Boards

{{BASE_URL}}members/me/boards?key={{API_KEY}}&token={{TOKEN}}

## 📥 GET: Read One Board by Name

{{BASE_URL}}members/me/boards?name=ByteBenders&id={{BOARD_ID}}&key={{API_KEY}}&token={{TOKEN}}

```
const schema = {
  type: "array",
  items: {
    type: "object",
    required: ["id", "name"],
    properties: {
      id: { type: "string" },
      name: { type: "string" }
    }
  }
};

pm.test("Response schema is valid", function () {
  pm.expect(pm.response.json()).to.have.jsonSchema(schema);
});
```
---
## 🛠 PUT: Update Board Name

{{BASE_URL}}boards/67fa845101e972636b8cadbf?key={{API_KEY}}&token={{TOKEN}}&name=NewBoardName

---
## ❌ DELETE: Delete Board

{{BASE_URL}}boards/{{boardId}}?key={{API_KEY}}&token={{TOKEN}}

## ⚠️ Error Handling Examples
   Invalid Token: Returns 401 Unauthorized

   Missing Parameters: Returns 400 Bad Request
   
---

## Rest Assured (Java)

##Run test API

**Run the CreateBoard test:**

```mvn test -Dtest=CreateBoard```

**Run the ReadBoard test:**

```mvn test -Dtest=ReadBoard```

**Run the ReadAllBoards test:**

```mvn test -Dtest=ReadAllBoards```

**Run the UpdateBoard test:**

```mvn test -Dtest=UpdateBoard```

**Run the DeleteBoard test:**

```mvn test -Dtest=DeleteBoard```

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
