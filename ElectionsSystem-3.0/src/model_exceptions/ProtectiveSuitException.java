package model_exceptions;

public class ProtectiveSuitException extends Exception {
	public ProtectiveSuitException() {
		super("You cannot vote without a protective suit!.");
	}
}
