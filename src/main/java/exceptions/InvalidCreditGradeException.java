package exceptions;

public class InvalidCreditGradeException extends Exception{
//	private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

	public InvalidCreditGradeException(String message){
        super(message);
    }
}
