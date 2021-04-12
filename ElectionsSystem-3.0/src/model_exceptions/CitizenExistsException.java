package model_exceptions;

public class CitizenExistsException extends Exception {
	public CitizenExistsException() {
		super("The Citizen already exists!");
	}
}
