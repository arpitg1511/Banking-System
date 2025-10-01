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

bankingsystem/
|── src
    │── exception
        │── AccountNotFoundException.java        
        │── InsufficientFundsException.java
    │── main
        │── BankingSystem.java  # Main application (console-based UI)
    │── model
        │── Account.java        # Abstract Base class for accounts
        │── SavingsAccount.java # SavingAccount subclass
        │── CurrentAccount.java        # CurrentAccount subclass
        │── Customer.java # Customer Owner Class
    │── service
        │── BankService.java        # Customer and Account details
        │── TransactionService.java # Withdrawal or Deposition subclass
        
        

</pre>


🚀 Getting Started

1️⃣ Clone the Repository

```bash
git clone  https://github.com/arpitg1511/Banking-System.git

cd Banking-System
```

2️⃣ Compile the Project

```bash
javac bankingsystem/*.java
```

3️⃣ Run the Project

```bash
java bankingsystem.BankingSystem
```

🖼️ Demo (Console Output)

```bash
Welcome to the Banking System
1. Create Account
2. Deposit
3. Withdraw
4. Check Balance
5. Exit

Enter your choice: 1
Enter Account Number: 1001
Enter Account Holder Name: Arpit
Account created successfully!
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

