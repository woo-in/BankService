package exceptions;

public class InsufficientFundsException extends Exception {
//    private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String message) {
        super(message);
    }
}