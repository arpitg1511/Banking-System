package model;

import exception.InsufficientFundsException;

/**
 * SavingsAccount enforces a minimum balance rule.
 */
public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, Customer customer, double initialDeposit) {
        super(accountNumber, customer, initialDeposit);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        double projected = getBalance() - amount;
        if (projected < MINIMUM_BALANCE) {
            throw new InsufficientFundsException(
                    String.format("Withdrawal would breach minimum balance (₹%.2f). Current balance: ₹%.2f",
                            MINIMUM_BALANCE, getBalance()));
        }
        setBalance(projected);
    }

    public static double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
}
