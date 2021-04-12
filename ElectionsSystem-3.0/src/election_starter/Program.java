package election_starter;

import java.time.LocalDate;

import election_controller.*;
import election_listeners.*;
import election_starter.*;
import election_system_model.*;
import election_system_model.Party.Faction;
import election_view.*;
import model_exceptions.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.fxml.FXML;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage electionStage) throws Exception {
		LocalDate electionDate = LocalDate.of(2021, 03, 01);
		RoundOfElections theModel = new RoundOfElections(electionDate);
		// BALLOTS:
		theModel.addBallot(new Ballot<Citizen>(1, "Hana Robina"), "Regular");
		theModel.addBallot(new Ballot<SickCitizen>(2, "Haim Ben Atar"), "Corona");
		theModel.addBallot(new Ballot<Soldier>(3, "Rotchild"), "Army");
		theModel.addBallot(new Ballot<SickSoldier>(4, "Herzel"), "Corona Army");
		// CITIZENS:
		theModel.addCitizen(new Citizen("Dorin", 315827011, 1995, 1, false, theModel.getElectionDate()));// normal
		theModel.addCitizen(new Citizen("Vladi", 315827012, 1997, 1, false, theModel.getElectionDate()));// normal
		theModel.addCitizen(new Citizen("Amit", 315827013, 1995, 2, true, theModel.getElectionDate()));// corona
		theModel.addCitizen(new Citizen("Rom", 315827014, 1996, 2, true, theModel.getElectionDate()));// corona
		theModel.addCitizen(new Citizen("Shahar", 315827015, 2001, 3, false, theModel.getElectionDate()));// soldier
		theModel.addCitizen(new Citizen("Omer", 315827016, 2000, 4, true, theModel.getElectionDate()));// soldiercorona
		// PARTIES:
		theModel.addParty(new Party("Licud", Faction.RIGHT, 1973, 9, 13));
		theModel.addParty(new Party("Kahol Lavan", Faction.MIDDLE, 2019, 2, 21));
		theModel.addParty(new Party("Meretch", Faction.LEFT, 1992, 3, 3));
		// NOMINEES:
		theModel.addNominee(
				new Nominee("Guy", 315827019, 1991, 1, false, "Kahol Lavan", 4, theModel.getElectionDate()));// nominne
		theModel.addNominee(
				new Nominee("Lilach", 315827017, 1995, 1, false, "Licud", 10, theModel.getElectionDate()));// nominne
		theModel.addNominee(
				new Nominee("Yarden", 315827021, 1993, 1, false, "Meretch", 12, theModel.getElectionDate()));// nominne
		theModel.addNominee(
				new Nominee("Karin", 315827022, 1992, 2, true, "Meretch", 7, theModel.getElectionDate()));// nominne+corona
		theModel.addNominee(
				new Nominee("Liem", 315827020, 1995, 2, true, "Kahol Lavan", 2, theModel.getElectionDate()));// nominne+corona
		theModel.addNominee(
				new Nominee("Shakked", 315827018, 1996, 2, true, "Licud", 4, theModel.getElectionDate()));// nominne+corona

		View1 theView = new View1(electionStage);
		Controller theController = new Controller(theModel, theView);
	}

}