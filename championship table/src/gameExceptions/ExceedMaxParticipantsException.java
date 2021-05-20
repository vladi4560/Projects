package gameExceptions;

public class ExceedMaxParticipantsException extends Exception {
	public ExceedMaxParticipantsException() {
		super("The List Contains The Maxsimum Number Of Participants");
	}
}
