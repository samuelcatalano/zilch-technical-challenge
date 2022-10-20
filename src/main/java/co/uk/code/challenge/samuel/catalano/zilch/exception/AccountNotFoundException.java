package co.uk.code.challenge.samuel.catalano.zilch.exception;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */
public class AccountNotFoundException extends Exception {

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AccountNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
