package exceptions;

public class BallotNotExistException extends Exception{
	public BallotNotExistException(){
		super("Ballot doesn't exist! cannot add the citizen to ballot. You must add the ballot first!");
	}
}
