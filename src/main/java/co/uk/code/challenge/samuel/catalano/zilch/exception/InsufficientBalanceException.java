package co.uk.code.challenge.samuel.catalano.zilch.exception;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */
public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException() {
        super();
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientBalanceException(Throwable cause) {
        super(cause);
    }

    protected InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
