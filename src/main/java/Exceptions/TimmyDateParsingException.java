package Exceptions;

public class TimmyDateParsingException extends RuntimeException {
    public TimmyDateParsingException() {};
    public TimmyDateParsingException(String message) {
        super(message);
    }
}
