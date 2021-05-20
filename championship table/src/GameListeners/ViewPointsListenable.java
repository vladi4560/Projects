package GameListeners;

import java.util.ArrayList;
import java.util.Vector;

import gameExceptions.tieException;

public interface ViewPointsListenable {

	void FireSendPointsGameToModel(String player1, String player2, Vector<Integer> sumScorePlayer1, Vector<Integer> sumScorePlayer2, String chosenGame, int gameIndex) throws tieException;
	
}
