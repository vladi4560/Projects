package gameExceptions;

public class PlayerExistException extends Exception {
	public PlayerExistException() {
	super("This Player Is Already Exist");
	}
}