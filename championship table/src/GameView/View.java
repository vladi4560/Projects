package GameView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import GameListeners.ViewListenable;
import GameListeners.ViewPointsListenable;
import GameModel.Champioship;
import gameExceptions.ExceedMaxParticipantsException;
import gameExceptions.LessParticipantsException;
import gameExceptions.PlayerExistException;
import gameExceptions.TheTextFieldIsEmptyException;
import gameExceptions.tieException;
import gameExceptions.ParticipantMissingException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sun.font.CreatedFontTracker;

public class View {
	private ArrayList<ViewListenable> allListeners = new ArrayList<ViewListenable>();
	private Vector<Label> listOfLabelPlayers = new Vector<Label>();
	private StackPane currentPage = new StackPane();
	private BorderPane champioshipPage1 = new BorderPane();
	private VBox championshipPage2 = new VBox(10);
	public static String chosenGame = "";
	private VBox addChampionship = new VBox(10);
	private VBox GameType = new VBox(10);
	private VBox addParticipants = new VBox(10);
	private VBox participantsList = new VBox(60);
	private HBox pyramidMatches = new HBox(10);
	private ViewPoints resultsPage;
	private String currentButtonPush;

	List<TextField> firstGameWinnerParticipate = new ArrayList<TextField>();
	List<TextField> secGameWinnerParticipate = new ArrayList<TextField>();
	List<TextField> finalParticpateWinner = new ArrayList<TextField>();
	List<Button> firstGamesBtn = new ArrayList<Button>();
	List<Button> sectGamesBtn = new ArrayList<Button>();
	List<Button> finalGameBtn = new ArrayList<Button>();

	public View(Stage ChampionshipStage, ViewPoints viewPoints) throws FileNotFoundException {

		resultsPage = viewPoints;

		ChampionshipStage.setTitle("Championship Game");
		Label welcome = new Label("Welcome To The Championship");
		welcome.setTextFill(Color.GREY);
		welcome.setFont(new Font(30));
		welcome.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		champioshipPage1.setAlignment(welcome, Pos.TOP_CENTER);
		champioshipPage1.setTop(welcome);
		////////////////////////////// First
		////////////////////////////// Page///////////////////////////////////////////////////////////////

		// Add Championship

		// GameType
		Label askGameType = new Label("Choose Game Championship Type: ");
		askGameType.setTextFill(Color.WHITE);
		askGameType.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		ToggleGroup btnGameTypes = new ToggleGroup();
		RadioButton btnGameType1 = new RadioButton("Basketball");
		RadioButton btnGameType2 = new RadioButton("Tennis");
		RadioButton btnGameType3 = new RadioButton("Soccer");
		btnGameType1.setToggleGroup(btnGameTypes);
		btnGameType2.setToggleGroup(btnGameTypes);
		btnGameType3.setToggleGroup(btnGameTypes);

		btnGameType1.setTextFill(Color.WHITE);
		btnGameType2.setTextFill(Color.WHITE);
		btnGameType3.setTextFill(Color.WHITE);
		GameType.setSpacing(10);
		GameType.setAlignment(Pos.CENTER);
		GameType.setBackground(
				new Background(new BackgroundFill(Color.GREY, new CornerRadii(500), new Insets(0, 300, 0, 300))));
		GameType.getChildren().addAll(askGameType, btnGameType1, btnGameType2, btnGameType3);

		MyRadioEventHandler gameTypes = new MyRadioEventHandler();
		btnGameType1.setOnAction(gameTypes);
		btnGameType2.setOnAction(gameTypes);
		btnGameType3.setOnAction(gameTypes);

		// Add Participant
		addParticipants.setVisible(false);
		addParticipants.setAlignment(Pos.CENTER);
		HBox ParticipantRow = new HBox();
		Label askParticipantName = new Label("Participant Name: ");
		askParticipantName.setTextFill(Color.GREY);
		TextField participantName = new TextField();
		ParticipantRow.setSpacing(10);
		ParticipantRow.setAlignment(Pos.CENTER);
		ParticipantRow.getChildren().addAll(askParticipantName, participantName);

		Button btnAddParticipant = new Button();

		btnAddParticipant.setText("Add Participant");
		btnAddParticipant.setTextFill(Color.WHITE);
		btnAddParticipant
				.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
		btnAddParticipant.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// add participant
				for (ViewListenable l : allListeners) {
					try {
						l.FireAddParticipant(participantName.getText());
						participantName.setText(null);
					} catch (ExceedMaxParticipantsException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (TheTextFieldIsEmptyException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (PlayerExistException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

		});

		Button btnStratChampionship = new Button();
		btnStratChampionship.setText("Start Championship");
		btnStratChampionship.setTextFill(Color.WHITE);
		btnStratChampionship
				.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
		btnStratChampionship.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				for (ViewListenable l : allListeners) {
					try {
						l.FireStartChampionship(chosenGame);
						champioshipPage1.setVisible(false);
						champioshipPage1.getChildren().remove(participantsList);
						SecondPageBuild();
						pyramidMatches.setVisible(true);
						championshipPage2.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

		});

		addParticipants.getChildren().addAll(ParticipantRow, btnAddParticipant, btnStratChampionship);

		addChampionship.getChildren().addAll(GameType, addParticipants);
		addChampionship.setAlignment(Pos.CENTER);

		for (int i = 0; i < 8; i++) {
			Label lPlayer = new Label();
			listOfLabelPlayers.add(lPlayer);
			lPlayer.setTextFill(Color.AQUA);
			lPlayer.setBackground(
					new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
			lPlayer.setBorder(new Border(
					new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		}
		participantsList.getChildren().addAll(listOfLabelPlayers);

		champioshipPage1.setCenter(addChampionship);
		champioshipPage1.setLeft(participantsList);

/////////////////////////////////////////Second Page///////////////////////////////////////////////////////////////

		pyramidMatches.setVisible(false);
		championshipPage2.setVisible(false);

		currentPage.getChildren().addAll(champioshipPage1, championshipPage2);
		ChampionshipStage.setScene(new Scene(currentPage, 0, 0));
		ChampionshipStage.show();

	}

////////////////////////////////////Methods/////////////////////////////////////////////////////////////////////////////////	

	public void builderHelper(int index) {
		VBox lines = new VBox(85);
		int indexTemp = index;
		for (int i = 0; i < indexTemp; i++) {
			VBox lineTemp = new VBox(0);
			HBox line2 = new HBox(0);
			HBox line3 = new HBox(0);
			Line l1 = new Line(0, 0, 60, 0);
			Line l2 = new Line(0, 0, 0, 40);
			Line l3 = new Line(0, 0, 60, 0);
			line2.getChildren().addAll(l1);
			line3.getChildren().addAll(l3);
			lineTemp.getChildren().addAll(line2, line3);
			lineTemp.setAlignment(Pos.CENTER);
			lines.getChildren().add(lineTemp);
			lines.setAlignment(Pos.CENTER_LEFT);
		}
		pyramidMatches.getChildren().add(lines);
	}

	public void SecondPageBuild() {
		Label championshipTitle = new Label("Championship");
		championshipTitle.setTextFill(Color.GREY);
		championshipTitle.setFont(new Font(30));
		championshipTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		VBox titlePage2 = new VBox();
		titlePage2.getChildren().add(championshipTitle);
		titlePage2.setAlignment(Pos.CENTER);
		participantsList.setAlignment(Pos.CENTER_LEFT);
		pyramidMatches.getChildren().addAll(participantsList);

		orderSecondPage(4, firstGamesBtn, "firstGamesBtn", firstGameWinnerParticipate, 90, 20);

		orderSecondPage(2, sectGamesBtn, "sectGamesBtn", secGameWinnerParticipate, 160, 65);

		orderSecondPage(1, finalGameBtn, "finalGameBtn", finalParticpateWinner, 15, 150);

		pyramidMatches.setAlignment(Pos.CENTER);
		championshipPage2.getChildren().addAll(titlePage2, pyramidMatches);
	}

	private void orderSecondPage(int size, List<Button> games, String gameNameCollection, List<TextField> particpates,
			double distance, int y) {
		VBox orderRound = new VBox();
		List<HBox> partOfRound = new ArrayList<HBox>();
		for (int i = 0; i < size; i++) {
			Button b = new Button("play game");
			games.add(b);
			createGameView(b, i + 1, gameNameCollection);
			particpates.add(new TextField());
			partOfRound.add(new HBox());
			VBox Temp = new VBox();
			Temp.getChildren().addAll(new Line(0, 0, 0, y), games.get(i), new Line(0, 0, 0, y));
			Temp.setAlignment(Pos.CENTER);
			VBox Temp2 = new VBox(new Line(0, 0, 43, 0), Temp, new Line(0, 0, 43, 0));
			if (gameNameCollection.equals("finalGameBtn")) {
				Label theWinner = new Label("The Winner");
				theWinner.setTextFill(Color.AQUA);
				theWinner.setFont(Font.font("Arial", FontWeight.BOLD, 15));
				theWinner
						.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
				partOfRound.get(i).getChildren().addAll(Temp2, new Line(0, 0, 15, 0), theWinner, particpates.get(i));
			} else {
				partOfRound.get(i).getChildren().addAll(Temp2, new Line(0, 0, 15, 0), particpates.get(i));
			}
			partOfRound.get(i).setAlignment(Pos.CENTER);
			orderRound.getChildren().add(partOfRound.get(i));
			orderRound.setAlignment(Pos.CENTER_LEFT);
		}

		orderRound.setSpacing(distance);
		pyramidMatches.getChildren().add(orderRound);
	}

	private void createGameView(Button playGamebutton, int index, String gameNameCollection) {
		playGamebutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					currentButtonPush = gameNameCollection;
					if (gameNameCollection.equals("firstGamesBtn")) {
						resultsPage.reset(listOfLabelPlayers.get((2 * index) - 2).getText(),
								listOfLabelPlayers.get((2 * index) - 1).getText(), chosenGame, index - 1);
						resultsPage.handle(event);
					} else if (gameNameCollection.equals("sectGamesBtn")) {
						for (ViewListenable l : allListeners) {
							l.FireCheckIfCanPlay(firstGameWinnerParticipate.get((2 * index) - 2).getText(),
									firstGameWinnerParticipate.get((2 * index) - 1).getText());
						}
						resultsPage.reset(firstGameWinnerParticipate.get((2 * index) - 2).getText(),
								firstGameWinnerParticipate.get((2 * index) - 1).getText(), chosenGame, index - 1);
						resultsPage.handle(event);
					} else {
						for (ViewListenable l : allListeners) {
							l.FireCheckIfCanPlay(secGameWinnerParticipate.get((2 * index) - 2).getText(),
									secGameWinnerParticipate.get((2 * index) - 1).getText());
						}
						resultsPage.reset(secGameWinnerParticipate.get((2 * index) - 2).getText(),
								secGameWinnerParticipate.get((2 * index) - 1).getText(), chosenGame, index - 1);
						resultsPage.handle(event);
					}
				} catch (ParticipantMissingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

	}

	public void addToListParticipants(String participantName) {
		for (int i = 0; i < listOfLabelPlayers.size(); i++) {
			if (listOfLabelPlayers.get(i).getText().isEmpty()) {
				listOfLabelPlayers.get(i).setText(participantName);
				return;
			}
		}

	}

	public void registerListener(ViewListenable listener) {
		allListeners.add(listener);
	}

	class MyRadioEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent ae) {
			addParticipants.setVisible(true);
			chosenGame = ((RadioButton) ae.getSource()).getText();
		}

	}

	public void SuccesRemovePlayerInModel(String participantName) {
		for (int i = 0; i < participantsList.getChildren().size(); i++) {
			if (participantsList.getChildren().get(i).toString().equals(participantName)) {
				listOfLabelPlayers.get(i).setText("");
			}
		}
	}

	public void updateWinner(String winner, int gameIndex) {

		if (currentButtonPush.equals("firstGamesBtn")) {
			firstGameWinnerParticipate.get(gameIndex).setText(winner);
			resultsPage.close();
		} else if (currentButtonPush.equals("sectGamesBtn")) {
			secGameWinnerParticipate.get(gameIndex).setText(winner);
			resultsPage.close();
		} else if (currentButtonPush.equals("finalGameBtn")) {
			finalParticpateWinner.get(0).setText(winner);
			resultsPage.close();
		}
	}

	public void gameResultIsTie() throws tieException {
		this.resultsPage.GameResualtIsTie();
	}
}
