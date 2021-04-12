package model_exceptions;

public class PartyExistException extends Exception {
	public PartyExistException() {
		super("Party already exist!");
	}
}
