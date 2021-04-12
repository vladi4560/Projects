package system;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import system.*;
import system.Party.Faction;
import exceptions.*;
import exceptions.ProtectiveSuitException;

public class MainGrafic {
	public static void main(String[] args) throws Exception {
		Messageable UI = new GraficalUI();
		// HARD-CODED:
		LocalDate date = LocalDate.of(2021, 3, 1);
		RoundOfElections roundOfElections = new RoundOfElections(date);
		// BALLOTS:
		roundOfElections.addBallot(new Ballot<Citizen>(1, "Hana Robina"), "Citizen");
		roundOfElections.addBallot(new Ballot<SickCitizen>(2, "Haim Ben Atar"), "Corona");
		roundOfElections.addBallot(new Ballot<Soldier>(3, "Rotchild"), "Army");
		roundOfElections.addBallot(new Ballot<SickSoldier>(4, "Herzel"), "Corona Army");
		// CITIZENS:
		roundOfElections
				.addCitizen(new Citizen("Dorin", 315827011, 1995, 1, false, roundOfElections.getElectionDate()));// normal
		roundOfElections
				.addCitizen(new Citizen("Vladi", 315827012, 1997, 1, false, roundOfElections.getElectionDate()));// normal
		roundOfElections.addCitizen(new Citizen("Amit", 315827013, 1995, 2, true, roundOfElections.getElectionDate()));// corona
		roundOfElections.addCitizen(new Citizen("Rom", 315827014, 1996, 2, true, roundOfElections.getElectionDate()));// corona
		roundOfElections
				.addCitizen(new Citizen("Shahar", 315827015, 2001, 3, false, roundOfElections.getElectionDate()));// soldier
		roundOfElections.addCitizen(new Citizen("Omer", 315827016, 2000, 4, true, roundOfElections.getElectionDate()));// soldiercorona
		// PARTIES:
		roundOfElections.addParty(new Party("Licud", Faction.RIGHT, 1973, 9, 13));
		roundOfElections.addParty(new Party("Kahol Lavan", Faction.MIDDLE, 2019, 2, 21));
		roundOfElections.addParty(new Party("Meretch", Faction.LEFT, 1992, 3, 3));
		// NOMINEES:
		roundOfElections.addNominee(
				new Nominee("Guy", 315827019, 1991, 1, false, "Kahol Lavan", 4, roundOfElections.getElectionDate()));// nominne
		roundOfElections.addNominee(
				new Nominee("Lilach", 315827017, 1995, 1, false, "Licud", 10, roundOfElections.getElectionDate()));// nominne
		roundOfElections.addNominee(
				new Nominee("Yarden", 315827021, 1993, 1, false, "Meretch", 12, roundOfElections.getElectionDate()));// nominne
		roundOfElections.addNominee(
				new Nominee("Karin", 315827022, 1992, 2, true, "Meretch", 7, roundOfElections.getElectionDate()));// nominne+corona
		roundOfElections.addNominee(
				new Nominee("Liem", 315827020, 1995, 2, true, "Kahol Lavan", 2, roundOfElections.getElectionDate()));// nominne+corona
		roundOfElections.addNominee(
				new Nominee("Shakked", 315827018, 1996, 2, true, "Licud", 4, roundOfElections.getElectionDate()));// nominne+corona

		// start:

		boolean start = true;
		while (start) {
			// Scanner scan = new Scanner(System.in);

			int choice = UI.getInt("Select an Action:\n" + "1.Add a ballot\n" + "2.Add a citizen\n" + "3.Add a party\n"
					+ "4.Add a nominee\n" + "5.Show all the baloots\n" + "6.Show all the citizens\n"
					+ "7.Show all the parties\n" + "8.Make an Election\n" + "9.Show the Election resualts\n"
					+ "10.Exit");

			switch (choice) {
			case 1:
				int serialNum1 = UI.getInt("Your Choice: 1.Add a ballot.\nEnter the ballot serial number:");
				String street = UI.getString("Enter the ballot street:");

				boolean enter = true;
				while (enter) {
					int type = UI.getInt(
							"choose your wanted type\n1.Regular Ballot\n2.Army Ballot\n3.Corona Ballot\n4.Corona Army Ballot");
					try {
						switch (type) {

						case 1:
							roundOfElections.addBallot(new Ballot<Citizen>(serialNum1, street), "Regular");
							enter = false;
							break;

						case 2:
							roundOfElections.addBallot(new Ballot<Soldier>(serialNum1, street), "Army");
							enter = false;
							break;

						case 3:
							roundOfElections.addBallot(new Ballot<SickCitizen>(serialNum1, street), "Corona");
							enter = false;
							break;
						case 4:
							roundOfElections.addBallot(new Ballot<SickSoldier>(serialNum1, street), "Corona Army");
							enter = false;
							break;
						default:
							UI.showMessage("Worng type, enter again");
						}

						UI.showMessage("Ballot successfully entered and added");
					} catch (BallotExistsException e) {
						UI.showMessage(e.getMessage());
						break;
					}
				}
				break;

			case 2:
				String name = UI.getString("Your Choice: 2.Add a citizen.\nEnter the citizen name: ");
				int yearBirthC = UI.getInt("Enter the citizen year birth:");
				int serialNum2 = UI.getInt("Enter the ballot serial number: ");
				boolean coronaSickness = UI.getBoolean("Do you have Corona Virus?: Enter True / False ");
				boolean idOkC = true;
				while (idOkC) {
					try {
						int id = UI.getInt("Enter the citizen ID:");
						roundOfElections.addCitizen(new Citizen(name, id, yearBirthC, serialNum2, coronaSickness,
								roundOfElections.getElectionDate()));
						idOkC = false;
						UI.showMessage("Citizen successfully entered and added");
					} catch (IdException e) {
						UI.showMessage(e.getMessage());
						break;
					} catch (YearBirthException e) {
						UI.showMessage(e.getMessage());
						break;
					}catch (BallotNotExistException e) {
						UI.showMessage(e.getMessage());
						break;
					}
				}
				break;

			case 3:
				String partyName = UI.getString("Your Choice: 3. Add a party.\nEnter the party name:");
				Faction efaction = Faction
						.valueOf(UI.getString("\nEnter the party faction: LEFT || RIGHT || MIDDLE").toUpperCase());
				int partyYearBirth = UI.getInt("\nEnter the party year birth: ");
				int partyMonthBirth = UI.getInt("\nEnter the party month birth: ");
				int partyDayBirth = UI.getInt("\nEnter the party day birth: ");
				try {
				roundOfElections
						.addParty(new Party(partyName, efaction, partyYearBirth, partyMonthBirth, partyDayBirth));
				UI.showMessage("Party successfully entered and added");
				}catch (PartyExistException e) {
					UI.showMessage(e.getMessage());
				}
				break;

			case 4:
				String nameNominee = UI.getString("Your Choice: 4.Add a nominee.\nEnter the nominee name: ");
				int yearBirthN = UI.getInt("\nEnter the nominee year birth: ");
				int serialNumN = UI.getInt("\nEnter the ballot serial number: ");
				boolean isolationN = UI.getBoolean("\nAre you in isolation?: enter true/flase ");
				String partyNameN = UI.getString("\nEnter your party name: ");
				int primaryNum = UI.getInt("\nEnter your primary number: ");
				boolean idOkN = true;
				while (idOkN) {
					try {
						int idNominee = UI.getInt("Enter the nominee ID:");
						roundOfElections.addNominee(new Nominee(nameNominee, idNominee, yearBirthN, serialNumN,
								isolationN, partyNameN, primaryNum, roundOfElections.getElectionDate()));
						idOkN = false;
					} catch (CitizenExistsException e) {
						UI.showMessage(e.getMessage());
					}
				}
				UI.showMessage("Nominee successfully entered and added");
				break;

			case 5:
				UI.showMessage("Youer Choice: 5.Show all the ballots.\n" + roundOfElections.printBallots());
				break;

			case 6:
				UI.showMessage("Your Choice: 6.Show all the citizens.\n" + roundOfElections.printCitizens());
				break;

			case 7:
				UI.showMessage("Your Choice: 7.Show all the parties.\n" + roundOfElections.printParties());
				break;

			case 8:
				UI.showMessage("Your Choice: 8.Make an Election.");
				Vector<Party> listOfParties = roundOfElections.getListOfParties();
				Set<Citizen> listOfCitizens = roundOfElections.getCitizens();

				for (int i = 0; i < listOfCitizens.size(); i++) {
					String answer = UI.getString((i + 1) + "." + listOfCitizens.getCollection().get(i)
							+ "\nDo you want to vote? Y for yes N for no. ");
					if (answer.toLowerCase().charAt(0) == 'y') {
						if (listOfCitizens.getCollection().get(i).coronaSickness) {
							answer = UI.getString("Do you wear protective suit? Y for yes N for no.");
							try {
								roundOfElections.wearProtectiveSuit(answer.toLowerCase().charAt(0));
							} catch (ProtectiveSuitException e) {
								UI.showMessage(e.getMessage());
								break;
							}
						}
						int citizenVote = UI.getInt("Type the number of your choosen party from the follow parites:\n"
								+ roundOfElections.showAllParties());
						roundOfElections.makeVote(listOfCitizens.getCollection().get(i), citizenVote - 1);
					} else {
						UI.showMessage(listOfCitizens.getCollection().get(i).getName() + " Doesn't want to vote");
					}
				}
				UI.showMessage("Election successfully ended");
				break;

			case 9:
				UI.showMessage("Your choice: 9.Show the Election resualts:\n" + roundOfElections.showResults());
				break;

			case 10:
				UI.showMessage("Your choice: 10.Exit\nGoodbye :)");
				start = false;
			}

		}
	}
}
