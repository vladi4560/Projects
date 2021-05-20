package GameView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import GameListeners.ViewListenable;
import GameListeners.ViewPointsListenable;
import controller.ChampionshipController;
import gameExceptions.TheTextFieldIsEmptyException;
import gameExceptions.tieException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewPoints implements EventHandler<ActionEvent> {
	private ArrayList<ViewPointsListenable> ViewPointsListeners = new ArrayList<ViewPointsListenable>();
	private String chosenGame = "";
	private String player2;
	private String player1;
	private Vector<TextField> scorePlayer1;
	private Vector<TextField> scorePlayer2;
	private boolean soccerIsTie = true;
	private int gameIndex;
	private Stage viewPointStage;
	private HBox scoreP1HBox;
	private HBox scoreP2HBox;

	public ViewPoints() {

	}

	public void reset(String player1, String player2, String chosenGame, int gameIndex) {
		this.chosenGame = chosenGame;
		this.player1 = player1;
		this.player2 = player2;
		this.gameIndex = gameIndex;
		this.scoreP1HBox = new HBox(20);
		this.scoreP2HBox = new HBox(20);

	}

	public void handle(ActionEvent event) {
		Label fillInScore = new Label("Fill in The Score");
		fillInScore.setTextFill(Color.GREY);
		fillInScore.setFont(new Font(30));
		fillInScore.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		fillInScore.setAlignment(Pos.TOP_CENTER);
		viewPointStage = new Stage();
		viewPointStage.setTitle("Championship Game");
		VBox PointsScore = new VBox(30);

		Label playerL1 = new Label(this.player1 + ":");
		Label playerL2 = new Label(this.player2 + ":");

		PointsScore.getChildren().addAll(fillInScore);

		scorePlayer1 = createScorePlayerText();
		scorePlayer2 = createScorePlayerText();
		Vector<Integer> sumScorePlayer1 = new Vector<Integer>();
		Vector<Integer> sumScorePlayer2 = new Vector<Integer>();

		if (chosenGame.equals("Basketball")) {
			Label basketballGame = new Label("Basketball Game");
			basketballGame.setTextFill(Color.GREY);
			basketballGame.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			basketballGame.setAlignment(Pos.TOP_CENTER);
			PointsScore.getChildren().addAll(basketballGame);

		} else if (chosenGame.equals("Tennis")) {
			Label tennisGame = new Label("Tennis Game");
			tennisGame.setTextFill(Color.GREY);
			tennisGame.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			tennisGame.setAlignment(Pos.TOP_CENTER);
			tennisGame.setAlignment(Pos.CENTER);
			PointsScore.getChildren().addAll(tennisGame);

		} else {
			Label soccerGame = new Label("Soccer Game");
			soccerGame.setTextFill(Color.GREY);
			soccerGame.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			soccerGame.setAlignment(Pos.TOP_CENTER);
			soccerGame.setAlignment(Pos.CENTER);
			PointsScore.getChildren().addAll(soccerGame);

		}

		Button doneButton = new Button("Done");
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					for (int i = 0; i < scorePlayer1.size(); i++) {
						if ((!(scorePlayer1.get(i).getText().isEmpty()))
								&& (!(scorePlayer2.get(i).getText().isEmpty()))) {
							sumScorePlayer1.add(Integer.parseInt(scorePlayer1.get(i).getText()));
							sumScorePlayer2.add(Integer.parseInt(scorePlayer2.get(i).getText()));
						} else {
							throw new TheTextFieldIsEmptyException();
						}
					}

				} catch (TheTextFieldIsEmptyException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				try {
					for (ViewPointsListenable l : ViewPointsListeners) {
						l.FireSendPointsGameToModel(player1, player2, sumScorePlayer1, sumScorePlayer2, chosenGame,
								gameIndex);
					}
				} catch (tieException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});

		scoreP1HBox.getChildren().add(playerL1);
		scoreP1HBox.getChildren().addAll(scorePlayer1);
		scoreP1HBox.setAlignment(Pos.CENTER);
		scoreP2HBox.getChildren().addAll(playerL2);
		scoreP2HBox.getChildren().addAll(scorePlayer2);
		scoreP2HBox.setAlignment(Pos.CENTER);

		PointsScore.getChildren().addAll(scoreP1HBox, scoreP2HBox, doneButton);
		PointsScore.setAlignment(Pos.CENTER);

		viewPointStage.setScene(new Scene(PointsScore, 400, 400));
		viewPointStage.show();

	}

	////////////////////////////////// METHODS/////////////////////////////////////////////////////////////////////////////////////

	

	private Vector<TextField> createScorePlayerText() {
		Vector<TextField> scorePlayer = new Vector<TextField>();
		int index = 0;
		if (chosenGame.equals("Basketball")) {
			index = 4;
		}
		if (chosenGame.equals("Tennis")) {
			index = 3;
		}
		if (chosenGame.equals("Soccer")) {
			index = 2;
		}
		for (int j = 0; j < index; j++) {
			TextField scorePlayerText = new TextField();
			scorePlayerText.setPrefWidth(40);
			scorePlayer.add(scorePlayerText);
		}
		return scorePlayer;
	}

	public void registerListener(ViewPointsListenable listener) {
		ViewPointsListeners.add(listener);
	}

	public void GameResualtIsTie() throws tieException {
		if (scorePlayer1.size() == 5) {
			throw new tieException();
		}
		int index;
		if (chosenGame.equals("Tennis")) {
			index = 2;
		} else {
			if (soccerIsTie) {
				index = 1;
				soccerIsTie = false;
			} else
				index = 2;
		}
		for (int i = 0; i < index; i++) {
			TextField scorePlayerText1 = new TextField();
			TextField scorePlayerText2 = new TextField();
			scorePlayerText1.setPrefWidth(40);
			scorePlayerText2.setPrefWidth(40);
			scorePlayer1.add(scorePlayerText1);
			scorePlayer2.add(scorePlayerText2);
			scoreP1HBox.getChildren().add(scorePlayerText1);
			scoreP2HBox.getChildren().add(scorePlayerText2);
			if (i == 2) {
				return;
			}
		}
	}

	public void close() {
		viewPointStage.close();
	}

}