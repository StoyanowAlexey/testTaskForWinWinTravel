# **Auth API & Processing API**

---

## **Technologies**
- ***Java 21***
- ***Spring Boot 3.2.3***
- ***Spring Security with JWT***
- ***PostgreSQL***
- ***MapStruct & ModelMapper***
- ***OpenAPI / Swagger***
- ***Docker*** 
- ***Lombok***
- ***Feign***
- ***Liquibase***

This project demonstrates a simple **authentication API** with JWT tokens and a **text processing API**, built with **Spring Boot**, **PostgreSQL**, and **Docker**.

---

## ***Project Structure***
- ***auth-api***: Handles user registration, authentication, and orchestrates text processing. Runs on port 8080.

- ***data-api***: Internal service that performs text transformation logic. Runs on port 8081.

- ***PostgreSQL***: Shared database for storing user credentials and processing logs.

---

# ***Getting Started***
### *1) Prerequisites*
- ***Maven installed***
- ***Docker installed***



### **2) *Build the services***

```bash
# Build auth-api
mvn -f auth-api/pom.xml clean package -DskipTests

# Build data-api
mvn -f data-api/pom.xml clean package -DskipTests

# Start services via Docker Compose
docker compose up -d --build
```
### 3) *API Usage & Testing*

---
### ***---------Postman---------***
Personally I used postman, but if you use curl it should work too

If you use **Postman**, just do this

1. **Open Postman**
2. **Register a new user**  
   POST `http://localhost:8080/api/auth/register`  
   Body (raw JSON):
   ```json
   {
     "email": "a@a.com",
     "password": "pass"
   }
3. **Login to get JWT token**

   POST `http://localhost:8080/api/auth/login`
   
   Body (raw JSON):
   ``` json
   {
     "email": "a@a.com",
     "password": "pass" 
   }
   ```
    - **copy the token from the response.**


4. **Process text**
   POST http://localhost:8080/api/process
    - **Go to the Authorization tab → select Bearer Token → paste your JWT token.**

   Body (raw JSON):
    ``` json
    {
      "text": "hello"
    }
    ```
   Expected response:
    ``` json
    {
      "result": "transformed text"
    }
    ```
   
---
### ***------------------Curl------------------***
If you use **Curl** use this:
``` bash
# Register
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"pass"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"a@a.com","password":"pass"}'
# Save token from response

# Process
curl -X POST http://localhost:8080/api/process \
-H "Authorization: Bearer <token>" \
-H "Content-Type: application/json" \
-d '{"text":"hello"}'
```

---
### 4) *Swagger*
- Open http://localhost:8080/api/swagger-ui/index.html in your browser.
- Click the Authorize (🔒 smth like that) button at the top right.
- Paste your JWT token to test secured endpoints.
- Example requests and response schemas are pre-filled.

---
### 5) *Notes*

- Base API path: ***/api*** (configured in application.properties).

- ***/auth/***** endpoints are public (registration & login).

- ***/process/***** endpoints are secured; ***JWT token*** required.

- Passwords are stored as ***BCrypt hashes***.

- ***JWT*** is used for stateless authentication.

- **Migrations** path (liquibase) : **auth-api/src/main/resources/db/changelog/db.changelog-master.yaml**
