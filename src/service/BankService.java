package service;

import model.Account;
import model.CurrentAccount;
import model.Customer;
import model.SavingsAccount;
import exception.AccountNotFoundException;

import java.util.*;

/**
 * BankService - responsible for account management.
 * Uses in-memory collections (HashMap) to store accounts and statements.
 */
public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();
    // Simple transaction statement store: accountNumber -> list of transactions
    private final Map<String, List<String>> statements = new HashMap<>();

    // Counters for generating account numbers
    private int savingsCounter = 0;
    private int currentCounter = 0;

    public BankService() {
        // default constructor
    }

    /**
     * Create account of specified type ("SAVINGS" or "CURRENT").
     *
     * @param type           "SAVINGS" or "CURRENT" (case-insensitive)
     * @param customer       customer object
     * @param initialDeposit initial deposit amount
     * @return created Account
     */
    public Account createAccount(String type, Customer customer, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative.");
        }

        String normalized = type.trim().toUpperCase(Locale.ROOT);
        Account account;
        switch (normalized) {
            case "SAVINGS":
            case "SAV":
            case "SAVINGSACCOUNT":
                String savAccNum = generateAccountNumber("SAV");
                account = new SavingsAccount(savAccNum, customer, initialDeposit);
                break;
            case "CURRENT":
            case "CUR":
            case "CURRENTACCOUNT":
                String curAccNum = generateAccountNumber("CUR");
                account = new CurrentAccount(curAccNum, customer, initialDeposit);
                break;
            default:
                throw new IllegalArgumentException("Invalid account type. Choose Savings or Current.");
        }

        accounts.put(account.getAccountNumber(), account);
        statements.put(account.getAccountNumber(), new ArrayList<>());
        recordStatement(account.getAccountNumber(),
                String.format("Account created with initial deposit ₹%.2f", initialDeposit));
        return account;
    }

    /**
     * Retrieve account by account number.
     *
     * @param accountNumber account number
     * @return Account
     * @throws AccountNotFoundException if not present
     */
    public Account getAccount(String accountNumber) throws AccountNotFoundException {
        Account acc = accounts.get(accountNumber);
        if (acc == null) {
            throw new AccountNotFoundException("Account " + accountNumber + " not found.");
        }
        return acc;
    }

    /**
     * Return unmodifiable collection of all accounts.
     */
    public Collection<Account> getAllAccounts() {
        return Collections.unmodifiableCollection(accounts.values());
    }

    /**
     * Delete an account.
     */
    public void deleteAccount(String accountNumber) throws AccountNotFoundException {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException("Account " + accountNumber + " not found.");
        }
        accounts.remove(accountNumber);
        statements.remove(accountNumber);
    }

    /**
     * Internal utility: record a transaction/statement line for an account.
     */
    public void recordStatement(String accountNumber, String entry) {
        List<String> list = statements.get(accountNumber);
        if (list == null) {
            list = new ArrayList<>();
            statements.put(accountNumber, list);
        }
        String timeStamped = String.format("%s | %s", new Date(), entry);
        list.add(timeStamped);
    }

    /**
     * Retrieve statements for an account.
     */
    public List<String> getStatements(String accountNumber) {
        List<String> list = statements.get(accountNumber);
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    private String generateAccountNumber(String prefix) {
        if ("SAV".equals(prefix)) {
            savingsCounter++;
            return String.format("SAV%03d", savingsCounter);
        } else if ("CUR".equals(prefix)) {
            currentCounter++;
            return String.format("CUR%03d", currentCounter);
        } else {
            throw new IllegalArgumentException("Unknown account prefix.");
        }
    }
}
