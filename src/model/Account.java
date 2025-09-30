package model;

/**
 * Abstract base class representing a bank account.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class Account {
    private String accountNumber;
    private double balance;
    private Customer customer;

    public Account(String accountNumber, Customer customer, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Withdraw behavior differs between account types -> Polymorphism
     *
     * @param amount Amount to withdraw
     * @throws Exception if withdrawal violates account-specific rules
     */
    public abstract void withdraw(double amount) throws Exception;

    /**
     * Deposit is common behavior for all account types.
     *
     * @param amount Amount to deposit
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public String getAccountInfo() {
        return String.format("AccountNumber: %s | Type: %s | Holder: %s | Balance: %.2f",
                accountNumber, this.getClass().getSimpleName(),
                customer != null ? customer.getName() : "N/A", balance);
    }
}
