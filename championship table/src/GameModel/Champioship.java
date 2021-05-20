package GameModel;

import java.util.ArrayList;
import java.util.Vector;

import GameListeners.GameModelListenable;
import GameListeners.ViewListenable;
import gameExceptions.ExceedMaxParticipantsException;
import gameExceptions.LessParticipantsException;
import gameExceptions.PlayerExistException;
import gameExceptions.TheTextFieldIsEmptyException;
import gameExceptions.tieException;
import gameExceptions.ParticipantMissingException;

public class Champioship {
	private Vector<GameModelListenable> gameModelListeners;
	private final int MAX_PLAYERS = 8;
	private Vector<String> playersNames;
	

	public Champioship() {
		this.gameModelListeners = new Vector<GameModelListenable>();
		this.playersNames = new Vector<String>();
		
	}

	private boolean playerExist(String name) {
		for (String i : playersNames) {
			if (i.equals(name)) {
				return false;
			}
		}
		return true;
	}

	public void addPlayer(String name)
			throws TheTextFieldIsEmptyException, ExceedMaxParticipantsException, PlayerExistException {
		if (name.isEmpty()) {
			throw new TheTextFieldIsEmptyException();
		}
		if (playersNames.size() == MAX_PLAYERS) {
			throw new ExceedMaxParticipantsException();
		}
		if (!playerExist(name)) {
			throw new PlayerExistException();
		}
		playersNames.add(name);
		fireSuccesAddingParticipantToModel(name);
	}

	private void fireSuccesAddingParticipantToModel(String name) {
		for (GameModelListenable l : gameModelListeners) {
			l.SuccesAddingParticipantToModel(name);
		}

	}

	public void startChampioship(String chosenGame) throws LessParticipantsException {
		if (!(playersNames.size() == 8)) {
			throw new LessParticipantsException();
		}
	}

	public void playGame(String player1, String player2, Vector<Integer> scoreP1, Vector<Integer> scoreP2,
			String gameType, int gameIndex) throws tieException {
		String winner = "";
		if (gameType.equals("Basketball")) {
			Basketball game = new Basketball(player1, player2);
			winner = playBasketballGame(game, scoreP1, scoreP2);
		} else if (gameType.equals("Tennis")) {
			Tennis game = new Tennis(player1, player2);
			winner = playTennisGame(game, scoreP1, scoreP2);
		} else if (gameType.equals("Soccer")) {
			Soccer game = new Soccer(player1, player2);
			winner = playSoccerGame(game, scoreP1, scoreP2);

		}
		FireGameWinnerFromModel(winner, gameIndex);
	}
	private void FireGameWinnerFromModel(String winner, int gameIndex) throws tieException {
		for (GameModelListenable l : gameModelListeners) {
			l.WinnerGameFromModel(winner, gameIndex);
		}

	}

	private String playSoccerGame(Soccer game, Vector<Integer> scoreP1, Vector<Integer> scoreP2) {
		String winner = "";
		for (int i = 0; i < scoreP1.size(); i++) {

			game.fillInScore(scoreP1.get(i), scoreP2.get(i));
		}
		winner = game.GameWinner();
		if (winner == null) {
			return null;
		}
		return winner;
	}

	private String playTennisGame(Tennis game, Vector<Integer> scoreP1, Vector<Integer> scoreP2) {
		String winner = "";
		for (int i = 0; i < scoreP1.size(); i++) {

			game.fillInScore(scoreP1.get(i), scoreP2.get(i));
		}
		winner = game.GameWinner();
		if (winner == null) {
			return null;
		}
		return winner;
	}

	

	private String playBasketballGame(Basketball game, Vector<Integer> scoreP1, Vector<Integer> scoreP2) {
		String winner = "";
		for (int i = 0; i < scoreP1.size(); i++) {

			game.fillInScore(scoreP1.get(i), scoreP2.get(i));
		}
		winner = game.GameWinner();
		if (winner == null) {
			return null;
		}

		return winner;
	}

	public void registerListener(GameModelListenable listener) {
		gameModelListeners.add(listener);
	}

	public void checkIfCanPlay(String player1, String player2) throws ParticipantMissingException {
		if (player1.isEmpty() || player2.isEmpty()) {
			throw new ParticipantMissingException();
		}
	}



}