package exception;

/**
 * Custom Exception untuk handling error dalam menu
 * Menerapkan konsep Exception Handling
 */
public class MenuException extends Exception {

    public MenuException(String message) {
        super(message);
    }

    public MenuException(String message, Throwable cause) {
        super(message, cause);
    }
}