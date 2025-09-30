package exception;

/**
 * Thrown when an account cannot be located.
 */
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
