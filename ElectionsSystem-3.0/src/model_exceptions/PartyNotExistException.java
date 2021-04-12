package model_exceptions;

public class PartyNotExistException extends Exception {
	public PartyNotExistException(){
		super("Party doen't exist!, cannot add the nominee");
	}
}
