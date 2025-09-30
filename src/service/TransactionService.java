package service;

import model.Account;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;

import java.util.List;

/**
 * TransactionService handles transfers, balance checks and statement generation.
 */
public class TransactionService {
    private final BankService bankService;

    public TransactionService(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Transfer money between accounts with proper checks and statement recording.
     *
     * @param fromAcc from account number
     * @param toAcc   to account number
     * @param amount  amount to transfer
     * @throws Exception AccountNotFoundException or InsufficientFundsException or IllegalArgumentException
     */
    public void transferMoney(String fromAcc, String toAcc, double amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        Account from = bankService.getAccount(fromAcc);
        Account to = bankService.getAccount(toAcc);

        // Try withdrawal from source - child classes enforce rules
        try {
            from.withdraw(amount);
        } catch (InsufficientFundsException e) {
            // propagate meaningful message
            throw e;
        }

        // deposit to destination
        to.deposit(amount);

        // record statements
        bankService.recordStatement(fromAcc,
                String.format("Transfer OUT ₹%.2f to %s (Balance: ₹%.2f)", amount, toAcc, from.getBalance()));
        bankService.recordStatement(toAcc,
                String.format("Transfer IN  ₹%.2f from %s (Balance: ₹%.2f)", amount, fromAcc, to.getBalance()));
    }

    /**
     * Get account balance
     */
    public double getAccountBalance(String accountNumber) throws AccountNotFoundException {
        Account acc = bankService.getAccount(accountNumber);
        return acc.getBalance();
    }

    /**
     * Generate and return account statement lines.
     */
    public List<String> generateStatement(String accountNumber) throws AccountNotFoundException {
        // validate account exists first
        bankService.getAccount(accountNumber);
        return bankService.getStatements(accountNumber);
    }
}
