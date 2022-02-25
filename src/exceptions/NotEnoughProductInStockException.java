package exceptions;

public class NotEnoughProductInStockException extends RuntimeException{
    public NotEnoughProductInStockException(String message) {
        super(message);
    }
}
