package controller;

import java.util.Vector;
import GameListeners.GameModelListenable;
import GameListeners.ViewListenable;
import GameListeners.ViewPointsListenable;
import GameModel.Champioship;
import GameView.View;
import GameView.ViewPoints;
import gameExceptions.ExceedMaxParticipantsException;
import gameExceptions.LessParticipantsException;
import gameExceptions.PlayerExistException;
import gameExceptions.TheTextFieldIsEmptyException;
import gameExceptions.tieException;
import gameExceptions.ParticipantMissingException;

public class ChampionshipController implements GameModelListenable, ViewListenable, ViewPointsListenable {

	private Champioship theModel;
	private View theView;
	private ViewPoints theViewPoints;

	public ChampionshipController(Champioship theModel, View theView, ViewPoints theViewPoints) {
		this.theModel = theModel;
		this.theView = theView;
		this.theViewPoints = theViewPoints;

		theModel.registerListener(this);
		theView.registerListener(this);
		theViewPoints.registerListener(this);
	}

	public void FireAddParticipant(String participantName)
			throws TheTextFieldIsEmptyException, ExceedMaxParticipantsException, PlayerExistException {
		theModel.addPlayer(participantName);
	}

	public void SuccesAddingParticipantToModel(String participantName) {
		theView.addToListParticipants(participantName);
	}

	@Override
	public void FireStartChampionship(String chosenGame) throws LessParticipantsException {
		theModel.startChampioship(chosenGame);
	}

	@Override
	public void WinnerGameFromModel(String winner, int gameIndex) throws tieException {
		if (winner == null) {
			theViewPoints.GameResualtIsTie();
		} else {
			theView.updateWinner(winner, gameIndex);
		}
	}



	public void FireSendPointsGameToModel(String player1, String player2, Vector<Integer> sumScorePlayer1,
			Vector<Integer> sumScorePlayer2, String chosenGame, int gameIndex) throws tieException {
		theModel.playGame(player1, player2, sumScorePlayer1, sumScorePlayer2, chosenGame, gameIndex);
	}

	@Override
	public void FireCheckIfCanPlay(String player1, String player2) throws ParticipantMissingException {
		theModel.checkIfCanPlay(player1, player2);

	}

}
