package GameListeners;

import gameExceptions.ExceedMaxParticipantsException;
import gameExceptions.LessParticipantsException;
import gameExceptions.PlayerExistException;
import gameExceptions.TheTextFieldIsEmptyException;
import gameExceptions.ParticipantMissingException;
import javafx.scene.control.Button;

public interface ViewListenable {
	void FireAddParticipant(String participantName) throws TheTextFieldIsEmptyException, ExceedMaxParticipantsException, PlayerExistException;
	void FireStartChampionship(String chosenGame) throws LessParticipantsException;
	void FireCheckIfCanPlay(String player1, String player2) throws ParticipantMissingException;
}
