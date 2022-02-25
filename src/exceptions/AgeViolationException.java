package exceptions;

public class AgeViolationException extends RuntimeException{
    public AgeViolationException(String message) {
        super(message);
    }
}
