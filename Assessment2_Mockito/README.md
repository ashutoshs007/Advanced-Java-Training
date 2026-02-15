# Mockito Order Project

## 📌 Project Overview
This project demonstrates **unit testing using Mockito and JUnit 5**.

The goal of this assignment is to understand how mocking works and how dependencies can be tested in isolation.

---

## 🛠 Technologies Used
- Java
- JUnit 5
- Mockito
- Maven
- IntelliJ IDEA

---

## 📚 Case Study
A simple **Order Processing System** with the following components:

- **PaymentService** → Processes payments
- **InventoryService** → Checks product stock
- **OrderService** → Places orders

---

## ⚙ Business Logic
The `placeOrder()` method performs:

1. Stock availability check  
2. Payment processing  
3. Returns appropriate response:

- ✅ `Order Successful`
- ❌ `Out of Stock`
- ❌ `Payment Failed`

---

## 🧪 Test Scenarios Covered

✔ Stock available + Payment success  
✔ Stock not available  
✔ Payment failure  
✔ Payment exception handling  
✔ Method invocation verification  
✔ Negative amount validation  

---

## 🎯 Key Concepts Demonstrated

- Dependency Mocking
- Behavior Stubbing
- Exception Testing
- Method Verification
- Isolated Unit Testing

---

## 🧾 What is Mocking?

**Mocking** is a technique used in unit testing where real dependencies are replaced with simulated objects.

### ✅ Why Mocking is Used

- Avoid database / API calls
- Test components independently
- Control dependency behavior
- Simulate edge cases easily

---

## ✅ Test Execution
All unit tests pass successfully ✔

---

## 👨‍💻 Author
Ashutosh  
B.Tech CSE

