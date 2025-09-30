package main;

import model.Account;
import model.Customer;
import service.BankService;
import service.TransactionService;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class providing console menu.
 *
 * Usage:
 * Run main() and use the console menu to interact with the in-memory banking system.
 */
public class BankingApplication {

    private static final BankService bankService = new BankService();
    private static final TransactionService transactionService = new TransactionService(bankService);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Console Banking System (Resume Project)");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        createAccountFlow();
                        break;
                    case "2":
                        depositFlow();
                        break;
                    case "3":
                        withdrawFlow();
                        break;
                    case "4":
                        transferFlow();
                        break;
                    case "5":
                        checkBalanceFlow();
                        break;
                    case "6":
                        statementFlow();
                        break;
                    case "7":
                        viewAllAccountsFlow();
                        break;
                    case "8":
                        running = false;
                        System.out.println("Exiting... Thank you for using the system.");
                        break;
                    default:
                        System.out.println("Invalid choice. Enter number between 1-8.");
                }
            } catch (AccountNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InsufficientFundsException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (Exception e) {
                // fallback for unexpected exceptions
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println(); // blank line for readability
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Check Balance");
        System.out.println("6. Account Statement");
        System.out.println("7. View All Accounts");
        System.out.println("8. Exit");
        System.out.print("Select an option (1-8): ");
    }

    private static void createAccountFlow() {
        System.out.print("Enter account type (Savings/Current): ");
        String type = scanner.nextLine().trim();

        System.out.print("Customer ID: ");
        String custId = scanner.nextLine().trim();
        System.out.print("Customer Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Customer Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Initial deposit amount: ");
        double initial = Double.parseDouble(scanner.nextLine().trim());

        Customer customer = new Customer(custId, name, email);
        Account acc = bankService.createAccount(type, customer, initial);

        System.out.println("Account created successfully!");
        System.out.println(acc.getAccountInfo());
    }

    private static void depositFlow() throws AccountNotFoundException {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        System.out.print("Amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        Account acc = bankService.getAccount(accNum);
        acc.deposit(amount);
        bankService.recordStatement(accNum, String.format("Deposit ₹%.2f (Balance: ₹%.2f)", amount, acc.getBalance()));
        System.out.println("Deposit successful. New balance: ₹" + acc.getBalance());
    }

    private static void withdrawFlow() throws Exception {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        System.out.print("Amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        Account acc = bankService.getAccount(accNum);
        try {
            acc.withdraw(amount);
            bankService.recordStatement(accNum, String.format("Withdrawal ₹%.2f (Balance: ₹%.2f)", amount, acc.getBalance()));
            System.out.println("Withdrawal successful. New balance: ₹" + acc.getBalance());
        } catch (InsufficientFundsException e) {
            throw e;
        }
    }

    private static void transferFlow() throws Exception {
        System.out.print("From account number: ");
        String from = scanner.nextLine().trim();
        System.out.print("To account number: ");
        String to = scanner.nextLine().trim();
        System.out.print("Amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        transactionService.transferMoney(from, to, amount);
        System.out.println("Transfer completed.");
    }

    private static void checkBalanceFlow() throws AccountNotFoundException {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        double balance = transactionService.getAccountBalance(accNum);
        System.out.printf("Account %s balance: ₹%.2f%n", accNum, balance);
    }

    private static void statementFlow() throws AccountNotFoundException {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        List<String> statements = transactionService.generateStatement(accNum);
        System.out.println("Statement for account: " + accNum);
        if (statements.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            statements.forEach(System.out::println);
        }
    }

    private static void viewAllAccountsFlow() {
        System.out.println("All accounts:");
        for (Account acc : bankService.getAllAccounts()) {
            System.out.println(acc.getAccountInfo());
        }
    }
}
