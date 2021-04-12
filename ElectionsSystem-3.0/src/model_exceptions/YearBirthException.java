package model_exceptions;

public class YearBirthException extends Exception {

	public YearBirthException() {
		super("Currnet ciziten under 18, cannot vote! adding the citizen to the voter register failed");
	}
}
