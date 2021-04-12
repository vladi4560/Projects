package system;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

import exceptions.BallotExistsException;
import exceptions.BallotNotExistException;
import exceptions.CitizenExistsException;
import exceptions.PartyExistException;
import exceptions.PartyNotExistException;
import exceptions.ProtectiveSuitException;

public class RoundOfElections {
	private LocalDate electionDate;

	private Set<Citizen> citizens;

	private Vector<Party> listOfParties;
	private Vector<Integer>sum;
	private Vector<Ballot<Citizen>> citizenBallots;

	public void setListOfParties(Vector<Party> listOfParties) {
		this.listOfParties = listOfParties;
	}

	private Vector<Ballot<SickCitizen>> sickCitizenBallots;
	private Vector<Ballot<Soldier>> soldierBallots;
	private Vector<Ballot<SickSoldier>> sickSoldierBallots;

	public RoundOfElections(LocalDate electionDate) {
		this.electionDate = electionDate;

		this.citizens = new Set<Citizen>();

		this.listOfParties = new Vector<Party>();
		
		this.sum=new Vector<Integer>();

		this.citizenBallots = new Vector<Ballot<Citizen>>();

		this.sickCitizenBallots = new Vector<Ballot<SickCitizen>>();

		this.soldierBallots = new Vector<Ballot<Soldier>>();

		this.sickSoldierBallots = new Vector<Ballot<SickSoldier>>();

	}

	public Vector<Ballot<? extends Citizen>> getAllBallots() {
		Vector<Ballot<? extends Citizen>> allBallots = new Vector<Ballot<? extends Citizen>>();
		for (int i = 0; i < citizenBallots.size(); i++) {
			allBallots.add(citizenBallots.get(i));
		}
		for (int i = 0; i < soldierBallots.size(); i++) {
			allBallots.add(soldierBallots.get(i));
		}
		for (int i = 0; i < sickCitizenBallots.size(); i++) {
			allBallots.add(sickCitizenBallots.get(i));
		}
		for (int i = 0; i < sickSoldierBallots.size(); i++) {
			allBallots.add(sickSoldierBallots.get(i));
		}
		return allBallots;
	}

	public boolean ballotExists(int balloId) {
		for (int i = 0; i < getAllBallots().size(); i++) {
			if (getAllBallots().get(i).getSerialNum() == balloId) {
				return true;
			}
		}
		return false;
	}

	public void addBallot(Ballot<?> ballot, String typeName) throws BallotExistsException {
		if (ballotExists(ballot.serialNum)) {
			throw new BallotExistsException();
		}
		for (int i = 0; i < listOfParties.size(); i++) {
			ballot.addParty(listOfParties.get(i));
		}

		if (typeName.equals("Citizen")) {
			this.citizenBallots.add((Ballot<Citizen>) ballot);
		}
		if (typeName.equals("Army")) {
			this.soldierBallots.add((Ballot<Soldier>) ballot);
		}
		if (typeName.equals("Corona")) {
			this.sickCitizenBallots.add((Ballot<SickCitizen>) ballot);
		}
		if (typeName.equals("Corona Army")) {
			this.sickSoldierBallots.add((Ballot<SickSoldier>) ballot);
		}
	}

	public Ballot getBallotById(int ballotId) {
		for (int i = 0; i < citizenBallots.size(); i++) {
			if (citizenBallots.get(i).getSerialNum() == ballotId) {
				return citizenBallots.get(i);
			}
		}
		for (int i = 0; i < sickCitizenBallots.size(); i++) {
			if (sickCitizenBallots.get(i).getSerialNum() == ballotId) {
				return sickCitizenBallots.get(i);
			}
		}
		for (int i = 0; i < soldierBallots.size(); i++) {
			if (soldierBallots.get(i).getSerialNum() == ballotId) {
				return soldierBallots.get(i);
			}
		}
		for (int i = 0; i < sickSoldierBallots.size(); i++) {
			if (sickSoldierBallots.get(i).getSerialNum() == ballotId) {
				return sickSoldierBallots.get(i);
			}
		}
		return null;

	}

	public <T extends Citizen> void addCitizen(Citizen citizen) throws CitizenExistsException, BallotNotExistException {

		// Add the citizen to the list
		if (!(this.citizens.add(citizen))) {
			throw new CitizenExistsException();
		}
		// Add the citizen to the appropriate ballot
		String nameType = "";
		int ballotId = citizen.getSerialBallotNum();
		int i = 0;
		for (i = 0; i < getAllBallots().size(); i++) {
			if (getAllBallots().get(i).getSerialNum() == ballotId) {
				getBallotById(ballotId).addCitizen(citizen);
				return;
			}
		}
		this.citizens.remove(citizen);
		throw new BallotNotExistException();

	}

	private Party getParty(String partyName) {
		for (int i = 0; i < listOfParties.size(); i++) {
			if (listOfParties.get(i).getName().toLowerCase().equals(partyName.toLowerCase())) {
				return listOfParties.get(i);
			}
		}
		return null;
	}

	private boolean partyExists(String partyName) {
		return getParty(partyName) != null;
	}

	public void addParty(Party party) throws PartyExistException {
		if ((partyExists(party.getName()))) {
			throw new PartyExistException();
		} else {
			listOfParties.add(party);
			sum.add(0);
			for (int i = 0; i < citizenBallots.size(); i++) {
				citizenBallots.get(i).addParty(party);
			}
			for (int i = 0; i < sickCitizenBallots.size(); i++) {
				sickCitizenBallots.get(i).addParty(party);
			}
			for (int i = 0; i < soldierBallots.size(); i++) {
				soldierBallots.get(i).addParty(party);
			}
			for (int i = 0; i < sickSoldierBallots.size(); i++) {
				sickSoldierBallots.get(i).addParty(party);
			}
		}
	}

	public void addNominee(Nominee nominee) throws PartyNotExistException, CitizenExistsException {
		if (!partyExists(nominee.getPartyName())) {
			throw new PartyNotExistException();
		}
		// Add the nominee as a citizen
		if (!citizens.add(nominee)) {
			throw new CitizenExistsException();
		}

		// Add the nominee to the party
		Party party = getParty(nominee.getPartyName());
		party.addNominee(nominee);

	}

	public String printBallots() {
		StringBuffer sb = new StringBuffer();
		sb.append("List of all the ballots in the currnet elections:\n");
		for (int i = 0; i < getAllBallots().size(); i++) {
			sb.append((i + 1) + "." + getAllBallots().get(i).toString()+"\n");
		}
		return sb.toString();
	}

	public String printCitizens() {
		StringBuffer sb = new StringBuffer();
		sb.append("List of all the citizens in the currnet elections:\n");
		for (int i = 0; i < citizens.size(); i++) {
			sb.append((i + 1) + "." + citizens.getCollection().get(i).toString()+"\n");
		}
		return sb.toString();
	}

	public String printParties() {
		StringBuffer sb = new StringBuffer();
		sb.append("List of all the parties in the currnet elections:\n");
		for (int i = 0; i < listOfParties.size(); i++) {
			sb.append((i + 1) + "." + listOfParties.get(i).toString()+"\n");
		}
		return sb.toString();
	}

	public void makeVote(Citizen citizen, int party) {
		Ballot ballot = getBallotById(citizen.getSerialBallotNum());
		if (ballot == null) {
			System.out.println("Ballot doesn't exists or you have the worng serial ballot number.");
		}
		ballot.vote(party);
	}
	
	public void wearProtectiveSuit(char citizenAnswer) throws ProtectiveSuitException {
		if(citizenAnswer=='y') {
			return;
		}
		throw new ProtectiveSuitException();
	}
	public String showAllParties() {
		StringBuffer Parties=new StringBuffer();
		for (int j = 0; j < listOfParties.size(); j++) {
			Parties.append((j + 1) + "." + listOfParties.get(j).getName()+"\n");
		}
		return Parties.toString();
	}

	public String showResults() {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < getAllBallots().size(); j++) {
			sb.append("Ballot " + getAllBallots().get(j).getSerialNum() + getAllBallots().get(j).getVotesPerParty());
			for (int i = 0; i < listOfParties.size(); i++) {
				sum.set(i,sum.get(i)+getAllBallots().get(j).getVotes(i));
				}
		}
		sb.append("The final results:\n");
		for (int i = 0; i < listOfParties.size(); i++) {
			sb.append("Party " + listOfParties.get(i).getName() + " " + sum.get(i) + "\n");
		}
		return sb.toString();
	}

	public LocalDate getElectionDate() {
		return electionDate;
	}

	public Set<Citizen> getCitizens() {
		return citizens;
	}

	public Vector<Party> getListOfParties() {
		return listOfParties;
	}

}
