package system;

import java.util.Arrays;
import java.util.Vector;

public class Ballot<T extends Citizen> {
	protected int serialNum;
	protected String street;

	protected Vector<T> citizens;

	protected Vector<Party> parties;
	protected Vector<Integer> votesPerParty;
	
	public boolean canVote(T citizen) {
		return true;
	}

	public Ballot(int serialNum, String street) {
		this.serialNum = serialNum;
		this.street = street;

		this.citizens = new Vector<T>();
		
		this.parties = new Vector<Party>();
		this.votesPerParty = new Vector<Integer>(parties.size());
	}


	public void addParty(Party party) {
		this.parties.add(party);
		this.votesPerParty.add(0);
	}

	public double getVotingPercentage() {
		double totalVotes = 0;

		for (int i = 0; i < votesPerParty.size(); i++) {
			totalVotes += votesPerParty.elementAt(i);
		}

		return totalVotes / (double) citizens.size();
	}

	public String getVotesPerParty() {
		StringBuffer sb = new StringBuffer();
		sb.append(" List of votes per party in :\n");
		for (int i = 0; i < parties.size(); i++) {
			sb.append((i + 1) + "." + parties.elementAt(i).getName() + ": " + votesPerParty.elementAt(i) + "\n");
		}
		return sb.toString();
	}

	public int getVotes(int index) {
		return this.votesPerParty.elementAt(index);
	}

	public void vote(int partyIndex) {
		votesPerParty.setElementAt(votesPerParty.elementAt(partyIndex).intValue()+1, partyIndex);
	}

	public  void addCitizen(T citizen) {
		this.citizens.add(citizen);
	}

	public int getSerialNum() {
		return this.serialNum;
	}

	public boolean equals(Object ballot) {
		if (!(ballot instanceof Ballot)) {
			return false;
		}
		Ballot<T> temp = (Ballot<T>) ballot;
		if (!(this.getSerialNum() == temp.getSerialNum())) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "Ballot serial number: " + this.serialNum + ", street: " + this.street + "\n";

	}
}



