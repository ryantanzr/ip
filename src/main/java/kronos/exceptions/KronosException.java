package kronos.exceptions;

/**
 * Represents a custom exception class for handling Kronos-specific
 * exceptions.
 */
public class KronosException extends Exception {

    private static final String errorCatchphrase = "TICKTOCK! ";

    public KronosException(String message) {
        super(errorCatchphrase + message);
    }
}
