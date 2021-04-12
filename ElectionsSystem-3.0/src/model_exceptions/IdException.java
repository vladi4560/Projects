package model_exceptions;

public class IdException extends Exception {
	
	public IdException() {
		super("ID need to be 9 digits");
	}
}
