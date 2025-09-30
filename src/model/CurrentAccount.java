package model;

import exception.InsufficientFundsException;

/**
 * CurrentAccount supports overdraft up to a limit.
 */
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 1000.0; // allowed negative balance up to -1000

    public CurrentAccount(String accountNumber, Customer customer, double initialDeposit) {
        super(accountNumber, customer, initialDeposit);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        double projected = getBalance() - amount;
        if (projected < -OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException(
                    String.format("Insufficient funds. Overdraft limit is ₹%.2f. Current balance: ₹%.2f",
                            OVERDRAFT_LIMIT, getBalance()));
        }
        setBalance(projected);
    }

    public static double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }
}
