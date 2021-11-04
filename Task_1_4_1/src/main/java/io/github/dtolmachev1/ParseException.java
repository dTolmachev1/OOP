package io.github.dtolmachev1;

/**
 * <p>Class for parsing exceptions.</p>
 */
public class ParseException extends Exception {
    /**
     * <p>Constructs new exception with empty message and cause.</p>
     */
    ParseException() {
        super();
    }

    /**
     * <p>Constructs new exception with specified message and empty cause.</p>
     *
     * @param message the detail message.
     */
    ParseException(String message) {
        super(message);
    }

    /**
     * <p>Constructs new exception with specified message and cause.
     *
     * @param message the detail message.
     * @param cause   the throwable cause.
     */
    ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructs new exception with empty message and specified cause.</p>
     *
     * @param cause the throwable cause.
     */
    ParseException(Throwable cause) {
        super(cause);
    }
}
