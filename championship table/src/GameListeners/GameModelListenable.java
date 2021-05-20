package GameListeners;

import gameExceptions.tieException;

public interface GameModelListenable {

	void SuccesAddingParticipantToModel(String name);

	void WinnerGameFromModel(String winner, int gameIndex) throws tieException;


}
