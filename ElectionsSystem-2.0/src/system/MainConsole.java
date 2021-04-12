package system;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import system.*;
import system.Party.Faction;
import exceptions.*;
import exceptions.ProtectiveSuitException;

public class MainConsole {
	public static void main(String[] args) throws Exception {
		// HARD-CODED:
		LocalDate date = LocalDate.of(2021, 3, 1);
		
		RoundOfElections roundOfElections = new RoundOfElections(date);
		// BALLOTS:
		roundOfElections.addBallot(new Ballot<Citizen>(1, "Hana_Robina"),"Citizen");
		roundOfElections.addBallot(new Ballot<SickCitizen>(2, "Haim_Ben_Atar"),"Corona");
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
		roundOfElections.addParty(new Party("Kahol-Lavan", Faction.MIDDLE, 2019, 2, 21));
		roundOfElections.addParty(new Party("Meretch", Faction.LEFT, 1992, 3, 3));
		// NOMINEES:
		roundOfElections.addNominee(
				new Nominee("Guy", 315827019, 1991, 1, false, "Kahol-Lavan", 4, roundOfElections.getElectionDate()));// nominne
		roundOfElections.addNominee(
				new Nominee("Lilach", 315827017, 1995, 1, false, "Licud", 10, roundOfElections.getElectionDate()));// nominne
		roundOfElections.addNominee(
				new Nominee("Yarden", 315827021, 1993, 1, false, "Meretch", 12, roundOfElections.getElectionDate()));// nominne
		roundOfElections.addNominee(
				new Nominee("Karin", 315827022, 1992, 2, true, "Meretch", 7, roundOfElections.getElectionDate()));// nominne+corona
		roundOfElections.addNominee(
				new Nominee("Liem", 315827020, 1995, 2, true, "Kahol-Lavan", 2, roundOfElections.getElectionDate()));// nominne+corona
		roundOfElections.addNominee(
				new Nominee("Shakked", 315827018, 1996, 2, true, "Licud", 4, roundOfElections.getElectionDate()));// nominne+corona

		// start:

		boolean start = true;
		while (start) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Select an Action:\n" + "1.Add a ballot\n" + "2.Add a citizen\n" + "3.Add a party\n"
					+ "4.Add a nominee\n" + "5.Show all the baloots\n" + "6.Show all the citizens\n"
					+ "7.Show all the parties\n" + "8.Make an Election\n" + "9.Show the Election resualts\n"
					+ "10.Exit");
			
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Your Choice: 1.Add a ballot.\nEnter the ballot serial number:");
				int serialNum1 = scan.nextInt();
				System.out.println("Enter the ballot street: ");
				String street = scan.next();
				boolean enter = true;
				while (enter) {
					System.out.println("choose your wanted type\n1.Regular Ballot\n2.Army Ballot\n3.Corona Ballot\n4.Corona Army Ballot");
					int type = scan.nextInt();

					switch (type) {

					case 1:
						roundOfElections.addBallot(new Ballot<Citizen>(serialNum1, street),"Regular");
						enter = false;
						break;

					case 2:
						roundOfElections.addBallot(new Ballot<Soldier>(serialNum1, street),"Army");
						enter = false;
						break;

					case 3:
						roundOfElections.addBallot(new Ballot<SickCitizen>(serialNum1, street),"Corona");
						enter = false;
						break;
					case 4:
						roundOfElections.addBallot(new Ballot<SickSoldier>(serialNum1, street),"Corona Army");
						enter=false;
						break;
					default:
						System.out.println("Worng type, enter again");
					}
				}
				break;

			case 2:
				System.out.println("Your Choice: 2.Add a citizen.\nEnter the citizen name: ");
				String name = scan.next();
				System.out.println("\nEnter the citizen year birth: ");
				int yearBirth = scan.nextInt();
				System.out.println("\nEnter the ballot serial number: ");
				int serialNum2 = scan.nextInt();
				System.out.println("\nDo you have Corona Virus?: Enter True / False ");
				boolean isolation = scan.nextBoolean();
				boolean idOk = true;
				while (idOk) {
					System.out.println("Enter the citizen ID: ");
					try {
						int id = scan.nextInt();
						roundOfElections.addCitizen(new Citizen(name, id, yearBirth, serialNum2, isolation,
								roundOfElections.getElectionDate()));
						idOk = false;
					} catch (IdException e) {
						System.out.println(e.getMessage());
					} catch (YearBirthException e) {
						System.out.println(e.getMessage());
						break;
					}
				}
				break;

			case 3:
				System.out.println("Your Choice: 3. Add a party.\nEnter the party name: ");
				String partyName = scan.nextLine();
				scan.next();
				System.out.println("\nEnter the party faction: ");
				Faction efaction = Faction.valueOf(scan.next().toUpperCase());
				System.out.println("\nEnter the party year birth: ");
				int partyYearBirth = scan.nextInt();
				System.out.println("\nEnter the party month birth: ");
				int partyMonthBirth = scan.nextInt();
				System.out.println("\nEnter the party day birth: ");
				int partyDayBirth = scan.nextInt();
				roundOfElections
						.addParty(new Party(partyName, efaction, partyYearBirth, partyMonthBirth, partyDayBirth));
				break;

			case 4:
				System.out.println("Your Choice: 4.Add a nominee.\nEnter the nominee name: ");
				String nameNominee = scan.next();
				System.out.println("\nEnter the nominee year birth: ");
				int yearBirth4 = scan.nextInt();
				System.out.println("\nEnter the ballot serial number: ");
				int serialNum4 = scan.nextInt();
				System.out.println("\nAre you in isolation?: enter true/flase ");
				boolean isolation4 = scan.hasNext();
				scan.next();
				System.out.println("\nEnter your party name: ");
				String partyName4 = scan.next();
				System.out.println("\nEnter your primary number: ");
				int primaryNum = scan.nextInt();
				boolean idOk2 = true;
				while (idOk2) {
					try {
						System.out.println("Enter the nominee ID: ");
						int idNominee = scan.nextInt();
						roundOfElections.addNominee(new Nominee(nameNominee, idNominee, yearBirth4, serialNum4,
								isolation4, partyName4, primaryNum, roundOfElections.getElectionDate()));
						idOk2 = false;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;

			case 5:
				System.out.println("Youer Choice: 5.Show all the ballots.\n" + roundOfElections.printBallots());
				break;

			case 6:
				System.out.println("Your Choice: 6.Show all the citizens.\n" + roundOfElections.printCitizens());
				break;

			case 7:
				System.out.println("Your Choice: 7.Show all the parties.\n" + roundOfElections.printParties());
				break;

			case 8:
				System.out.println("Your Choice: 8.Make an Election.");
				Vector<Party> listOfParties=roundOfElections.getListOfParties();
				Set<Citizen> listOfCitizens=roundOfElections.getCitizens();
				
				for (int i = 0; i < listOfCitizens.size(); i++) {
					System.out.println(
							(i + 1) + "." + listOfCitizens.getCollection().get(i) + "Do you want to vote? Y for yes N for no. ");
					if (scan.next().toLowerCase().charAt(0) == 'y') {
						if (listOfCitizens.getCollection().get(i).coronaSickness) {
							System.out.println("Do you wear protective suit? Y for yes N for no.");
							try {
								roundOfElections.wearProtectiveSuit(scan.next().toLowerCase().charAt(0));
								continue;
							}catch(ProtectiveSuitException e) {
								System.out.println(e.getMessage());
								break;
							}
						}
							System.out.println("Type the number of your choosen party from the follow parites:");
							for (int j = 0; j < listOfParties.size(); j++) {
								System.out.println((j + 1) + "." + listOfParties.get(j).getName());
							}
							int citizenVote = scan.nextInt();
							roundOfElections.makeVote(listOfCitizens.getCollection().get(i), citizenVote - 1);
						} else {
							System.out.println(listOfCitizens.getCollection().get(i).getName() + " Doesn't want to vote");
						}
					}
				
				break;

			case 9:
				System.out.println("Your choice: 9.Show the Election resualts:\n" + roundOfElections.showResults());
				break;

			case 10:
				System.out.println("Your choice: 10.Exit\n");
				start = false;
			}

		}
}}
