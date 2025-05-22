package exceptions;

public class InvalidAccountException extends RuntimeException{

	  private static final long serialVersionUID = 1L;
	  public enum Role { SENDER, RECEIVER, GENERAL }
	  private final Role role;
	
	  public InvalidAccountException(Role role) {
		  this.role = role; 
	  }
	  
	  public Role getRole() { return role; }
	
}
