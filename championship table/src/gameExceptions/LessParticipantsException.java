package gameExceptions;

public class LessParticipantsException extends Exception{
	public LessParticipantsException() {
		super("The List Contains Less than the alowed Number Of Participants");
	}
}

