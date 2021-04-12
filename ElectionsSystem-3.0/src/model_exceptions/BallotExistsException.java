package model_exceptions;


public class BallotExistsException extends Exception {
public BallotExistsException() {
	super("The ballot already exists");
}
}
