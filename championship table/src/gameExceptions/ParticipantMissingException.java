package gameExceptions;

public class ParticipantMissingException extends Exception{
public ParticipantMissingException() {
	super("Cannot make this play!. There is a missing player.");
}
	
}
