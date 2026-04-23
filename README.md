
# 💸 Simple Transaction App (WIP)

⚠️ **WORK IN PROGRESS PROJECT**

This is a learning project built with Spring Boot to simulate a basic transaction system between users.  
---

## 🚧 Current Status

- Basic user creation
- Balance retrieval
- Money transfer between users
- Transaction history tracking
- Failed transaction logging
---

## 🗺️ Roadmap

### 🟡 In Progress / Planned
- [ ] Exception handling at service layer
- [ ] Proper ResponseEntity return for each endpoint
- [ ] Replace `Integer` balance with `BigDecimal` (or float-based money handling)
- [ ] Add dummy currency system (USD / MYR / etc.)
- [ ] User signup & authentication system
- [ ] JWT-based authentication
- [ ] Input validation improvements
- [ ] Transaction concurrency safety (locking / race condition handling)

### 🟢 Frontend
- [ ] Simple React UI
- [ ] User dashboard
- [ ] Transfer form
- [ ] Transaction history page
---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / PostgreSQL (depending on config)
- Maven
---

## 📡 API Testing (cURL)

Below are example `curl` commands to test the current services.

### 👤 1. Create User

```bash
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "userName": "Alice",
  "initialBalance": 1000
}'
```

### 👤 2. Get User Balance
```bash
curl -X GET http://localhost:8080/users/1/balance
```

### 💸 3. Perform Transaction
```bash
curl -X POST http://localhost:8080/transactions \
-H "Content-Type: application/json" \
-d '{
  "senderID": 1,
  "recipientID": 2,
  "transferAmount": 50
}'
```

### 📜 4. Get User Transaction History
```bash
curl -X GET http://localhost:8080/transactions/history/1
```

### ❌ 5. Get Failed Transactions
```bash
curl -X GET http://localhost:8080/transactions/failed
```

### ⚠️ Notes
Minimum transfer amount: 10 \
Maximum transfer amount: 100000 \
Failed transactions are still recorded in DB \
No authentication yet (open API) \

### 🧠 Design Intent

This project is built to practice:
- Service-layer design in Spring Boot
- Transaction handling logic
- Basic domain modeling (User, Transaction)
- DTO mapping
- Error handling patterns
