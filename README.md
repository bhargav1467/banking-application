# 🏦 Banking Application (Spring Boot)

A simple Spring Boot-based REST API for performing basic bank account operations like creating an account, depositing, withdrawing, fetching details, and deleting an account.

---

## 🗂️ Project Structure

### 1. **Entity** (`Account`)
- Represents the bank account table `tb040_bank_account` in the database.
- Fields:
  - `id` – Auto-generated primary key
  - `accountHolderName`
  - `balance`
  - `phoneNo`

📁 Path: `Entity/Account.java`

---

### 2. **DTOs**

#### a. `AccountDto`
- Used to send/receive account data from the API (includes all fields).

#### b. `TransactionRequestDTO`
- Used for deposit/withdraw operations.
- Contains only one field:
  - `amount`

📁 Path: `Dto/AccountDto.java`, `Dto/TransactionRequestDTO.java`

---

### 3. **Repository**
- Interface extending `JpaRepository<Account, Long>`.
- Provides basic CRUD functionality without needing implementation.

📁 Path: `Repo/AccountRepo.java`

---

### 4. **Service Layer**

#### Interface: `AccountService`
Defines:
- `createNew(AccountDto)`
- `getAccountById(Long)`
- `deposit(Long, Double)`
- `withdraw(Long, Double)`
- `getAllAccount()`
- `deleteAccount(Long)`

#### Implementation: `AccountServiceImpl`
- Contains business logic for deposit/withdraw with balance update.
- Uses `AccountMapper` to convert between Entity and DTO.

📁 Path: `Service/AccountService.java`, `Service/AccountServiceImpl.java`

---

### 5. **Controller Layer**

📁 Path: `Controller/AccountController.java`

- **POST `/create`**  
  → Creates a new account  
  🔁 Uses: `AccountDto`

- **GET `/{id}`**  
  → Get account details by ID

- **PUT `/deposit/{id}`**  
  → Deposit amount to account  
  🔁 Accepts: `{ "amount": 1000 }`  
  🔁 Uses: `TransactionRequestDTO`

- **PUT `/withdraw/{id}`**  
  → Withdraw amount from account  
  🔁 Accepts: `{ "amount": 500 }`  
  🔁 Uses: `TransactionRequestDTO`

- **GET `/get/all`**  
  → Get all accounts

- **DELETE `/delete/{id}`**  
  → Delete account by ID

---

## 🧠 Flow of Operation

1. User sends HTTP request to `AccountController` via API.
2. Controller uses `AccountService` to process the request.
3. Service interacts with `AccountRepo` to query/update the database.
4. Data is mapped between `Account` Entity and `AccountDto` using `AccountMapper`.
5. Final response is returned via `ResponseEntity`.

---

## 🔄 Sample Request Payloads

### Deposit:
```json
PUT /api/banking/deposit/1
{
  "amount": 1000
}
