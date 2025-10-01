# 💳 Banking System (Java - Console Based)

A simple **console-based Banking System** built in Java using **Object-Oriented Programming (OOP)** principles.  
This project demonstrates concepts like **Encapsulation, Abstraction, Inheritance, and Polymorphism**, along with basic banking operations.

---

## ✨ Features
- 🏦 Create and manage bank accounts  
- 💰 Deposit & Withdraw money  
- 📊 Check account balance  
- 🗂️ In-memory data storage using Java Collections  
- ❌ Error handling for invalid operations  

---

## 📂 Project Structure

<pre>

src/
├── model/
│ ├── Account.java (abstract)
│ ├── SavingsAccount.java
│ ├── CurrentAccount.java
│ └── Customer.java
├── service/
│ ├── BankService.java
│ └── TransactionService.java
├── exception/
│ ├── InsufficientFundsException.java
│ └── AccountNotFoundException.java
└── main/
└── BankingApplication.java
        
        

</pre>


🚀 Getting Started

1️⃣ Clone the Repository

```bash
git clone  https://github.com/arpitg1511/Banking-System.git

cd Banking-System
```

2️⃣ Compile the Project

```bash
javac -d out src/model/*.java src/exception/*.java src/service/*.java src/main/*.java
```

3️⃣ Run the Project

```bash
java -cp out main.BankingApplication
```

🖼️ Demo (Console Output)

```bash
Welcome to the Banking System
1. Create Account
2. Deposit Money
3. Withdraw Money
4. Transfer Money
5. Check Balance
6. Account Statement
7. View All Accounts
8. Exit
Select an option (1-8): 1
Enter account type (Savings/Current): Savings
Customer ID: 1
Customer Name: Arpit
Customer Email: arpit@yahoo.com
Initial deposit amount: 1200
Account created successfully!
AccountNumber: SAV001 | Type: SavingsAccount | Holder: Arpit | Balance: 1200.00
```


🧑‍💻 OOP Concepts Used
Encapsulation → Private fields with getters/setters

Inheritance → SavingsAccount extends Account

Polymorphism → Overriding methods for different account types

Abstraction → Hiding implementation details of operations



📚 Future Enhancements
Add support for multiple account types (Current, Fixed Deposit)

Implement transaction history

Add interest calculation for savings accounts

Build a GUI-based version

🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.

📜 License

This project is licensed under the [MIT License](LICENSE).


⭐ Don’t forget to star this repo if you like it!



---

