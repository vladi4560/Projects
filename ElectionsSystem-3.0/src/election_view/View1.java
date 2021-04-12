package election_view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.webkit.ContextMenu.ShowContext;

import election_listeners.ViewListenable;
import election_system_model.Citizen;
import election_system_model.Party;
import election_system_model.Party.Faction;
import election_system_model.RoundOfElections;
import election_system_model.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_exceptions.BallotExistsException;
import model_exceptions.BallotNotExistException;
import model_exceptions.CitizenExistsException;
import model_exceptions.IdException;
import model_exceptions.PartyExistException;
import model_exceptions.PartyNotExistException;
import model_exceptions.ProtectiveSuitException;
import model_exceptions.YearBirthException;

public class View1 {
	private ArrayList<ViewListenable> allListeners = new ArrayList<ViewListenable>();
	private StackPane currentWindow = new StackPane();
	private VBox menu = new VBox(10);
	private Set<Citizen> listOfCiziten= new Set<Citizen>();
	private Vector<Party> listOfParties= new Vector<Party>();

	private class CurrentWindowButtonHandler implements EventHandler<ActionEvent> {
		StackPane currentWindow1;
		Pane pane;

		public CurrentWindowButtonHandler(StackPane currentWindow, Pane pane) {
			super();
			this.currentWindow1 = currentWindow;
			this.pane = pane;
		}

		@Override
		public void handle(ActionEvent event) {
			for (Node otherPane : currentWindow1.getChildren()) {
				otherPane.setVisible(false);
			}
			pane.setVisible(true);
		}
	}

	private void addCurrentWindowOption(Button button, Pane pane) {
		menu.getChildren().add(button);
		currentWindow.getChildren().add(pane);

		button.setOnAction(new CurrentWindowButtonHandler(currentWindow, pane));
	}

	public View1(Stage electionStage) throws FileNotFoundException {
		BorderPane system = new BorderPane();
		Button btnAddBallot = new Button();
		Button btnAddCitizen = new Button();
		Button btnAddParty = new Button();
		Button btnAddNomniee = new Button();
		Button btnShowBallots = new Button();
		Button btnshowCitizens = new Button();
		Button btnShowParties = new Button();
		Button btnMakeElection = new Button();
		Button btnElectionResults = new Button();
		Button btnexit = new Button();

		electionStage.setTitle("Election Round System");

		Label welcome = new Label("Welcome to the Election Round System");
		welcome.setTextFill(Color.WHITESMOKE);
		welcome.setFont(new Font(30));
		welcome.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		system.setAlignment(welcome, Pos.TOP_CENTER);
		Label menuTitle = new Label("        Menu");
		menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		menuTitle.setTextFill(Color.WHITE);
		system.setAlignment(menuTitle, Pos.CENTER);
		Label choice = new Label("Please make your choice");
		choice.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		choice.setTextFill(Color.WHITE);

///////////////////////////////ADD BAllOT///////////////////////////////////////////////////////////////

		VBox ballotPage = new VBox(10);
		btnAddBallot.setText("Add Ballot");
		btnAddBallot.setTextFill(Color.BLACK);
		btnAddBallot.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		HBox ballotRow = new HBox();
		Label askSerialNum = new Label("Enter Ballot Serial Number:");
		askSerialNum.setTextFill(Color.WHITE);
		TextField serialNum = new TextField();
		ballotRow.getChildren().addAll(askSerialNum, serialNum);
		ballotRow.setSpacing(10);
		ballotRow.setAlignment(Pos.CENTER);

		HBox streetRow = new HBox();
		Label askStreet = new Label("Enter Street Name:");
		askStreet.setTextFill(Color.WHITE);
		TextField street = new TextField();
		streetRow.getChildren().addAll(askStreet, street);
		streetRow.setSpacing(10);
		streetRow.setAlignment(Pos.CENTER);

		HBox ballotTypeRow = new HBox();
		Label askBallotType = new Label("Select Ballot Type: ");
		askBallotType.setTextFill(Color.WHITE);
		ComboBox<String> ballotType = new ComboBox<String>();
		ballotType.getItems().addAll("Regular", "Army", "Corona", "Corona Army");
		ballotTypeRow.getChildren().addAll(askBallotType, ballotType);
		ballotTypeRow.setAlignment(Pos.CENTER);
		Button addBallot = new Button("Add Ballot");
		addBallot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ViewListenable l : allListeners) {
					try {
						l.AddBallotFromView(Integer.parseInt(serialNum.getText()), street.getText(),
								(String) ballotType.getValue());
					} catch (BallotExistsException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}

		});

		ballotPage.getChildren().addAll(ballotRow, streetRow, ballotTypeRow, addBallot);
		ballotPage.setAlignment(Pos.CENTER);
///////////////////////////////ADD CITIZEN///////////////////////////////////////////////////////////////

		VBox citizenPage = new VBox(10);
		btnAddCitizen.setText("Add Citizen");

		HBox citizenNameRow = new HBox(5.5);
		Label askCitizenName = new Label("Enter Citizen Full Name:");
		askCitizenName.setTextFill(Color.WHITE);
		TextField citizenName = new TextField();
		citizenNameRow.getChildren().addAll(askCitizenName, citizenName);
		citizenNameRow.setAlignment(Pos.CENTER);

		HBox citizenIdRow = new HBox(5.5);
		Label askcitizenId = new Label("Enter Citizen Id:");
		askcitizenId.setTextFill(Color.WHITE);
		TextField citizenId = new TextField();
		citizenIdRow.getChildren().addAll(askcitizenId, citizenId);
		citizenIdRow.setAlignment(Pos.CENTER);

		HBox citizenYearBirthRow = new HBox(5.5);
		Label askYearBirth = new Label("Enter The Citizen Year Birth:");
		askYearBirth.setTextFill(Color.WHITE);
		TextField citizenYearBirth = new TextField();
		citizenYearBirthRow.getChildren().addAll(askYearBirth, citizenYearBirth);
		citizenYearBirthRow.setAlignment(Pos.CENTER);

		HBox checkIfSoliderRow = new HBox(5.5);
		CheckBox ifSolider = new CheckBox();
		Label askIfSoldier = new Label("Are You a Soldier?");
		askIfSoldier.setTextFill(Color.WHITE);
		CheckBox carryWeapon = new CheckBox();
		Label askCarryWeapon = new Label("Do You Carry Weapon?");
		askCarryWeapon.setTextFill(Color.WHITE);
		askCarryWeapon.setVisible(false);
		carryWeapon.setVisible(false);
		ifSolider.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				carryWeapon.setVisible(ifSolider.isSelected());
				askCarryWeapon.setVisible(ifSolider.isSelected());
			}
		});
		checkIfSoliderRow.getChildren().addAll(ifSolider, askIfSoldier, carryWeapon, askCarryWeapon);
		checkIfSoliderRow.setAlignment(Pos.CENTER);

		HBox citizenBallotIdRow = new HBox(5.5);
		Label askcitizenBallotId = new Label("Enter Ballot serial number:");
		askcitizenBallotId.setTextFill(Color.WHITE);
		TextField citizenBallotId = new TextField();
		citizenBallotIdRow.getChildren().addAll(askcitizenBallotId, citizenBallotId);
		citizenBallotIdRow.setAlignment(Pos.CENTER);

		HBox coronaRow = new HBox(5.5);
		coronaRow.setAlignment(Pos.CENTER);
		Label askCitizenHaveCorona = new Label("Do you have Corona Virus?");
		askCitizenHaveCorona.setTextFill(Color.WHITE);
		CheckBox coronaSickness = new CheckBox();
		HBox sickDaysRow = new HBox(5.5);
		sickDaysRow.setAlignment(Pos.CENTER);
		Label askSickDays = new Label("Enter Number of Sick Days:");
		askSickDays.setTextFill(Color.WHITE);
		TextField sickDays = new TextField("0");

		askSickDays.setVisible(false);
		sickDays.setVisible(false);
		coronaSickness.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				askSickDays.setVisible(coronaSickness.isSelected());
				sickDays.setVisible(coronaSickness.isSelected());
			}
		});
		coronaRow.getChildren().addAll(coronaSickness, askCitizenHaveCorona, askSickDays, sickDays);
		coronaRow.setAlignment(Pos.CENTER);

		Button addCitizen = new Button("Add Citizen");
		addCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					for (ViewListenable l : allListeners) {
						l.AddCitizenFromView(citizenName.getText(), Integer.parseInt(citizenId.getText()),
								Integer.parseInt(citizenYearBirth.getText()),
								Integer.parseInt(citizenBallotId.getText()), coronaSickness.isSelected(),
								Integer.parseInt(sickDays.getText()), ifSolider.isSelected(), carryWeapon.isSelected());
					}
				} catch (CitizenExistsException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (YearBirthException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IdException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (BallotNotExistException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

		});

		citizenPage.getChildren().addAll(citizenNameRow, citizenIdRow, citizenYearBirthRow, citizenBallotIdRow,
				checkIfSoliderRow, coronaRow, addCitizen);
		citizenPage.setAlignment(Pos.CENTER);

///////////////////////////////ADD PARTY///////////////////////////////////////////////////////////////

		VBox partyPage = new VBox(10);
		btnAddParty.setText("Add Party");

		HBox PartyNameRow = new HBox();
		PartyNameRow.setSpacing(5.5);
		Label askPartyName = new Label("Party Name: ");
		askPartyName.setTextFill(Color.WHITE);
		TextField partyName = new TextField();
		PartyNameRow.getChildren().addAll(askPartyName, partyName);
		PartyNameRow.setAlignment(Pos.CENTER);

		HBox partyTypeRow = new HBox(5.5);
		Label askType = new Label("Party Type: ");
		askType.setTextFill(Color.WHITE);
		ComboBox<String> faction = new ComboBox<String>();
		faction.getItems().addAll("LEFT", "RIGHT", "MIDDLE");
		partyTypeRow.getChildren().addAll(askType, faction);
		partyTypeRow.setAlignment(Pos.CENTER);

		HBox partyYearBirthRow = new HBox();
		partyYearBirthRow.setSpacing(5.5);
		Label askPartyYearBirth = new Label("Year Birth: ");
		askPartyYearBirth.setTextFill(Color.WHITE);
		TextField partyYearBirth = new TextField();
		partyYearBirthRow.getChildren().addAll(askPartyYearBirth, partyYearBirth);
		partyYearBirthRow.setAlignment(Pos.CENTER);

		HBox partyMonthBirthRow = new HBox();
		partyMonthBirthRow.setSpacing(5.5);
		Label askPartyMonthBirth = new Label("Month Birth: ");
		askPartyMonthBirth.setTextFill(Color.WHITE);
		TextField partyMonthBirth = new TextField();
		partyMonthBirthRow.getChildren().addAll(askPartyMonthBirth, partyMonthBirth);
		partyMonthBirthRow.setAlignment(Pos.CENTER);

		HBox partyDayBirthRow = new HBox();
		partyDayBirthRow.setSpacing(5.5);
		Label askPartyDayBirth = new Label("Day Birth: ");
		askPartyDayBirth.setTextFill(Color.WHITE);
		TextField partyDayBirth = new TextField();
		partyDayBirthRow.getChildren().addAll(askPartyDayBirth, partyDayBirth);
		partyDayBirthRow.setAlignment(Pos.CENTER);

		Button addParty = new Button("Add Party");
		addParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					for (ViewListenable l : allListeners) {
						l.AddPartyFromView(partyName.getText(), (Faction.valueOf(faction.getValue())),
								Integer.parseInt(partyYearBirth.getText()), Integer.parseInt(partyMonthBirth.getText()),
								Integer.parseInt(partyDayBirth.getText()));
					}
				} catch (PartyExistException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		partyPage.getChildren().addAll(PartyNameRow, partyTypeRow, partyYearBirthRow, partyMonthBirthRow,
				partyDayBirthRow, addParty);
		partyPage.setAlignment(Pos.CENTER);

///////////////////////////////ADD NOMINEE///////////////////////////////////////////////////////////////

		VBox NomineePage = new VBox(10);
		btnAddNomniee.setText("Add Nomniee");

		HBox nomineeNameRow = new HBox();
		Label asknomineeName = new Label("Enter the Nomniee name:");
		asknomineeName.setTextFill(Color.WHITE);
		TextField nomineeName = new TextField();
		nomineeNameRow.getChildren().addAll(asknomineeName, nomineeName);
		nomineeNameRow.setAlignment(Pos.CENTER);

		Label askNomineeID = new Label("Enter the Nomnie Id:");
		askNomineeID.setTextFill(Color.WHITE);
		TextField nomineeID = new TextField();
		HBox nomineeIdRow = new HBox();
		nomineeIdRow.getChildren().addAll(askNomineeID, nomineeID);
		nomineeIdRow.setAlignment(Pos.CENTER);

		Label askNomineeYear = new Label("Enter the Nomnie Yeat Birth:");
		askNomineeYear.setTextFill(Color.WHITE);
		TextField nomineeYear = new TextField();
		HBox nomineeYearRow = new HBox();
		nomineeYearRow.getChildren().addAll(askNomineeYear, nomineeYear);
		nomineeYearRow.setAlignment(Pos.CENTER);

		Label askNomineeBallot = new Label("Enter the Nomnie Ballot Serial Number:");
		askNomineeBallot.setTextFill(Color.WHITE);
		TextField nomineeBallot = new TextField();
		HBox nomineeBallotRow = new HBox();
		nomineeBallotRow.getChildren().addAll(askNomineeBallot, nomineeBallot);
		nomineeBallotRow.setAlignment(Pos.CENTER);

		HBox nomineeSickRow = new HBox(5.5);
		Label asknomineeSick = new Label("Are you in Isolation?");
		asknomineeSick.setTextFill(Color.WHITE);
		CheckBox nomineeIsSick = new CheckBox();
		Label askNomineeSickDays = new Label("Sick Days: ");
		askNomineeSickDays.setTextFill(Color.WHITE);
		TextField nomineeSickDays = new TextField("0");
		askNomineeSickDays.setVisible(false);
		nomineeSickDays.setVisible(false);
		nomineeSickRow.getChildren().addAll(nomineeIsSick, asknomineeSick, askNomineeSickDays, nomineeSickDays);
		nomineeSickRow.setAlignment(Pos.CENTER);
		nomineeIsSick.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				askNomineeSickDays.setVisible(nomineeIsSick.isSelected());
				nomineeSickDays.setVisible(nomineeIsSick.isSelected());
			}

		});

		HBox nomineePartyRow = new HBox();
		Label asknomineeParty = new Label("Enter the Nomnie Party Name:");
		asknomineeParty.setTextFill(Color.WHITE);
		TextField nomineeParty = new TextField();
		nomineePartyRow.getChildren().addAll(asknomineeParty, nomineeParty);
		nomineePartyRow.setAlignment(Pos.CENTER);

		HBox nomineePrimaryRow = new HBox();
		Label asknomineePrimary = new Label("Enter the Nomnie Primary Name:");
		asknomineePrimary.setTextFill(Color.WHITE);
		TextField nomineePrimary = new TextField();
		nomineePrimaryRow.getChildren().addAll(asknomineePrimary, nomineePrimary);
		nomineePrimaryRow.setAlignment(Pos.CENTER);

		Button addNominee = new Button("Add Nominee");
		addNominee.setAlignment(Pos.CENTER);
		addNominee.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					for (ViewListenable l : allListeners) {
						l.AddNomnieFromView(nomineeName.getText(), Integer.parseInt(nomineeID.getText()),
								Integer.parseInt(nomineeYear.getText()), Integer.parseInt(nomineeBallot.getText()),
								nomineeIsSick.isSelected(), nomineeParty.getText(),
								Integer.parseInt(nomineePrimary.getText()));
					}
				} catch (CitizenExistsException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (YearBirthException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IdException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (PartyNotExistException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

		});

		NomineePage.getChildren().addAll(nomineeNameRow, nomineeIdRow, nomineeYearRow, nomineeBallotRow, nomineeSickRow,
				nomineePartyRow, nomineePrimaryRow, addNominee);
		NomineePage.setAlignment(Pos.CENTER);

///////////////////////////////SHOW ALL BALLOTS///////////////////////////////////////////////////////////////

		VBox allBallotdetails = new VBox(20);
		btnShowBallots.setText("Ballot Information");

		Button showBallots = new Button("Show All The Ballots");
		ScrollPane scrollBallots = new ScrollPane();
		scrollBallots.setVisible(false);
		allBallotdetails.getChildren().addAll(showBallots, scrollBallots);

		showBallots.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				scrollBallots.setVisible(true);
				scrollBallots.setFitToHeight(true);
				for (ViewListenable l : allListeners)
					scrollBallots.setContent(new Text(l.showAllballotsInformation()));

			}
		});
		allBallotdetails.setAlignment(Pos.CENTER);
///////////////////////////////SHOW ALL CITIZENS///////////////////////////////////////////////////////////////

		VBox allCitizensdetails = new VBox(20);
		btnshowCitizens.setText("Citizen Information");

		Button showCitizen = new Button("Show all Citizens");
		ScrollPane scrollCitizen = new ScrollPane();
		scrollCitizen.setVisible(false);
		allCitizensdetails.getChildren().addAll(showCitizen, scrollCitizen);

		showCitizen.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				scrollCitizen.setVisible(true);
				scrollCitizen.setFitToHeight(true);
				for (ViewListenable l : allListeners)
					scrollCitizen.setContent(new Text(l.showAllCItizenInformation()));

			}
		});
		allCitizensdetails.setAlignment(Pos.CENTER);

///////////////////////////////SHOW ALL PARTIES///////////////////////////////////////////////////////////////

		VBox allPartiesdetails = new VBox(20);
		btnShowParties.setText("Parties Information");

		Button showParties = new Button("Show all Parties");
		ScrollPane scrollParties = new ScrollPane();
		scrollParties.setVisible(false);
		allPartiesdetails.getChildren().addAll(showParties, scrollParties);

		showParties.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				scrollParties.setVisible(true);
				scrollParties.setFitToHeight(true);
				for (ViewListenable l : allListeners)
					scrollParties.setContent(new Text(l.showAllPartiesInformation()));

			}
		});
		allPartiesdetails.setAlignment(Pos.CENTER);
		
///////////////////////////////MAKE AN ELECTION///////////////////////////////////////////////////////////////

		VBox electionPage = new VBox();
		btnMakeElection.setText("Make An Election");

		Label electionWelcome = new Label("Welcome To The Election");
		electionWelcome.setTextFill(Color.WHITE);
		electionWelcome.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		electionWelcome.setAlignment(Pos.CENTER);
		Label electionExplanation = new Label(
				"The system goes over all citizens and asks whether the citizen wants to vote, who to vote for.");
		electionExplanation.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		electionExplanation.setTextFill(Color.WHITE);
		Button makeElection = new Button("Make An Election");
		makeElection.setAlignment(Pos.CENTER);
		makeElection.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				makeElectionFromView();
			}
		});
		electionPage.getChildren().addAll(electionWelcome, electionExplanation, makeElection);
		electionPage.setAlignment(Pos.CENTER);

///////////////////////////////SHOW ALL RESULTS///////////////////////////////////////////////////////////////

		VBox resultsPage = new VBox(20);
		resultsPage.setAlignment(Pos.CENTER);
		btnElectionResults.setText("Election Results");

		Button showResults = new Button("Show The Election Results");
		ScrollPane scrollResults = new ScrollPane();

		scrollResults.setVisible(false);
		showResults.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				scrollResults.setVisible(true);
				scrollResults.setFitToHeight(true);
				for (ViewListenable l : allListeners) {
					scrollResults.setContent(new Text(l.showResultsElectionFromView()));}
			}
			
			
		});
		resultsPage.getChildren().addAll(showResults, scrollResults);

///////////////////////////////END///////////////////////////////////////////////////////////////

		btnexit.setText("Exit System");
		btnexit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				electionStage.close();
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////	

		menu.getChildren().addAll(menuTitle, choice);
		system.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		system.setTop(welcome);
		system.setLeft(menu);
		system.setCenter(currentWindow);

		addCurrentWindowOption(btnAddBallot, ballotPage);
		addCurrentWindowOption(btnAddCitizen, citizenPage);
		addCurrentWindowOption(btnAddParty, partyPage);
		addCurrentWindowOption(btnAddNomniee, NomineePage);
		addCurrentWindowOption(btnShowBallots, allBallotdetails);
		addCurrentWindowOption(btnshowCitizens, allCitizensdetails);
		addCurrentWindowOption(btnShowParties, allPartiesdetails);
		addCurrentWindowOption(btnMakeElection, electionPage);
		addCurrentWindowOption(btnElectionResults, resultsPage);
		menu.getChildren().add(btnexit);

		for (Node page : currentWindow.getChildren()) {
			page.setVisible(false);

		}
		electionStage.setScene(new Scene(system, 0, 0));
		electionStage.show();
	}

	public void makeElectionFromView() {
		for (ViewListenable l : allListeners) {
			 listOfCiziten = l.getCitizensToView();
			 listOfParties = l.getPartiesToView();
		}
		makeElection(listOfCiziten, listOfParties);

	}
	
	public void wearProtectiveSuitFromView(int citizenAnswer) throws ProtectiveSuitException {
		if (citizenAnswer == JOptionPane.YES_OPTION) {
			return;
		}
		throw new ProtectiveSuitException();
	}

	public void makeElection(Set<Citizen> citizens, Vector<Party> listOfParties) {
		Vector<String> nameOfParties = new Vector<String>();
		for (int j = 0; j < listOfParties.size(); j++) {
			nameOfParties.add(listOfParties.get(j).getName());
		}
		
		JComboBox<String> jListOfPatries = new JComboBox<String>(nameOfParties);
	
	
		for (int i = 0; i < citizens.size(); i++) {
			int returnValue = JOptionPane.showConfirmDialog(null,
					((i + 1) + "." + citizens.getCollection().get(i) + "Do you want to vote?"),
					citizens.getCollection().get(i) + "choice", JOptionPane.YES_NO_OPTION);
			if (returnValue == JOptionPane.YES_OPTION) {
				if (citizens.getCollection().get(i).isIsolation()) {
					returnValue = JOptionPane.showConfirmDialog(null, "Do you wear protective suit?", "Corona Terms",
							JOptionPane.YES_NO_OPTION);
					try {
						wearProtectiveSuitFromView(returnValue);
					} catch (ProtectiveSuitException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						continue;
					}
				}
				
				String parites=getSortetListOfParties(nameOfParties);
				String citizenVote =JOptionPane.showInputDialog(null, "Which Party Do You Want to Vote?\n"+parites.toString());
				
				    int intCitizenVote=Integer.parseInt(citizenVote);
				try {
					for (ViewListenable l : allListeners) {
					l.makeVoteFromView(citizens.getCollection().get(i), intCitizenVote-1);
					}
				} catch (BallotNotExistException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		
			else {
				JOptionPane.showMessageDialog(null,
						citizens.getCollection().get(i).getName() + " Doesn't want to vote");
			}
			
		}fireElectionSuccessfullyEnd();
	}



	private String getSortetListOfParties(Vector<String> nameOfParties) {
		StringBuffer f= new StringBuffer();
		for(int i=0;i<nameOfParties.size();i++) {
			f.append((i+1)+"."+nameOfParties.get(i)+"\n");
		}
		return f.toString();
	}

	private int getIntFromStringParty(String citizenVote,Vector<String>nameOfParties) {
		for(int i=0;i<nameOfParties.size();i++) {
			if(citizenVote.equals(nameOfParties.get(i))){
				return i;
			}
		}
		return 0;
	}

	private void fireElectionSuccessfullyEnd() {
		JOptionPane.showMessageDialog(null, "Election Successfully Ended");
		
	}
	

	public void registerListener(ViewListenable listener) {
		allListeners.add(listener);
	}

	public void BallotExitsMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void CitizenExistsMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void CitizenAddedMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void SucceededAddingBallotMessage(String string) {
		JOptionPane.showMessageDialog(null, string);

	}

	public void PartyAddedMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void SucceededAddingNomnieeMessage(String string) {
		JOptionPane.showMessageDialog(null, string);
	}

	public void ElectionSuccessfullyEndMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
